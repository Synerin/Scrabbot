package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Scrabble {
    public static final int BOARD_SIZE = 15;
    public static final int[] LETTER_VALUES = new int[] {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
    public char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
    Trie allWords = new Trie();

    public void processDictionary(File fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(fileName);

        while(scanner.hasNextLine()) {
            String word = scanner.nextLine();
            allWords.insert(word);
        }
    }

    public void playWord(String word, int[][] positions) {
        // TODO Add validation for words
        int col;
        int row;

        for(int i = 0; i < positions.length; i++) {
            col = positions[i][0];
            row = positions[i][1];

            board[col][row] = word.charAt(i);
        }
    }

    public int calculateWordScore(int[][] positions) {
        int score = 0;

        for(int[] p : positions) {
            score += calculateLetterScore(p);
        }

        return score;
    }

    public int calculateLetterScore(int[] position) {
        int score = 0;

        int col = position[0];
        int row = position[1];

        int leftRange = col;
        int rightRange = col;
        int aboveRange = row;
        int belowRange = row;

        while(leftRange > 0 && board[row][leftRange - 1] != ' ') leftRange--;
        while(rightRange < BOARD_SIZE - 1 && board[row][rightRange + 1] != ' ') rightRange++;
        while(aboveRange > 0 && board[aboveRange - 1][col] != ' ') aboveRange--;
        while(belowRange < BOARD_SIZE - 1 && board[belowRange + 1][col] != ' ') belowRange++;

        if(leftRange != rightRange) {
            for(int i = leftRange; i <= rightRange; i++) {
                score += LETTER_VALUES[board[row][i] - 'A'];
            }
        }

        if(aboveRange != belowRange) {
            for(int j = aboveRange; j <= belowRange; j++) {
                score += LETTER_VALUES[board[j][col] - 'A'];
            }
        }

        return score;
    }

    public void setBoard() {
        // Default Spaces
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = ' ';
            }
        }

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
