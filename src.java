import java.io.BufferedReader;     // Import the Scanner class to read input from the user
import java.io.InputStreamReader; // Import the BufferedReader class to read output from the ping command
import java.util.Scanner; // Import the InputStreamReader class to read output from the ping command


public class src 
{
    public static void main(String[] args) // Main method - entry point of the program
    {
        System.out.println("Hello, user! Welcome to the Java program.");
        Scanner scanner = new Scanner(System.in); // Create a scanner object to read input from the user

        System.out.println();
        
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine(); // Read the user's name from the input
        
        System.out.println("Hello, " + name + "!");

        System.out.println(); // Print a blank line for better readability

        System.out.println("Please enter your age (as a number): ");    
        while (!scanner.hasNextInt()) // Check if the input is not an integer
        {
            System.out.println("Invalid input. Please enter a number for your age: "); // Prompt the user to enter a valid number
            scanner.nextLine(); // Consume the invalid input
        }
        int age = scanner.nextInt(); // Read the user's age from the input
        scanner.nextLine(); // Consume the newline character left by nextInt()
        while (age < 0) // Check if the age is negative
        {
            System.out.println("Age cannot be negative. Please enter a valid age: "); // Prompt the user to enter a valid age
            while (!scanner.hasNextInt()) // Check if the input is not an integer 
            {
                System.out.println("Age must be a number. Please enter a valid age: "); // Prompt the user to enter a valid number
                scanner.nextLine(); // Consume the invalid input
            }

            age = scanner.nextInt(); // Read the user's age from the input
            scanner.nextLine(); // Consume the newline character left by nextInt()
        }
        System.out.println("You are " + age + " years old.");

        System.out.println(); // Print a blank line for better readability

        while(true)
        {
            System.out.println("Have you used this program before? (yes/no): ");
            String input = scanner.next(); // Read the user's response
            
            if (input.equalsIgnoreCase("Yes"))
            {
                System.out.println("You answered: Yes.");
                break;
            }
            else if (input.equalsIgnoreCase("no"))
            {
                System.out.println("You answered: No.");
                break;
            }
            else
            {
                System.out.println("\"" + input + "\" not recognized. Please type 'yes' or 'no'.");
            }
        }
    
        System.out.println(); // Print a blank line for better readability

        System.out.println("Thank you for using this Latency Analyzer program! Please type 'confirm' down below to proceed.");
        String confirmation1 = scanner.next(); // Read the user's confirmation response is this correct? 
        if (confirmation1.equalsIgnoreCase("confirm")) // Check if the user confirmed
        {
            System.out.println("You confirmed");
        }
        else
        {
            System.out.println("You did not choose to proceed. Please type 'confirm' in all lower-case to proceed."); // Inform the user that they did not confirm
        }
    
        while(true)
        {
            System.out.println("Do you consent to answering a few survey questions? Your privacy is important to us, and your responses are completely confidential. Your responses help improve the program and provide a better user experience. Please type 'yes' to proceed or 'no' to exit the survey.");
            String consent = scanner.next(); // Read the user's consent response
            if (consent.equalsIgnoreCase("yes")) // Check if the user consented
                {
                    System.out.println("Thank you for consenting to the survey! We appreciate your time and feedback.");
                    break;
                }
            else if (consent.equalsIgnoreCase("no"))
                {
                    System.out.println("You did not consent to the survey. Thank you for using the program! Goodbye.");
                    System.exit(0);
                    return;
                }
                else
                {
                    System.out.println("Invalid input. Please type 'yes' to proceed or 'no' to exit the survey."); // Prompt the user to enter a valid response
                }
        } 

        // Day 2
        
        System.out.println(); // Print a blank line for better readability
        
        System.out.println("Please answer the following survey questions with accuracy and honesty. Please type complete sentences to answer the following questions.");

        System.out.println(); // Print a blank line for better readability

        System.out.println("1. How often do you use this program?"); // Ask the first survey question
        String answer1 = scanner.next();

        System.out.println(); // Print a blank line for better readability

        System.out.println("2. Have you ever experienced any issues with internet speed or latency in your day to day life? If so, please describe the issues you have faced."); // Ask the second survey question
        String answer2 = scanner.next();

        System.out.println(); // Print a blank line for better readability

        System.out.println("3. How important is internet speed and latency to you in your daily life? Please explain your answer.");
        String answer3 = scanner.next();

        System.out.println(); // Print a blank line for better readability
    
        System.out.println("4. Have you ever used any other programs or tools to analyze your internet speed or latency? If so, please describe your experience with those programs or tools.");
        String answer4 = scanner.next();
        
        System.out.println(); // Print a blank line for better readability

        System.out.println("5. Lastly, which aspects of your life, like work, gaming, or video calling, are most affected by your internet?");
        String answer5 = scanner.next();

        System.out.println(); // Print a blank line for better readability

        System.out.println("Thank you for completing the survey! Your responses have been recorded and are privately stored. We appreciate your time and feedback.");
        
        // Day 3

        System.out.println(); // Print a blank line for better readability

        System.out.println("What is your public test target (eg. Cloudflare, Google DNS)?");
        String websiteInput = scanner.next(); // Read the user's website/IP address input
        System.out.println("Confirming: " + websiteInput); // Print the user's website/IP address input;
        
        System.out.println(); // Print a blank line for better readability

        System.out.println("Thank you for providing your public test target. We will now proceed to analyze the latency to " + websiteInput + ". Please wait while we perform the analysis...");

        System.out.println(); // Print a blank line for better readability

        PingResult result = new PingResult(websiteInput, 24.5, true, "2026-08-13 22:30");

        result.target = websiteInput;
        result.latency = 24.5;
        result.success = true;
        result.timestamp = "2026-08-13 22:30";

        System.out.println("Target: " + result.target);
        System.out.println("Latency: " + result.latency + " ms");
        System.out.println("Success: " + result.success);
        System.out.println("Timestamp: " + result.timestamp);

        scanner.close(); // Close the scanner to prevent resource leaks
    }

    // NEW METHOD — ProcessBuilder goes HERE:

    public static PingResult ping(String target) //  Simulate a ping operation and return a PingResult object
    {
        ProcessBuilder processBuilder = new ProcessBuilder(
            "ping",
            "-n", 
            "1", // Send only one ping packet
            target
        );

        try
        {
            Process process = processBuilder.start(); // Start the ping process
            
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) // Read the output of the ping command
            {
                System.out.println(line); // Print each line of the ping output
            }

        }
        catch (Exception e)
        {
            e.printStackTrace(); // Print the stack trace if an exception occurs
        }

        return new PingResult(target, 0, false, "placeholder"); // Return a PingResult object with placeholder values
    }
}

//  Day 4
//  Perform Ping Results using Constructor here:

//  Ping Result Class
class PingResult 
{
    String target;
    double latency;
    boolean success;
    String timestamp;

    public PingResult(String target, double latency, boolean success, String timestamp)
    {
        this.target = target;
        this.latency = latency;
        this.success = success;
        this.timestamp = timestamp;
    }
}