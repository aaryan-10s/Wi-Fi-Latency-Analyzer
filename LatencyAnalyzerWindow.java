/*
    Aaryan Soni
    Began: 9/22, 6:30 PM
    Goal: New Java file to create the actual desktop application and GUI Interface for Wi-Fi Latency Analyzer
 */

import java.awt.Color;
import java.awt.Component;     //  Controls how components are arranged
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;     //  Utilize array List tools
import java.util.ArrayList;       //  Gives access to Windows Swing components
import javax.swing.*;        //  Gives access to color tools for customizing the GUI

public class LatencyAnalyzerWindow
{
    public static void main(String[] args) 
    {
        //  Runs the interface safely on java's user interface thread
        SwingUtilities.invokeLater(() -> 
        {
            JFrame window = new JFrame("Wi-Fi Latency Analyzer");       //  Creates the main app Window and assigns a title

            //  Construct color objects to use for customizing the GUI
            Color backgroundColor = new Color (245, 247, 250);
            Color sectionColor = new Color(235, 235, 235);
            Color primaryColor = new Color(40, 100, 180);
            Color textColor = new Color(40, 40, 40);

            JPanel panel = new JPanel();        //  Creates panel to hold labels, dropdowns, etc.
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));        //  Places componenents top --> bottom
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            panel.setBackground(backgroundColor);       //  Main window now has a light grey background

            //      ------------ TITLE SECTION -------------
            
            JLabel title = new JLabel("Wi-Fi Latency Analyzer");        //  Creates main title shown in the app
            title.setFont(new Font("Arial", Font.BOLD, 24));
            title.setAlignmentX(Component.CENTER_ALIGNMENT);        //  Alignment
            title.setForeground(primaryColor);      //  Set text color to blue
            
            JLabel subtitle = new JLabel("Measures your network performance");      //  Creates subtitle shown in the app
            subtitle.setFont(new Font("Arial", Font.PLAIN, 14));
            subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);     
            subtitle.setForeground(textColor);      //  Changes text beneath title (subtitle) to dark grey
            
            panel.add(title);
            panel.add(Box.createVerticalStrut(20));
            panel.add(subtitle);

            //      ------------    TEST SETTINGS SECTION    ------------
            JPanel testSettingsPanel = new JPanel();        //  Construct a test settings panel to encapsulate the 3 smaller panels (targetPanel, testCountPanel, delayPanel)
            testSettingsPanel.setLayout(new BoxLayout(testSettingsPanel, BoxLayout.Y_AXIS));
            testSettingsPanel.setBackground(sectionColor);      //  Set panel background color
            testSettingsPanel.setBorder(BorderFactory.createLineBorder(new Color( 190, 200, 215)));     //  Construct border around panel

            JPanel targetPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel targetLabel = new JLabel("Choose a test target: ");      //  Explains what dropdown is used for

            //  Removing the default blue shade that covers the dropdown panel
            UIManager.put("ComboBox.selectionBackground", Color.WHITE);
            UIManager.put("ComboBox.selectionForeground", textColor);

            //  Dropdown: user chooses safe test targets (public DNS servers)
            String[] targets =                      
            {
                "Cloudflare (1.1.1.1)",
                "Google (8.8.8.8)",
                "Quad9 9.9.9.9",
                "OpenDNS (208.67.222.222)",
                "AdGuard (94.140.14.14)"
            };

            JComboBox<String> targetDropBox = new JComboBox<>(targets);     //  Creates a dropdown menu for users to choose test target
            targetDropBox.setBackground(Color.WHITE);       //  Set background of dropdown panel to white
            targetDropBox.setForeground(textColor);     //  Set text color of dropdown options to blue
            targetDropBox.setRenderer(new DefaultListCellRenderer()     //  Removing the default blue shade that covers the dropdown panel
            {
                @Override 
                public Component getListCellRendererComponent(
                    JList<?> list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus)
                {
                    JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    
                    label.setBackground(Color.WHITE);
                    label.setForeground(textColor);

                    return label;
                }
            });

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
            
            //      add seperate smaller panels to the test settings panel panel (variable = panel)
            testSettingsPanel.add(targetPanel);
            testSettingsPanel.add(testCountPanel);
            testSettingsPanel.add(delayPanel);
            
            testSettingsPanel.setMaximumSize(testSettingsPanel.getPreferredSize());     //  Adjust size of box to make panel more neat
            testSettingsPanel.add(Box.createVerticalStrut(10));
            panel.add(testSettingsPanel);       //  Add test settings panel to the main panel

            //      ----------    BUTTON AND STATUS SECTION    ---------- 

            JButton startButton = new JButton("▶ Start Test");        //  Button to run real Ping Tests
            startButton.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));      //  Change button text font to support Button icon
            startButton.setForeground(Color.WHITE);     //  Change text to white
            startButton.setAlignmentX(Component.CENTER_ALIGNMENT);      //  Button alignment on the panel
            startButton.setBackground(primaryColor);     //  Set background color of button panel to grey
            startButton.setFocusPainted(false);
            
            JLabel statusLabel = new JLabel("Ready to test");       //  Displays status
            statusLabel.setFont(new Font("Arial", Font.PLAIN, 13));
            statusLabel.setForeground(textColor);       //  Set text color to blue
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
                String targetIP = SelectedTarget.substring(SelectedTarget.indexOf("(") + 1, SelectedTarget.indexOf(")"));       //  use subString() method to only get IP address of public dns servers from dropdown list

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

                    if (delaySeconds <= 0)
                    {
                        statusLabel.setText("Delay must be greater than 0.");
                        return;
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
                    PingResult result = src.ping(targetIP);
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
            panel.add(Box.createVerticalStrut(10));
            panel.add(startButton);
            
            panel.add(Box.createVerticalStrut(20));
            panel.add(statusLabel);
            
            //      ----------    Live Results Section    ----------
            JLabel resultsTitle = new JLabel("LIVE RESULTS");       //  Construct object to store Live Results title
            resultsTitle.setFont(new Font("Arial", Font.BOLD, 18));
            resultsTitle.setForeground(primaryColor);
            resultsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel resultsPanel = new JPanel(new GridLayout(0, 1, 5, 5));       //  Construct new JPanel object
            resultsPanel.setBorder(BorderFactory.createLineBorder(new Color(190, 200, 215)));       //  Blue gray border color
            resultsPanel.setBackground(sectionColor);
            resultsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            //  Add all of the results to seperate panels + customize results text
            resultsPanel.add(averageLatencyLabel);
            averageLatencyLabel.setFont(new Font("Arial", Font.BOLD, 14));
            
            resultsPanel.add(minLatencyLabel);
            minLatencyLabel.setFont(new Font("Arial", Font.BOLD, 14));
            
            resultsPanel.add(maxLatencyLabel);
            maxLatencyLabel.setFont(new Font("Arial", Font.BOLD, 14));
            
            resultsPanel.add(packetLossLabel);
            packetLossLabel.setFont(new Font("Arial", Font.BOLD, 14));
            
            resultsPanel.add(jitterLabel);
            jitterLabel.setFont(new Font("Arial", Font.BOLD, 14));
            
            panel.add(resultsTitle);
            panel.add(Box.createVerticalStrut(5));
            panel.add(resultsPanel);      //  Adds seperate panels to main panel
            
            window.add(panel);      //  Adds main panel/completed interface to window

            window.setSize(650, 500);       // Sets window starting size
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      //  Makes the program fully close when user exits window
            window.setLocationRelativeTo(null);     //  Opens program in center of the screen
            window.setVisible(true);        //  Make completed window visible
        });
    }
}
//  End of LatencyAnalyzerWindow file
//  End of GUI/Desktop APP code