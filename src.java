import java.util.Scanner;     // Import the Scanner class to read input from the user

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

        System.out.println("Please enter your age: ");    
        @SuppressWarnings("unused")
        int age = scanner.nextInt(); // Read the user's age from the input
        System.out.println("You are " + age + " years old.");

        System.out.println(); // Print a blank line for better readability

        System.out.println("Have you used this program before? (yes/no): ");
        String input = scanner.next(); // Read the user's response
        System.out.println("You answered: " + input);

        System.out.println(); // Print a blank line for better readability

        System.out.println("Thank you for using this Latency Analyzer program! Please type 'confirm' down below to proceed.");
        String confirmation = scanner.next(); // Read the user's confirmation response
        if (confirmation.equals("confirm")) // Check if the user confirmed
        {
            System.out.println("You confirmed");
        }
        else
        {
            System.out.println("You did not choose to proceed. Please type 'confirm' in all lower-case to proceed."); // Inform the user that they did not confirm
        }

        System.out.println(); // Print a blank line for better readability

        System.out.println("Do you consent to answering a few survey questions? Your privacy is important to us, and your responses are completely confidential. Your responses help improve the program and provide a better user experience. Please type 'yes' to proceed or 'no' to exit the survey.");
        String consent = scanner.next(); // Read the user's consent response
        if (consent.equals("yes"))
            {
                System.out.println("Thank you for consenting to the survey! We appreciate your time and feedback.");
            }
        else
            {
                System.out.println("You did not consent to the survey. Thank you for using the program! Goodbye.");
                System.exit(0);
            }

        System.out.println(); // Print a blank line for better readability
        
        Scanner survey = new Scanner(System.in); // Create a new scanner object for the survey
        System.out.println("Please answer the following survey questions with accuracy and honesty. Please type complete sentences to answer the following questions.");

        System.out.println(); // Print a blank line for better readability

        System.out.println("1. How often do you use this program?"); // Ask the first survey question
        String answer1 = survey.nextLine();

        System.out.println(); // Print a blank line for better readability

        System.out.println("2. Have you ever experienced any issues with internet speed or latency in your day to day life? If so, please describe the issues you have faced."); // Ask the second survey question
        String answer2 = survey.nextLine();

        System.out.println(); // Print a blank line for better readability

        System.out.println("3. How important is internet speed and latency to you in your daily life? Please explain your answer.");
        String answer3 = survey.nextLine();

        System.out.println(); // Print a blank line for better readability
    
        System.out.println("4. Have you ever used any other programs or tools to analyze your internet speed or latency? If so, please describe your experience with those programs or tools.");
        String answer4 = survey.nextLine();
        
        System.out.println(); // Print a blank line for better readability

        System.out.println("5. Lastly, which aspects of your life, like work, gaming, or video calling, are most affected by your internet?");
        String answer5 = survey.nextLine();

        System.out.println(); // Print a blank line for better readability

        System.out.println("Thank you for completing the survey! Your responses have been recorded and are privately stored. We appreciate your time and feedback.");
        
        Scanner website = new Scanner(System.in); // Create a new scanner object for the website question
        System.out.println("What is your public test target (eg. Cloudfare, Google DNS)?"); // Print a blank line for better readability
        String websiteInput = website.nextLine(); // Read the user's website/IP address input
        System.out.println("Confirming: " + websiteInput); // Print the user's website/IP address input;
        
        PingResult pingResult = new PingResult();
        pingResult.target = "google.com";
        pingResult.latency = 24.5;
        pingResult.success = true;
        pingResult.timestamp = "2026-08-13 22:30";

        System.out.println("Target: " + pingResult.target);
        System.out.println("Latency: " + pingResult.latency + " ms");
        System.out.println("Success: " + pingResult.success);
        System.out.println("Timestamp: " + pingResult.timestamp);

    }
       
    
}

class PingResult

{
    String target;
    double latency;
    boolean success;
    String timestamp;
}