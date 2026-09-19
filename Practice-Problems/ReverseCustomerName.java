public class ReverseCustomerName {

    static String reverseCustomerName(
            String customerName) {

        char[] characters =
                customerName.toCharArray();

        StringBuilder reversed =
                new StringBuilder();

        for (int i = characters.length - 1;
             i >= 0;
             i--) {

            reversed.append(characters[i]);
        }

        return reversed.toString();
    }

    public static void main(String[] args) {

        String customerName = "Sunil";

        String reversed =
                reverseCustomerName(customerName);

        System.out.println(
                "Original Name: " + customerName);

        System.out.println(
                "Reversed Name: " + reversed);
    }
}