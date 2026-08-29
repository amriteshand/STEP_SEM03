import java.util.Scanner;

public class ISBNNormalizerValidator {

    // Method to normalize the ISBN-style code
    static String normalizeCode(String raw) {

        // Remove leading and trailing spaces
        String code = raw.trim();

        // If code has less than 3 characters, return it as it is
        // so that substring does not cause an error
        if (code.length() < 3) {
            return code;
        }

        // Convert only the first 3 characters to uppercase
        String publisherCode = code.substring(0, 3).toUpperCase();

        // Keep the remaining characters unchanged
        String remainingPart = code.substring(3);

        return publisherCode + remainingPart;
    }

    // Method to validate the normalized code and format it
    static String validateAndFormat(String code) {

        // Check total length
        if (code.length() != 13) {
            return "Invalid: wrong length";
        }

        // Check first 3 characters
        for (int i = 0; i < 3; i++) {

            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        // Check remaining 10 characters
        for (int i = 3; i < 13; i++) {

            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: body must contain only digits";
            }
        }

        // Build formatted output
        StringBuilder result = new StringBuilder();

        result.append("[")
              .append(code.substring(0, 3))
              .append("] YEAR: ")
              .append(code.substring(3, 7))
              .append(" | CATALOG: ")
              .append(code.substring(7));

        return result.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter ISBN-style code: ");
        String raw = sc.nextLine();

        // Normalize the input first
        String normalizedCode = normalizeCode(raw);

        // Validate and format the normalized code
        System.out.println(validateAndFormat(normalizedCode));

        sc.close();
    }
}