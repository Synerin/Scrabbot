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
        assertEquals('3', scrabble.board[0][0]);
        assertEquals(' ', scrabble.board[0][1]);
        assertEquals('*', scrabble.board[7][7]);
        assertEquals('2', scrabble.board[1][1]);
        assertEquals('t', scrabble.board[5][1]);
        assertEquals('d', scrabble.board[3][0]);
    }

    @Test
    public void wordTest() throws FileNotFoundException {
        scrabble = new Scrabble();
        scrabble.setBoard();
        scrabble.processDictionary(new File("src/main/dictionary.txt"));
        String testWord = "HELLO";
        int[][] testPositions = new int[][] {{7, 5}, {7, 6}, {7, 7}, {7, 8}, {7, 9}};

        assertNotEquals('L', scrabble.board[7][7]);

        assertTrue(scrabble.playWord(testWord, testPositions));
        assertEquals('L', scrabble.board[7][7]);
        assertEquals(' ', scrabble.board[6][7]);
    }

    @Test
    public void getWordTest() {
        scrabble = new Scrabble();
        scrabble.setBoard();
        String testWord = "HELLO";
        int[][] testPositions = new int[][] {{7, 5}, {7, 6}, {7, 7}, {7, 8}, {7, 9}};

        scrabble.playWord(testWord, testPositions);

        assertEquals(testWord, scrabble.getWord(testPositions));
    }

    @Test
    public void scoreTestHorizontal() {
        scrabble = new Scrabble();
        scrabble.setBoard();
        String testWord = "HELLO";
        int[][] testPositions = new int[][] {{7, 5}, {7, 6}, {7, 7}, {7, 8}, {7, 9}};

        scrabble.playWord(testWord, testPositions);

        assertEquals(8, scrabble.calculateWordScore(testWord));
        assertEquals(8, scrabble.calculateLetterScore(new int[]{7,7}));
        assertEquals(8, scrabble.calculateWordScore(testPositions));
    }

    @Test
    public void scoreTestVertical() {
        scrabble = new Scrabble();
        scrabble.setBoard();
        String testWord = "HELLO";
        int[][] testPositions = new int[][] {{5, 7}, {6, 7}, {7, 7}, {8, 7}, {9, 7}};

        scrabble.playWord(testWord, testPositions);

        assertEquals(8, scrabble.calculateWordScore(testWord));
        assertEquals(8, scrabble.calculateLetterScore(new int[]{7,7}));
        assertEquals(8, scrabble.calculateWordScore(testPositions));
    }

    @Test
    public void scoreTestTwoWords() {
        scrabble = new Scrabble();
        scrabble.setBoard();

        String firstWord = "HELLO";
        int[][] firstPositions = new int[][] {{7, 5}, {7, 6}, {7, 7}, {7, 8}, {7, 9}};
        scrabble.playWord(firstWord, firstPositions);

        String secondWord = "WRLD"; // Skips the 'O'
        int[][] secondPositions = new int[][] {{6, 9}, {8, 9}, {9, 9}, {10, 9}};
        scrabble.playWord(secondWord, secondPositions);

        assertEquals(8, scrabble.calculateWordScore(secondWord));
        assertEquals(17, scrabble.calculateLetterScore(new int[]{7,9}));
        assertEquals(8, scrabble.calculateWordScore(secondPositions));
    }
}