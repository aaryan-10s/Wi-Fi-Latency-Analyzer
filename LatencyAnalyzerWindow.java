/*
    Aaryan Soni
    Began: 9/22, 6:30 PM
    Goal: New Java file to create the actual desktop application and GUI Interface for Wi-Fi Latency Analyzer
 */

import java.awt.Color;      //  Gives access to color tools for customizing the GUI
import java.awt.Component;     //  Controls how components are arranged
import java.awt.Dimension;      //  Adjusting window dimensions (size), W X H (width and height)
import java.awt.FlowLayout;     //  Layout for labels and text
import java.awt.Font;       //  Font for customizing text
import java.awt.GridLayout;     //  Utilize array List tools    
import java.util.ArrayList;       //  Gives access to Windows Swing components
import javax.swing.*;       //  Java SWING - set of GUI tools in java to create desktop APPS
import com.formdev.flatlaf.FlatLightLaf;        //  FlatLaf - swing public API to modernize my buttons/logos in java swing application
import javax.lang.model.util.ElementScanner14;

public class LatencyAnalyzerWindow
{
    
    //  ---------- COLORS ---------- (private static final means more efficient and consistency (constant))
    private static final Color BACKGROUND = new Color(245, 247, 250);       //  light gray-blue
    private static final Color CARD = Color.WHITE;      //  white
    private static final Color BORDER = new Color(220, 225, 232);       //  soft, light-gray blue

    private static final Color PRIMARY = new Color (37, 99, 235);       //  modern royal blue
    private static final Color PRIMARY_DARK = new Color(29, 78, 216);       //  deeper, darker blue

    private static final Color TEXT = new Color(31, 41, 55);        //  dark charcoal/gray
    private static final Color MUTED = new Color (107, 114, 128);     //  neutral gray
    
    private static final Color SUCCESS = new Color(22, 163, 74);        //  leafy green
    private static final Color WARNING = new Color(217, 119, 6);        //  warm orange
    private static final Color DANGER = new Color(220, 38, 38);     //  bright red
    
    public static void main(String[] args) 
    {
        try
        {
            FlatLightLaf.setup();
        }
        catch (Exception e)
        {
            System.out.println("Could not look FlatLaf. Loading default java swing...");
        }

        //  Runs the interface safely on java's user interface thread
        SwingUtilities.invokeLater(() -> 
        {
            
            
            JFrame window = new JFrame("Wi-Fi Latency Analyzer");       //  Creates the main app Window and assigns a title
            
            window.setSize(750, 600);       // Sets window starting size
            window.setMinimumSize(new Dimension(650, 500));
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      //  Makes the program fully close when user exits window
            window.setLocationRelativeTo(null);     //  Opens program in center of the screen
            window.setVisible(true);        //  Make completed window visible
            
            //  MAIN PANEL
            JPanel mainPanel = new JPanel();        //  Creates panel to hold labels, dropdowns, etc.
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));        //  Places componenents top --> bottom
            mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));
            mainPanel.setBackground(BACKGROUND);       //  Main window now has a light grey background

            //      ------------ TITLE SECTION -------------
            JLabel title = new JLabel("Wi-Fi Latency Analyzer");        //  Creates main title shown in the app
            title.setFont(new Font("Segoe UI", Font.BOLD, 30));
            title.setAlignmentX(Component.LEFT_ALIGNMENT);        //  Alignment
            title.setForeground(TEXT);      //  Set text color to blue
            
            JLabel subtitle = new JLabel("Measure latency, packet loss, and jitter to understand connection stability and response speeds.");      //  Creates subtitle shown in the app
            subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);     
            subtitle.setForeground(MUTED);      //  Changes text beneath title (subtitle) to dark grey
            
            mainPanel.add(title);
            mainPanel.add(Box.createVerticalStrut(5));
            mainPanel.add(subtitle);
            mainPanel.add(Box.createVerticalStrut(25));

            //      ------------    TEST SETTINGS SECTION    ------------

            //  ------------  TARGET  ------------    
            JPanel testSettingsPanel = new JPanel();        //  Construct a test settings panel to encapsulate the 3 smaller panels (targetPanel, testCountPanel, delayPanel)
            testSettingsPanel.setLayout(new BoxLayout(testSettingsPanel, BoxLayout.Y_AXIS));
            testSettingsPanel.setBackground(CARD);      //  Set panel background color
            testSettingsPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(BORDER), BorderFactory.createEmptyBorder(18, 20, 18, 20)));     //  Construct border around panel
            testSettingsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            JLabel settingsTitle = new JLabel("Test Settings");
            settingsTitle.setFont(new Font("Segue UI", Font.BOLD, 18));
            settingsTitle.setForeground(TEXT);

            testSettingsPanel.add(settingsTitle);
            testSettingsPanel.add(Box.createVerticalStrut(15));

            JPanel targetPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));     //  panel for test targets
            targetPanel.setBackground(CARD);

            JLabel targetLabel = new JLabel("Test target: ");      //  Explains what dropdown is used for
            targetLabel.setFont(new Font("Segue UI", Font.BOLD, 13));
            targetLabel.setForeground(TEXT);

            //  Dropdown: user chooses safe test targets (public DNS servers)
            String[] targets =                      
            {
                "Cloudflare (1.1.1.1)",
                "Google (8.8.8.8)",
                "Quad9 (9.9.9.9)",
                "OpenDNS (208.67.222.222)",
                "AdGuard (94.140.14.14)"
            };

            JComboBox<String> targetDropBox = new JComboBox<>(targets);     //  Creates a dropdown menu for users to choose test target
            targetDropBox.setPreferredSize(new Dimension(230, 32));     //  dimensions (L x W)
            targetDropBox.setBackground(Color.WHITE);       //  Set background of dropdown panel to white
            targetDropBox.setForeground(TEXT);     //  Set text color of dropdown options to blue
            
            targetPanel.add(targetLabel);
            targetPanel.add(targetDropBox);
            testSettingsPanel.add(targetPanel);     //  add smaller panels to main settings panel

            //  ------------  TEST COUNT  ------------ 
            JPanel testCountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
            testCountPanel.setBackground(CARD);

            JLabel testCountLabel = new JLabel("Number of tests");
            testCountLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
            testCountLabel.setForeground(TEXT);
            
            JTextField testCountField = new JTextField("5");     //  Text field begins with 5 as suggested # of tests the user should run
            testCountField.setPreferredSize(new Dimension(80, 32));
            testCountField.setMinimumSize(new Dimension(80, 32));
            testCountField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));        //  box for user input (add missing side of box)
            testCountField.setOpaque(true);

            testCountPanel.add(testCountLabel);
            testCountPanel.add(testCountField);
            testSettingsPanel.add(testCountPanel);      //  add smaller panels to main settings panel

            //  ------------  DELAY  ------------
            JPanel delayPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
            delayPanel.setBackground(CARD);

            JLabel delayLabel = new JLabel("Delay between tests");
            delayLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
            delayLabel.setForeground(TEXT);

            JTextField delayField = new JTextField("1");     //  Text field begins with 1 as the suggestedd text delay
            delayField.setPreferredSize(new Dimension(80, 32));     
            delayField.setMinimumSize(new Dimension(80, 32));
            delayField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));        //  box for user input (add missing side of box)
            delayField.setOpaque(true);
           
            JLabel secondsLabel = new JLabel("seconds");
            secondsLabel.setForeground(MUTED);

            delayPanel.add(delayLabel);     //  add labels to delay panel
            delayPanel.add(delayField);
            delayPanel.add(secondsLabel);
            testSettingsPanel.add(delayPanel);      //  add smaller panels to main settings panel
            
            testSettingsPanel.setMaximumSize(testSettingsPanel.getPreferredSize());     //  Adjust size of box to make panel more neat
            mainPanel.add(testSettingsPanel);       //  Add test settings panel to the main panel
            mainPanel.add(Box.createVerticalStrut(20));
            
            //      ----------    BUTTON AND STATUS SECTION    ---------- 

            //  ------------  TEST BUTTON  ------------
            JButton startButton = new JButton("▶ Start Test");        //  Button to run real Ping Tests
            startButton.setFont(new Font("Segoe UI", Font.BOLD, 15));      //  Change button text font to support Button icon
            startButton.setForeground(Color.WHITE);     //  Change text to white
            startButton.setBackground(PRIMARY);     //  Set background color of button panel to roya blue
            startButton.setFocusPainted(false);
            startButton.setBorderPainted(false);
            startButton.setPreferredSize(new Dimension(180, 42));
            startButton.setMaximumSize(new Dimension(180, 42));
            startButton.setAlignmentX(Component.LEFT_ALIGNMENT);      //  Button alignment on the panel   
            
            mainPanel.add(startButton);
            mainPanel.add(Box.createVerticalStrut(10));

            //  ------------  STATUS  ------------
            JLabel statusLabel = new JLabel("Ready to test");       //  Displays status
            statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            statusLabel.setForeground(MUTED);       //  Set text color to gray
            statusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            mainPanel.add(statusLabel);
            mainPanel.add(Box.createVerticalStrut(20));
            
            //  ------------  RESULTS DASHBOARD  ------------
            //  Placeholder values (eg. -- ms) will be replaced by real ping data after test completion
            JLabel averageValue = new JLabel("--");
            JLabel minValue = new JLabel("--");
            JLabel maxValue = new JLabel("--");
            JLabel jitterValue = new JLabel("--");
            JLabel packetLossValue = new JLabel("--");
            
            //  short clarification of statistics names
            JLabel averageDetail = new JLabel("Average Response time");
            JLabel minDetail = new JLabel("Fastest successful response");
            JLabel maxDetail = new JLabel("Slowest successful response time");
            JLabel jitterDetail = new JLabel("Average change between tests");
            JLabel packetLossDetail = new JLabel("Failed tests / total tests");
            JLabel healthValue = new JLabel("READY");       //  status
            JLabel healthDescription = new JLabel("Run a test to evaluate connection stability");

            JPanel resultsGrid = new JPanel(new GridLayout(2, 3, 12, 12));      //  new Panel (grids) to display results
            resultsGrid.setBackground(BACKGROUND);
            resultsGrid.setAlignmentX(Component.LEFT_ALIGNMENT);
            resultsGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 220));

            //  add labels/metrics to results panel to display results in boxes
            resultsGrid.add(createMetricCard("Average Latency", averageValue, averageDetail));
            resultsGrid.add(createMetricCard("Minimum Latency", minValue, minDetail));
            resultsGrid.add(createMetricCard("Maximum Latency", maxValue, maxDetail));
            resultsGrid.add(createMetricCard("Jitter", jitterValue, jitterDetail));
            resultsGrid.add(createMetricCard("Packet Loss", packetLossValue, packetLossDetail));

            JPanel emptySpace = new JPanel();       //  empty 6th box to account for empty space
            emptySpace.setBackground(BACKGROUND);
            resultsGrid.add(emptySpace);

            mainPanel.add(Box.createVerticalStrut(18));
            mainPanel.add(resultsGrid);     //  add results panel to main panel

            //  connection status panel (health)
            JPanel healthCard = new JPanel();
            healthCard.setLayout(new BoxLayout(healthCard, BoxLayout.Y_AXIS));
            healthCard.setBackground(CARD);
            healthCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(16, 20, 16, 20)
                )
            );
            healthCard.setAlignmentX(Component.LEFT_ALIGNMENT);
            healthCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));

            JLabel healthTitle = new JLabel("Connection Status");       //  title of connection health/status
            healthTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
            healthTitle.setForeground(TEXT);

            healthValue.setFont(new Font("Segue UI", Font.BOLD, 24));       //  value
            healthValue.setForeground(PRIMARY);

            healthDescription.setFont(new Font("Segue UI", Font.PLAIN, 14));        //  short description of connection health/status
            healthDescription.setForeground(PRIMARY);

            //  add titles/labels to healthcard panel
            healthCard.add(healthTitle);
            healthCard.add(Box.createVerticalStrut(4));
            healthCard.add(healthValue);
            healthCard.add(Box.createVerticalStrut(2));
            healthCard.add(healthDescription);

            mainPanel.add(Box.createVerticalStrut(14));     //  add healthcard panel to main panel
            mainPanel.add(healthCard);
            
            //  Runs everytime user clicks button 'Start Test' (IMPORTANT as it connects src.java functionality with the UI)
            startButton.addActionListener(event -> 
            {
                String selectedTarget = (String) targetDropBox.getSelectedItem();       //  Reads item selected from dropdown menu
                String targetIP = selectedTarget.substring(selectedTarget.indexOf("(") + 1, selectedTarget.indexOf(")"));       //  use subString() method to only get IP address of public dns servers from dropdown list
                targetDropBox.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));        //  create a box for where user inputs number
                targetDropBox.setOpaque(true);

                //  Read # of tests and delay from text fields (test settings)
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
                
                //  disable button while testing
                startButton.setEnabled(false);
                startButton.setText("Testing...");
                statusLabel.setText("Running test 1 of " + numberOfTests + "...");

                //  Run requested # of tests
                for (int i = 0; i < numberOfTests; i++)
                {
                    statusLabel.setText("Running test " + (i + 1) + " of " + numberOfTests + "...");        //  show current test #
                    
                    statusLabel.paintImmediately(statusLabel.getVisibleRect());     //  update live

                    PingResult result = src.ping(targetIP);     //  run ping connection
                    results.add(result);

                    if (i < numberOfTests - 1)      //  Delay between # of tests
                    {
                        try
                        {
                            Thread.sleep((long)(delaySeconds * 1000));
                        }
                        catch (InterruptedException e)
                        {
                            Thread.currentThread().interrupt();
                            statusLabel.setText("Test Interrupted.");
                            
                            startButton.setEnabled(true);
                            startButton.setText("▶ Start Test");

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

                        //  min/max latencies
                        if (result.latency < minLatency)
                            minLatency = result.latency;
                        if (result.latency > maxLatency)
                            maxLatency = result.latency;

                        //  Jitter
                        if (!hasPreviousLatency)        //  hasPreviousLatency = true
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
                if (jitterComparisons > 0)      //  have atleast 2 test results?
                    jitter = totalJitter / jitterComparisons;       //  average jitter

                //  ----------  UPDATE GUI (GRAPHICAL USER INTERFACE)  ----------
                statusLabel.setText("Testing Complete: " + successfulTests + "/" + numberOfTests + " tests successful.");       //  Display # of successful tests

                if (successfulLatencyTests > 0)     //  If atleast 1 successful ping test, then display:
                {
                    averageValue.setText(String.format("%.2f ms", averageLatency));
                    minValue.setText(String.format("%.2f ms", minLatency));
                    maxValue.setText(String.format("%.2f ms", maxLatency));
                }
                else        //  If 0 Successful ping tests, then display N/A:
                {
                    averageValue.setText("N/A");
                    minValue.setText("N/A");
                    maxValue.setText("N/A");
                }

                packetLossValue.setText(String.format("%.2f%%", packetLoss));      //  Display Packet Loss (%)
                packetLossDetail.setText(failedTests + " failed / " + numberOfTests + " tests");        //  display # of failed tests

                if (jitterComparisons > 0)      //  Atleast 1 jitter comparison aka 2 ping results?
                    jitterValue.setText(String.format("%.2f ms", jitter));     //  Display jitter
                else        //  0 Jitter comparisons aka [0,1] ping results, so display N/A
                    jitterValue.setText("N/A");
            
            // ============   CONNECTION STATUS ( outcome)   ============ **(very important - helps user understand the latency statistics)
            //  ** 3 parameters: ping, jitter, packet loss
            //  *** instability determined by jitter/packet loss
            //  *** response delay/speed determined from average latency

            //  1st outcome: all tests failed
            if(successfulLatencyTests == 0)     //  no successful ping results/responses
            {
                healthValue.setText("NO RESPONSE");
                healthValue.setForeground(DANGER);      //  red == not good
                healthDescription.setText("All of the ping tests failed.");     //  short description
            }

            //  2nd outcome: very unstable & slow
            else if((packetLoss > 10 || jitter > 30) && averageLatency > 150)
            {
                healthValue.setText("HIGHLY UNSTABLE & SLOW");
                healthValue.setForeground(DANGER);
                healthDescription.setText("Either packet loss or jitter was high, and latency was high.");
            }
            
            //  3rd outcome: very unstable
            else if(packetLoss > 10 || jitter > 30)
            {
                healthValue.setText("HIGHLY UNSTABLE");
                healthValue.setForeground(DANGER);
                healthDescription.setText("The test showed high packet loss or high jitter.");
            }
            
            //  4th outcome: very slow
            else if(averageLatency > 150)       //  ping > 150ms
            {
                healthValue.setText("VERY SLOW");
                healthValue.setForeground(DANGER);
                healthDescription.setText("The test showed high latency.");
            }
            
            //  5th outcome: some instability and little slow
            else if((packetLoss > 0 || jitter > 15) && averageLatency > 100)
            {
                healthValue.setText("SOME INSTABILITY & SLOW SPEEDS");
                healthValue.setForeground(WARNING);      //  orange: mid connection
                healthDescription.setText("The test showed some jitter or packet loss, and slightly high latency.");
            }
            
            //  6th outcome: some instability
            else if(packetLoss > 0 || jitter > 15)
            {
                healthValue.setText("SOME INSTABILITY");
                healthValue.setForeground(WARNING);
                healthDescription.setText("The test showed some jitter or packet loss.");
            }

            //  7th outcome: little slow
            else if(averageLatency > 100)
            {
                healthValue.setText("SLOW SPEEDS");
                healthValue.setForeground(WARNING);
                healthDescription.setText("The test showed slightly high latency.");
            }
            
            //  8th/final outcome: good test results (stable & good speeds)
            else        //  packet loss == 0, latency < 100, jitter < 15
            {
                healthValue.setText("STABLE");
                healthValue.setForeground(SUCCESS);     //  green: healthy
                healthDescription.setText("The test showed low latency, low jitter, and no packet loss.");
            }

            //  reenable button after test is complete
            startButton.setEnabled(true);
            startButton.setText("▶ Start Test");
            });

            //  =======   ADD VISUAL COMPONENTS (UI) TO MAIN WINDOW    =======   
            window.add(mainPanel);      //  Adds main panel/completed interface to window
            window.setVisible(true);        //  make complete window visible

        });     //  End of swingutil invoke later

    }       //  End of main method



    //  =======   METRIC CARD METHOD    =======
    private static JPanel createMetricCard(String title, JLabel valueLabel, JLabel detailLabel)
    {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(CARD);

        card.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(14, 16, 14, 16)
            )
    );

    JLabel titleLabel = new JLabel(title);      //  Metric title
    titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
    titleLabel.setForeground(MUTED);

    valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));        //  large metric value
    valueLabel.setForeground(TEXT);

    detailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));      //  small description
    detailLabel.setForeground(MUTED);

    card.add(titleLabel);       //  add all components into metric card
    card.add(Box.createVerticalStrut(5));
    card.add(valueLabel);
    card.add(Box.createVerticalStrut(2));
    card.add(detailLabel);

    return card;
    }
    
}       //  End of LatencyAnalyzerWindow class

//  End of GUI/Desktop APP code

