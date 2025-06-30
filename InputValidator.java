import java.util.Scanner;

public class InputValidator {
    public static int getValidInteger(String prompt, int min, int max) {
        System.out.print(prompt);
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        try {
            int value = Integer.parseInt(userInput);
            if (value < min || value > max) {
                throw new IllegalArgumentException("Input out of range.");
            }
            return value;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Please try again.");
            return getValidInteger(prompt, min, max);
        }
    }
    public static String getNonEmptyString(String prompt) {
        System.out.print(prompt);
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        if (userInput.trim().isEmpty()) {
            System.out.println("Error: Input cannot be empty.");
            return getNonEmptyString(prompt);
        }
        return userInput;
    }
    public static int getValidDiceSides(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        String input = scanner.nextLine();
        try { 
            int count = Integer.parseInt(input);
            if (count > 0) {
                return count;
            } else {
                throw new IllegalArgumentException("You must roll at least one die.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input. Please enter a valid number.");
            return getValidDiceSides(prompt);
        }
    }
}