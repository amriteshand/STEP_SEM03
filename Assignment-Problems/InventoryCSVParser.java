import java.util.Scanner;

public class InventoryCSVParser {

    // Method to parse and display the inventory record
    static void parseInventoryRecord(String csvLine) {

        // Split the CSV line using comma
        String[] fields = csvLine.split(",");

        // Check whether exactly 3 fields are present
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        // Display the formatted inventory record
        System.out.println("Product: " + fields[0]
                + " | SKU: " + fields[1]
                + " | Qty: " + fields[2]);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter inventory record: ");
        String csvLine = sc.nextLine();

        parseInventoryRecord(csvLine);

        sc.close();
    }
}