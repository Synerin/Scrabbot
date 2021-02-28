package main;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class ScrabbleTest {

    Scrabble scrabble;

    @Test
    public void dictTest() throws FileNotFoundException {
        scrabble = new Scrabble();
        scrabble.processDictionary(new File("src/main/dictionary.txt"));
        boolean apple = scrabble.allWords.checkWord("Apple");
        boolean apfel = scrabble.allWords.checkWord("Apfel");

        assertTrue(apple);
        assertFalse(apfel);
    }

    @Test
    public void boardTest() {
        scrabble = new Scrabble();
        scrabble.setBoard();
        assertTrue(scrabble.board[0][0] == 'T');
        assertTrue(scrabble.board[0][1] == ' ');
        assertTrue(scrabble.board[7][7] == '*');
        assertTrue(scrabble.board[1][1] == 'D');
        assertTrue(scrabble.board[5][1] == 't');
        assertTrue(scrabble.board[3][0] == 'd');
    }

    @Test
    public void wordTest() {
        scrabble = new Scrabble();
        scrabble.setBoard();
        String testWord = "HELLO";
        int[][] testPositions = new int[][] {{7, 5}, {7, 6}, {7, 7}, {7, 8}, {7, 9}};

        assertFalse(scrabble.board[7][7] == 'L');

        scrabble.playWord(testWord, testPositions);

        assertTrue(scrabble.board[7][7] == 'L');
        assertTrue(scrabble.board[6][7] == ' ');
    }
}