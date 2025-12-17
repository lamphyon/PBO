import java.util.Scanner;
import java.util.HashSet;

public class InputReader {
    private Scanner scanner;

    public InputReader() {
        scanner = new Scanner(System.in);
    }

    public HashSet<String> getInput() {
        System.out.print("> ");
        String inputLine = scanner.nextLine().trim().toLowerCase();
        String[] wordArray = inputLine.split("\\s+");
        
        HashSet<String> words = new HashSet<>();
        for (String word : wordArray) {
            words.add(word);
        }
        return words;
    }
}
