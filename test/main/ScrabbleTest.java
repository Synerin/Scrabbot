package main;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class ScrabbleTest {

    Scrabble scrabble = new Scrabble();

    @Test
    public void dictTest() throws FileNotFoundException {
        scrabble.processDictionary(new File("src/main/dictionary.txt"));
        boolean apple = scrabble.allWords.checkWord("Apple");
        boolean apfel = scrabble.allWords.checkWord("Apfel");

        assertTrue(apple);
        assertFalse(apfel);
    }

}