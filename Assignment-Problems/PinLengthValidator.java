import java.util.Scanner;

public class PinLengthValidator {

    // Method to check whether PIN has exactly 4 digits
    static void checkPinLength(String pin) {

        if (pin.length() == 4) {
            System.out.println("PIN length OK.");
        } else {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        checkPinLength(pin);

        sc.close();
    }
}