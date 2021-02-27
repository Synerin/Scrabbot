package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Scrabble {
    private char[] letters;
    Trie allWords = new Trie();

    public void processDictionary(File fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(fileName);

        while(scanner.hasNextLine()) {
            String word = scanner.nextLine();
            allWords.insert(word);
        }
    }
}
