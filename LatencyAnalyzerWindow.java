/*
    Aaryan Soni
    Began: 9/22, 6:30 PM
    Goal: New Java file to create the actual desktop application and GUI Interface for Wi-Fi Latency Analyzer
 */

import java.awt.Component;
import java.awt.FlowLayout;     //  Controls how components are arranged
import java.awt.Font;
import java.awt.GridLayout;
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
                "Cloudflare (1.1.1.1)",
                "Google (8.8.8.8)"
            };

            JComboBox<String> targetDropBox = new JComboBox<>(targets);     //  Creates a dropdown menu for users to choose test target

            targetPanel.add(targetLabel);
            targetPanel.add(targetDropBox);

            JPanel testCountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel testCountLabel = new JLabel("Number of tests:");
            JTextField testCountField = new JTextField("5", 5);     //  Text field begins with 5 as suggested # of tests the user should run

            targetPanel.add(testCountLabel);
            targetPanel.add(testCountField);
            
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
            
            //  Runs everytime user clicks button 'Start Test':
            startButton.addActionListener(event -> 
            {
                String SelectedTarget = (String) targetDropBox.getSelectedItem();       //  Reads item selected from dropdown menu
                //  Reads values currently entered into text fields:
                String numberOftests = testCountField.getText();
                String delaySeconds = delayField.getText();

                //  Displays what the app plans to test:
                statusLabel.setText(
                    "Status: Preparing " + numberOftests + " test(s) to " + SelectedTarget + " every " + delaySeconds + " second(s)" 
                );
            });

            //  Adds all visual UI components to panel
            panel.add(startButton);
            panel.add(Box.createVerticalStrut(10));
            panel.add(statusLabel);
            panel.add(Box.createVerticalStrut(20));

            //      ----------    Live Results Section    ----------
            JPanel resultsPanel = new JPanel(new GridLayout(0, 1, 5, 5));       //  Construct new JPanel object
            resultsPanel.setBorder(BorderFactory.createTitledBorder("LIVE RESULTS"));       //  Gives results section visible header and border

            //  Placeholder values (eg. -- ms) will be replaced by real ping data later after connecting backend to desktop APP
            JLabel averageLatencyLabel = new JLabel("Average Latency: -- ms");
            JLabel minLatencyLabel = new JLabel("Minimum Latency: -- ms ");
            JLabel maxLatencyLabel = new JLabel("Maximum Latency: -- ms");
            JLabel packetLossLabel = new JLabel("Packet Loss: -- %");
            JLabel jitterLabel = new JLabel("Jitter: -- ms");

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
