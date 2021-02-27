package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Scrabble {
    public static final int BOARD_SIZE = 15;
    public char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
    Trie allWords = new Trie();

    public void processDictionary(File fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(fileName);

        while(scanner.hasNextLine()) {
            String word = scanner.nextLine();
            allWords.insert(word);
        }
    }

    public void setBoard() {
        // Triple Words
        setTripleWordScores();

        // Double Words
        setDoubleWordScores();

        // Triple Letters
        setTripleLetterScores(1);
        setTripleLetterScores(5);
        setTripleLetterScores(9);
        setTripleLetterScores(13);

        // Double Letters
        setDoubleLetterScores(0);
        setDoubleLetterScores(2);
        setDoubleLetterScores(3);
        setDoubleLetterScores(6);
        setDoubleLetterScores(7);
        setDoubleLetterScores(8);
        setDoubleLetterScores(11);
        setDoubleLetterScores(12);
        setDoubleLetterScores(14);

        // Starting Spot
        board[7][7] = '*';
    }

    public void setTripleWordScores() {
        int row = 0, col = 0;

        for(; col < BOARD_SIZE; row += 7, col += 7) {
            board[row][col] = 'T';
        }
    }

    public void setDoubleWordScores() {
        int row = 1, col = 1;

        for(; row < BOARD_SIZE; row++) {
            for(; col < BOARD_SIZE; col++) {
                if(row == col) board[row][col] = 'D';
            }
        }
    }

    public void setTripleLetterScores(int row) {
        if(row == 1 || row == 13) {
            board[row][5] = 't';
            board[row][9] = 't';
        } else if(row == 5 || row == 9) {
            for(int col = 1; col < BOARD_SIZE; col += 4) {
                board[row][col] = 't';
            }
        }
    }

    public void setDoubleLetterScores(int row) {
        if(row == 0 || row == 7 || row == 14) {
            board[row][3] = 'd';
            board[row][11] = 'd';
        } else if(row == 2 || row == 12) {
            board[row][6] = 'd';
            board[row][8] = 'd';
        } else if(row == 3 || row == 11) {
            board[row][0] = 'd';
            board[row][7] = 'd';
            board[row][14] = 'd';
        } else if(row == 6 || row == 8) {
            board[row][2] = 'd';
            board[row][6] = 'd';
            board[row][8] = 'd';
            board[row][12] = 'd';
        }
    }
}
