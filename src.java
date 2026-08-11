import java.util.Scanner;     // Import the Scanner class to read input from the user

public class src 
{
    public static void main(String[] args)
    {
        System.out.println("Hello, user! Welcome to the Java program.");
        Scanner scanner = new Scanner(System.in); // Create a scanner object to read input from the user
        
        System.out.print("Please enter your name: ");
        String name = scanner.nextLine(); // Read the user's name from the input
        
        System.out.println("Hello, " + name + "!");

        System.out.println("Please enter your age: ");    
        @SuppressWarnings("unused")
        int age = scanner.nextInt();
        System.out.println("You are " + age + " years old.");

        System.out.println("Have you used this program before? (yes/no): ");
        String question = scanner.nextLine(); // Read the user's response
        
        System.out.println("You answered: " + question);

    }
}