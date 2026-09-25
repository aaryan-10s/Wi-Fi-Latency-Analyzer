/*
    Aaryan Soni
    Began: 9/22, 6:30 PM
    Goal: New Java file to create the actual desktop application and GUI Interface for Wi-Fi Latency Analyzer
 */

import java.awt.Component;
import java.awt.FlowLayout;     //  Controls how components are arranged
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;     //  Utilize array List tools
import javax.swing.*;       //  Gives access to Windows Swing components

public class LatencyAnalyzerWindow
{
    public static void main(String[] args) 
    {
        //  Runs the interface safely on java's user interface thread
        SwingUtilities.invokeLater(() -> 
        {
            JFrame window = new JFrame("Wi-Fi Latency Analyzer");       //  Creates the main app Window and assigns a title

            JPanel panel = new JPanel();        //  Creates panel to hold labels, dropdowns, etc.
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));        //  Places componenents top --> bottom
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            //      ------------ TITLE SECTION -------------
            
            JLabel title = new JLabel("Wi-Fi Latency Analyzer");        //  Creates main title shown in the app
            title.setFont(new Font("Arial", Font.BOLD, 24));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            panel.add(title);
            panel.add(Box.createVerticalStrut(20));

            //      ------------    TEST SETTINGS SECTION    ------------
            
            JPanel targetPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            
            JLabel targetLabel = new JLabel("Choose a test target: ");      //  Explains what dropdown is used for

            //  Dropdown: user chooses safe test targets (public DNS servers)
            String[] targets =                      
            {
                "1.1.1.1",
                "8.8.8.8"
            };

            JComboBox<String> targetDropBox = new JComboBox<>(targets);     //  Creates a dropdown menu for users to choose test target

            targetPanel.add(targetLabel);
            targetPanel.add(targetDropBox);

            JPanel testCountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel testCountLabel = new JLabel("Number of tests:");
            JTextField testCountField = new JTextField("5", 5);     //  Text field begins with 5 as suggested # of tests the user should run

            testCountPanel.add(testCountLabel);
            testCountPanel.add(testCountField);
            
            JPanel delayPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel delayLabel = new JLabel("Delay between tests (seconds): ");

            JTextField delayField = new JTextField("1", 5);     //  Text field begins with 1 as the suggestedd text delay
            
            delayPanel.add(delayLabel);
            delayPanel.add(delayField);
            
            //      Add to main panel (variable = panel)
            panel.add(targetPanel);
            panel.add(testCountPanel);
            panel.add(delayPanel);
            panel.add(Box.createVerticalStrut(10));

            //      ----------    BUTTON AND STATUS SECTION    ---------- 

            JButton startButton = new JButton("Start Test");        //  Button to run real Ping Tests
            startButton.setAlignmentX(Component.CENTER_ALIGNMENT);      //  Button alignment on the panel
            JLabel statusLabel = new JLabel("Status: Ready to test");       //  Displays status
            statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            //  Placeholder values (eg. -- ms) will be replaced by real ping data later after connecting core program to desktop APP
            JLabel averageLatencyLabel = new JLabel("Average Latency: -- ms");
            JLabel minLatencyLabel = new JLabel("Minimum Latency: -- ms ");
            JLabel maxLatencyLabel = new JLabel("Maximum Latency: -- ms");
            JLabel packetLossLabel = new JLabel("Packet Loss: -- %");
            JLabel jitterLabel = new JLabel("Jitter: -- ms");

            //  Runs everytime user clicks button 'Start Test' (IMPORTANT as it connects src.java functionality with the UI)
            startButton.addActionListener(event -> 
            {
                String SelectedTarget = (String) targetDropBox.getSelectedItem();       //  Reads item selected from dropdown menu
                
                //  Read # of tests and delay from text fields
                String testCountText = testCountField.getText();
                String delayText = delayField.getText();

                int numberOfTests;
                double delaySeconds;

                //  Validate # of tests
                try
                {
                    numberOfTests =  Integer.parseInt(testCountText);

                    if (numberOfTests <= 0)
                    {
                        statusLabel.setText("Number of tests must be greater than 0.");
                        return;
                    }
                }
                catch (NumberFormatException e)
                {
                    statusLabel.setText("Please enter a valid number of tests.");
                    return;
                }

                //  Validate delay
                try
                {
                    delaySeconds = Double.parseDouble(delayText);

                    if (delaySeconds < 0)
                    {
                        statusLabel.setText("Delay cannot be negative.");
                    }
                }
                catch (NumberFormatException e)
                {
                    statusLabel.setText("Please enter a valid delay.");
                    return;
                }

                //  Store all of the Ping Results
                ArrayList<PingResult> results = new ArrayList<>();

                statusLabel.setText("Testing...");

                //  Run requested # of tests
                for (int i = 0; i < numberOfTests; i++)
                {
                    PingResult result = src.ping(SelectedTarget);
                    results.add(result);

                    //  Delay between # of tests
                    if (i < numberOfTests - 1)
                    {
                        try
                        {
                            Thread.sleep((long)(delaySeconds * 1000));
                        }
                        catch (InterruptedException e)
                        {
                            Thread.currentThread().interrupt();
                            statusLabel.setText("Test Interrupted.");
                            return;
                        }
                    }
                }

                //  ----------  ANALYZE RESULTS (Jitter, packetLoss, etc.)  ---------- 
                int successfulTests = 0;
                int failedTests = 0;
                int successfulLatencyTests = 0;
                
                double totalLatency = 0;
                double minLatency = Double.MAX_VALUE;       //  Max value a double can store
                double maxLatency = 0;

                double totalJitter = 0;
                double previousLatency = 0;
                int jitterComparisons = 0;
                boolean hasPreviousLatency = false;

                for (PingResult result : results)
                {
                    if (result.success)
                    {
                        successfulTests++;

                        totalLatency += result.latency;
                        successfulLatencyTests++;

                        //  Max/min latencies
                        if (result.latency < minLatency)
                            minLatency = result.latency;
                        if (result.latency > maxLatency)
                            maxLatency = result.latency;

                        //  Jitter
                        if (!hasPreviousLatency)
                        {
                            previousLatency = result.latency;
                            hasPreviousLatency = true;
                        }
                        else
                        {
                            double diff = Math.abs(result.latency - previousLatency);
                            totalJitter += diff;
                            jitterComparisons++;

                            previousLatency = result.latency;
                        }
                    }
                    else
                    {
                        failedTests++;
                    }
                }

                //  ----------  CALCULATE FINAL STATISTICS  ----------
                double averageLatency = 0;
                double packetLoss = ((double) failedTests / results.size()) * 100;
                double jitter = 0;

                if (successfulLatencyTests > 0)     //  Have atleast 1 successful ping result?
                    averageLatency = totalLatency / successfulLatencyTests;     //  Only successful ping tests account for average latency
                if (jitterComparisons > 0)
                    jitter = totalJitter / jitterComparisons;

                //  ----------  UPDATE GUI (GRAPHICAL USER INTERFACE)  ----------

                statusLabel.setText("Testing Complete: " + successfulTests + "/" + numberOfTests + " tests successful.");       //  Display # of successful tests

                if (successfulLatencyTests > 0)     //  If atleast 1 successful ping test, then display:
                {
                    averageLatencyLabel.setText(String.format("Average Latency: %.2f ms", averageLatency));
                    minLatencyLabel.setText(String.format("Minimum Latency: %.2f ms", minLatency));
                    maxLatencyLabel.setText(String.format("Maximum Latency: %.2f ms", maxLatency));
                }
                else        //  If 0 Successful ping tests, then display N/A:
                {
                    averageLatencyLabel.setText("Average Latency: N/A");
                    minLatencyLabel.setText("Minimum Latency: N/A");
                    maxLatencyLabel.setText("Maximum Latency: N/A");
                }

                packetLossLabel.setText(String.format("Packet Loss: %.2f%%", packetLoss));      //  Display Packet Loss (%)

                if (jitterComparisons > 0)      //  Atleast 1 jitter comparison aka 2 ping results?
                    jitterLabel.setText(String.format("Jitter: %.2f ms", jitter));     //  Display jitter
                else        //  0 Jitter comparisons aka [0,1] ping results, so display N/A
                    jitterLabel.setText("Jitter: N/A");
            });

            //  Adds all visual UI components to panel
            panel.add(startButton);
            panel.add(Box.createVerticalStrut(10));
            panel.add(statusLabel);
            panel.add(Box.createVerticalStrut(20));

            //      ----------    Live Results Section    ----------
            JPanel resultsPanel = new JPanel(new GridLayout(0, 1, 5, 5));       //  Construct new JPanel object
            resultsPanel.setBorder(BorderFactory.createTitledBorder("LIVE RESULTS"));       //  Gives results section visible header and border

            //  Add all of the results to seperate panels
            resultsPanel.add(averageLatencyLabel);
            resultsPanel.add(minLatencyLabel);
            resultsPanel.add(maxLatencyLabel);
            resultsPanel.add(packetLossLabel);
            resultsPanel.add(jitterLabel);

            panel.add(resultsPanel);      //  Adds seperate panels to main panel
            window.add(panel);      //  Adds main panel/completed interface to window

            window.setSize(650, 500);       // Sets window starting size
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      //  Makes the program fully close when user exits window
            window.setLocationRelativeTo(null);     //  Opens program in center of the screen
            window.setVisible(true);        //  Make completed window visible
        });
    }
}
