import java.util.*;

public class FilteredWordFrequency {

    // Method to calculate and print filtered word frequencies
    static void printFilteredWordFrequency(String feedback) {

        // Convert text to lowercase
        String cleanedText = feedback.toLowerCase();

        // Remove periods and commas
        cleanedText = cleanedText.replace(".", "");
        cleanedText = cleanedText.replace(",", "");

        // Split the cleaned text into words
        String[] words = cleanedText.split("\\s+");

        // Fixed list of stop words
        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};

        // HashMap to store word frequencies
        HashMap<String, Integer> frequency = new HashMap<>();

        // Process every word
        for (String word : words) {

            // Check whether the word is a stop word
            boolean isStopWord = false;

            for (String stopWord : stopWords) {

                if (word.equals(stopWord)) {
                    isStopWord = true;
                    break;
                }
            }

            // Skip stop words
            if (isStopWord) {
                continue;
            }

            // Update word frequency
            if (frequency.containsKey(word)) {
                frequency.put(word, frequency.get(word) + 1);
            } else {
                frequency.put(word, 1);
            }
        }

        // Convert HashMap entries into a list
        ArrayList<Map.Entry<String, Integer>> entries =
                new ArrayList<>(frequency.entrySet());

        // Sort entries by frequency in descending order
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> e1,
                               Map.Entry<String, Integer> e2) {

                return e2.getValue() - e1.getValue();
            }
        });

        // Print the sorted word frequencies
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter feedback paragraph:");
        String feedback = sc.nextLine();

        printFilteredWordFrequency(feedback);

        sc.close();
    }
}