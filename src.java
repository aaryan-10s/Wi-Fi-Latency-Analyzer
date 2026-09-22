/*
    Aaryan Soni
    Wifi-Latency Analyzer Project
    Inspired by Gaming and unstable connections!
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;     // Reads text from ping process
import java.time.LocalDateTime; // Import the BufferedReader class - convert process input stream into readable text
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner; // Import the InputStreamReader class to read output from the ping command

public class src 

{
    public static void main(String[] args) // Main method - entry point of the program
    {
        System.out.println("Hello, user! Welcome to the Java program.");
        Scanner input = new Scanner(System.in); // Create a scanner object to read input from the user

        System.out.println();
        
        System.out.print("Please enter your name: ");
        String name = input.nextLine(); // Read the user's name from the input
        
        System.out.println("Hello, " + name + "!");

        System.out.println(); // Print a blank line for better readability

        System.out.println("Please enter your age (as a number): ");    
        while (!input.hasNextInt()) // Check if the input is not an integer
        {
            System.out.println("Invalid input. Please enter a number for your age: "); // Prompt the user to enter a valid number
            input.nextLine(); // Consume the invalid input
        }
        int age = input.nextInt(); // Read the user's age from the input
        input.nextLine(); // Consume the newline character left by nextInt()
        while (age < 0) // Check if the age is negative
        {
            System.out.println("Age cannot be negative. Please enter a valid age: "); // Prompt the user to enter a valid age
            while (!input.hasNextInt()) // Check if the input is not an integer 
            {
                System.out.println("Age must be a number. Please enter a valid age: "); // Prompt the user to enter a valid number
                input.nextLine(); // Consume the invalid input
            }

            age = input.nextInt(); // Read the user's age from the input
            input.nextLine(); // Consume the newline character left by nextInt()

        }
       
        System.out.println();
        System.out.println("You are " + age + " years old.");

        System.out.println(); // Print a blank line for better readability

        while(true)
        {
            System.out.println("Have you used this program before? (yes/no): ");
            String response = input.nextLine(); // Read the user's response
            
            System.out.println();
            if (response.equalsIgnoreCase("Yes"))
            {
                System.out.println("Thank you for using this program!");
                break;
            }
            else if (response.equalsIgnoreCase("no"))
            {
                System.out.println("Welcome to the program, " + name + "!");
                break;
            }
            else
            {
                System.out.println("\"" + response + "\" not recognized. Please type 'yes' or 'no'.");
            }
            System.out.println();
        }
        
        while(true)
        {
            System.out.println();
            System.out.println("Please type 'confirm' down below to proceed or 'exit' to leave the program.");

            String confirmation1 = input.nextLine(); // Read the user's confirmation response (is this correct)?
            if (confirmation1.equalsIgnoreCase("confirm")) // Check if the user confirmed
            {
                System.out.println();
                System.out.println("You chose to proceed. Are you sure that you would like to proceed? Please type 'yes' again to proceed or 'no' to exit the program.");   // Double (2-Step) verification
                String confirmation2 = input.nextLine();
                if (confirmation2.equalsIgnoreCase("yes"))
                {
                    System.out.println();
                    System.out.println("Proceeding...");
                    break;
                }
                else if (confirmation2.equalsIgnoreCase("no"))
                {
                    System.out.println();
                    System.out.println("Thank you for using this program. Goodbye!");
                    input.close();
                    System.exit(0);

                }
                else
                {
                    System.out.println();
                    System.out.println("Invalid input. Please try again.");
                } 

            }
            else if (confirmation1.equalsIgnoreCase("exit"))
            {
                System.out.println();
                System.out.println("You chose to exit. Goodbye, " + name + "."); // Inform the user that they did not confirm
                input.close();
                System.exit(0);
            }
            else
            {
                System.out.println();
                System.out.println("Failed to recognize user input.");
            }
        }
        
        
        System.out.println();
        boolean surveyConsent = false;

        while(true) //   Prompt user to answer optional survey questions
        {
            System.out.println("Do you consent to answering a few survey questions?. Your responses help improve the program and provide a better user experience. Please type 'yes' to proceed or 'no' to exit the survey.");
            String consent = input.nextLine(); // Read the user's consent response
            
            if (consent.equalsIgnoreCase("yes"))    // Check if the user consented
            {
                System.out.println();
                System.out.println("Thank you for consenting to the survey! We appreciate your time and feedback.");
                surveyConsent = true;
                break;
            }
            else if (consent.equalsIgnoreCase("no"))
            {
                System.out.println();
                System.out.println("You did not consent to the survey.");
                System.out.println("Would you still like to proceed to the Wi-Fi Latency Analyzer program? (type 'yes' or 'no')?"); // Ask user if they would like to proceed to the program

                String consent1 = input.nextLine();
                if (consent1.equalsIgnoreCase("yes"))   //  Check if user wants to proceed to Wifi Latency Analyzer
                {
                    System.out.println();
                    System.out.println("Proceeding to program...");                            
                    break;
                }
                else if (consent1.equalsIgnoreCase("no"))                    
                {
                    System.out.println();
                    System.out.println("You chose not to proceed. Goodbye!");
                    input.close();
                    System.exit(0);
                }
                else  
                {
                    System.out.println();
                    System.out.println("Invalid input. Please type 'yes' to proceed or 'no' to exit the program.");
                }

            }
            else
            {
                System.out.println();
                System.out.println("Invalid input. Please type 'yes' to proceed or 'no' to exit the survey."); // Prompt the user to enter a valid response
                System.out.println();
            }
        } 

        // Day 2
        
        System.out.println(); // Print a blank line for better readability
        
        if (surveyConsent)
            {

                System.out.println("Please answer the following survey questions with accuracy and honesty. Please type complete sentences to answer the following questions.");

                System.out.println(); // Print a blank line for better readability

                System.out.println("1. How often do you use this program?"); // Ask the first survey question
                String answer1 = input.nextLine();

                System.out.println(); // Print a blank line for better readability

                System.out.println("2. Have you ever experienced any issues with internet speed or latency in your day to day life? If so, please describe the issues you have faced."); // Ask the second survey question
                String answer2 = input.nextLine();

                System.out.println(); // Print a blank line for better readability

                System.out.println("3. How important is internet speed and latency to you in your daily life? Please explain your answer.");
                String answer3 = input.nextLine();

                System.out.println(); // Print a blank line for better readability
            
                System.out.println("4. Have you ever used any other programs or tools to analyze your internet speed or latency? If so, please describe your experience with those programs or tools.");
                String answer4 = input.nextLine();
                
                System.out.println(); // Print a blank line for better readability

                System.out.println("5. Lastly, which aspects of your life, like work, gaming, or video calling, are most affected by your internet?");
                String answer5 = input.nextLine();

                System.out.println(); // Print a blank line for better readability

                System.out.println("Thank you for completing the survey! Your responses have been recorded and are privately stored. We appreciate your time and feedback.");
                
            }
        // Day 3

        System.out.println(); // Print a blank line for better readability

        System.out.println("Enter a hostname or IP address to test (eg., cloudflare.com, 1.1.1.1, google.com, 8.8.8.8 etc.)");
        String websiteInput = input.nextLine(); // Read the user's website/IP address input
        System.out.println("Confirming: " + websiteInput); // Print the user's website/IP address input;
        
        System.out.println(); // Print a blank line for better readability
        
        System.out.println("How many ping tests would you like to run?");
        
        int numberOfTests;  //  How many tests being performed? *(whole #)
        
        while(!input.hasNextInt())
        {
            System.out.println("Invalid input. Please enter a whole number.");

            input.nextLine();
        }
        numberOfTests = input.nextInt();
        input.nextLine();
 
        while(numberOfTests <= 0)   //  Number of Pings being ran cannot be <0
        {
            System.out.println();
            System.out.println("Number of tests must be greater than 0.");
            System.out.println("Please enter the number of tests again: ");
            while (!input.hasNextInt())
            {
                System.out.println("Invalid input. Please enter a whole number.");
                input.nextLine();
            }
            numberOfTests = input.nextInt();
            input.nextLine();
        }

        //  Create an arrayList to display multiple PingResults
        ArrayList<PingResult> results = new ArrayList<>();
        
        System.out.println();

        System.out.println("Beginning " + numberOfTests + " ping tests to " + websiteInput + "...");
        for (int i = 0; i < numberOfTests; i++)
        {
            System.out.println();
            System.out.println("===== TEST " + (i + 1) + " OF " + numberOfTests + " =====");
            PingResult result = ping(websiteInput);
            System.out.println();       //  Spacing

            //  Store this pingResult into Array
            results.add(result);
            System.out.println("Results successfully stored: " + results.size());
        }
        
        // ------- DISPLAY ALL STORED RESULTS -------
        System.out.println();
        System.out.println("===========================");
        System.out.println("     ALL TEST RESULTS");
        System.out.println("===========================");

        int successfulLatencyTests = 0;
        
        // ------- DISPLAY ALL PING RESULTS ------- 
        displayResults(results, numberOfTests);

        System.out.println();
       
        System.out.println("Total results stored: " + results.size());
        System.out.println("Analyzed Ping for " + numberOfTests + " tests" + " at target: " + websiteInput);
        System.out.println(); // Print a blank line for better readability
        
        //  ===================================================
        //  ANALYZE THE STORED RESULTS
        //  ===================================================

         // ------- ANALYZE PING RESULTS ------- 
    
        //  STEP 1: Count # of successful and failed tests
        
        double totalLatency = 0;

        int successfulTests = 0;
        int failedTests = 0;

        double minLatency = Double.MAX_VALUE;
        double maxLatency = 0;

        double totalJitter = 0;
        double previousLatency = 0;
        int jitterComparisons = 0;
        boolean haspreviousLatency = false;

        for (PingResult result: results)
        {
            if (result.success)     //  Use if/else to meet conditions and display max/min values on terminal
            {
                successfulTests++;      //  Add succcessfulTests if program makes a successful connection
                
                totalLatency += result.latency;
                successfulLatencyTests++;
                
                if (result.latency < minLatency)
                {
                    minLatency = result.latency;
                }
                if (result.latency > maxLatency)
                {
                    maxLatency = result.latency;
                }
                if (!haspreviousLatency)        //  If no previous latency (1st ping result)
                {
                previousLatency = result.latency;   //  Convert first ping result into previous latency
                haspreviousLatency = true;          //  Now for every ping result after, there will be a previous latency
                }
                else
                {
                double diff = Math.abs(result.latency - previousLatency);
                
                totalJitter += diff;
                jitterComparisons++;
                previousLatency = result.latency;
                }
            }
            
            else    //      If conditions not met (min or max), display failure
            {
                failedTests++;
            }
        }
        System.out.println("Successful Tests: " + successfulTests);
        System.out.println("Failed tests: " + failedTests);
        
        //  STEP 2: Calculate Packet Loss (%)

        double packetLoss = ((double) failedTests / results.size()) * 100;
        
        //  STEP 3: Calculate Average Latency
        
        double averageLatency = 0;

        if (successfulLatencyTests > 0)
        {
            averageLatency = (totalLatency / successfulLatencyTests);
        }
        //  STEP 4: Calculate Jitter
        
        double jitter = 0;
        if (jitterComparisons > 0)      //  Do we have atleast 2 ping results aka 1 comparison?
        {
            jitter = totalJitter / jitterComparisons;       //  jitter (aka average jitter)
        }
        
        System.out.println("Packet Loss: " + packetLoss + "%");
        if (jitterComparisons > 0)      //  If we have atleast 2 ping results aka 1 comparison, then display Jitter
            System.out.printf("Jitter: %.2f ms%n", jitter);
        else                            //  If 1 or 0 ping results, aka 0 comparisons, then Jitter = N/A
            System.out.println("Jitter: N/A");
        System.out.println("Average Latency: " + averageLatency + " ms");
        System.out.println("Minimum Latency: " + minLatency + " ms");
        System.out.println("Maximum Latency: " + maxLatency + " ms");
        
        System.out.println();

        //  ===================================================
        //  - FINAL SUMMARY / DISPLAYING ANALYSIS OF TESTS -
        //  ===================================================

        System.out.println("================================");
        System.out.println("        PING TEST SUMMARY");
        System.out.println("================================");
       
        System.out.println();

        System.out.println("Target: " + websiteInput);
        System.out.println("Tests performed: " + results.size());
        System.out.println("Successful Tests: " + successfulTests);
        System.out.println("Failed Tests: " + failedTests);
        System.out.printf("Packet Loss: %.2f%%%n", packetLoss);     //  Format/round packetLoss to 2 decimal places
        if (jitterComparisons > 0)
            System.out.printf("Jitter: %.2f ms%n", jitter);
        else
            System.out.println("Jitter: N/A");
        if ((successfulLatencyTests > 0))       //  Basically means: if we have tests that ran, then only can we display these
        {
            System.out.printf("Average Latency: %.2f ms%n", averageLatency);            //  Display avg Latency
            System.out.printf("Mininum Latency: %.2f ms%n", minLatency);    //  Display min Latency
            System.out.printf("Maximum Latency: %.2f ms%n", maxLatency);    //  Display max Latency
        }
        else        //  Display N/A to indicate failed tests
        {
            System.out.println("Average Latency: N/A");
            System.out.println("Minimum Latency: N/A");
            System.out.println("Maximum Latency: N/A");
        }
        System.out.println("================================");
    }

    // ------- DISPLAY ALL PING RESULTS ------- 
    private static void displayResults(ArrayList<PingResult> results, int numberOfTests)
    {
        int i = 0;
        
        for (PingResult result : results)
        {
            System.out.println();
            System.out.println("===== TEST " + (i + 1) + " OF " + numberOfTests + " =====");
            System.out.println();

            System.out.println("Target: " + result.target);
            
            if (result.success)         //  If ping connection = success
            {
                System.out.println("Latency: " + result.latency + " ms");
            }
            else        //  If ping connection = failure
            {
                System.out.println("Latency: N/A");        //  **Very important to display N/A and NOT 0 because 0 says 
            }                                                 //  we got a measurement, but failed connection == N/A (no measurement)
            System.out.println("Success: " + result.success);
            System.out.println("Timestamp: " + result.timestamp);
           
            System.out.println("-----------------------");

            i++;
        }
    }

    //  Ping METHOD — ProcessBuilder goes HERE (runs operating system's ping Command here):
    //  Runs a real ping, determines success, extracts latency,
    //  and returns results in a PingResult object

    private static PingResult ping(String target) //  Run a real ping and return the result
    {
        ProcessBuilder processBuilder = new ProcessBuilder(
            "ping",
            "-n", 
            "1", // Send only one ping packet
            target
        );

        boolean success;

        try
        {
            Process process = processBuilder.start(); // Start the ping process
            
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            );

            double latency = 0;

            String line;
            while ((line = reader.readLine()) != null) // Read the output of the ping command
            {
                System.out.println(line); // Print each line of the ping output
                if (line.toLowerCase().contains("time="))
                {
                    int timeIndex = (line.toLowerCase().indexOf("time="));  //  IndexOf -search for String, then extract
                    System.out.println("Latency information found! "); // Check for "time" info, and if true, print...
                    System.out.println("time= found at index: " + timeIndex);

                    String latencyText = line.substring(timeIndex + 5); //  Extract part of latencyText
                    System.out.println("Latency text: " + latencyText);

                    int msIndex = latencyText.toLowerCase().indexOf("ms");
                    if (msIndex != -1)
                        {
                            String latencyNumber = latencyText.substring(0, msIndex);
                            System.out.println("Latency number: " + latencyNumber);
                            latency = Double.parseDouble(latencyNumber);    //  Convert latency from string --> double to store into pingResult
                        }
                    System.out.println("Latency as a number: " + latency);
                }

            }

            int exitCode = process.waitFor(); // Wait for connection success ('success')
            
            if (exitCode == 0)
            {
                success = true;
                System.out.println("Connection responded.");
            }
            else
            {
                success = false;
                System.out.println("Connection timed out or could not be reached.");
            }
            LocalDateTime now = LocalDateTime.now();   //  Extract current time

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy  hh:mm:ss a");   //  Format time into something readable for user
            String timestamp = now.format(formatter);   //  process current time into formatter
            return new PingResult(target, latency, success, timestamp);   //  Display target, latency, success/failure, and time of Ping ran
        }
        catch (Exception e)
        {
            e.printStackTrace(); // Print the stack trace if an exception occurs
            return new PingResult(target, 0, false, "placeholder"); // Return a PingResult object with the actual success state

        }
    
    }
}

//  Day 4

//  Ping Result Class - Stores the result of a network test
class PingResult 
{
    String target;
    double latency;
    boolean success;
    String timestamp;

    public PingResult(
        String target, 
        double latency, 
        boolean success, 
        String timestamp
    )
    
    {
        this.target = target;
        this.latency = latency;
        this.success = success;
        this.timestamp = timestamp;
    }
}
//  End of PingResult class
//  End of source file
//  Wi-Fi Latency Analyzer
