import java.util.Scanner;

public class WordReversalEncoder {

    // Method to reverse every word while keeping word order unchanged
    static String reverseEachWord(String sentence) {

        // Split the sentence into individual words
        String[] words = sentence.split(" ");

        StringBuilder result = new StringBuilder();

        // Reverse each word one by one
        for (int i = 0; i < words.length; i++) {

            StringBuilder reversedWord = new StringBuilder();

            // Add characters from the end to the beginning
            for (int j = words[i].length() - 1; j >= 0; j--) {
                reversedWord.append(words[i].charAt(j));
            }

            // Add the reversed word to the result
            result.append(reversedWord);

            // Add space between words
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String sentence = sc.nextLine();

        System.out.println(reverseEachWord(sentence));

        sc.close();
    }
}