import java.util.Scanner;

public class UserInputResponse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a command (hello, goodbye, help, or quit): ");
        String userInput = scanner.nextLine();

        switch (userInput.toLowerCase()) {
            case "hello":
                System.out.println("Hello! It's nice to meet you.");
                break;
            case "goodbye":
                System.out.println("Goodbye! It was nice chatting with you.");
                break;
            case "help":
                System.out.println("Available commands:");
                System.out.println("  hello: Say hello");
                System.out.println("  goodbye: Say goodbye");
                System.out.println("  help: Show this help message");
                System.out.println("  quit: Quit the program");
                break;
            case "quit":
                System.out.println("Goodbye! Quitting the program.");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid command. Try 'hello', 'goodbye', 'help', or 'quit'.");
                break;
        }

        // Continue to prompt the user for input until they quit
        while (true) {
            System.out.println("Enter a command (hello, goodbye, help, or quit): ");
            userInput = scanner.nextLine();

            switch (userInput.toLowerCase()) {
                case "hello":
                    System.out.println("Hello! It's nice to meet you.");
                    break;
                case "goodbye":
                    System.out.println("Goodbye! It was nice chatting with you.");
                    break;
                case "help":
                    System.out.println("Available commands:");
                    System.out.println("  hello: Say hello");
                    System.out.println("  goodbye: Say goodbye");
                    System.out.println("  help: Show this help message");
                    System.out.println("  quit: Quit the program");
                    break;
                case "quit":
                    System.out.println("Goodbye! Quitting the program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid command. Try 'hello', 'goodbye', 'help', or 'quit'.");
                    break;
            }
        }
    }
}
