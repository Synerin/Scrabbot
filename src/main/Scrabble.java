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

    public boolean playWord(String word, int[][] positions) {
        if(!allWords.checkWord(word)) {
            System.out.println("\"" + word + "\" is an invalid word.");
            return false;
        }

        int col;
        int row;

        for(int i = 0; i < positions.length; i++) {
            col = positions[i][0];
            row = positions[i][1];

            board[col][row] = word.charAt(i);
        }

        return true;
    }

    public int calculateWordScore(String word) {
        int score = 0;

        for(char c : word.toCharArray()) {
            score += LETTER_VALUES[c - 'A'];
        }

        return score;
    }

    public int calculateWordScore(int[][] positions) {
        int rawScore = calculateWordScore(getWord(positions));
        int score = rawScore;
        int multiplier = findWordMultiplier(positions);

        for(int[] p : positions) {
            System.out.println(p[0] + " " + p[1] + ": " + board[p[0]][p[1]]);
            score += rawScore - calculateLetterScore(p);
        }

        return score * multiplier;
    }

    public int calculateLetterScore(int[] position) {
        int score = 0;
        int multiplier = 1;

        int row = position[0];
        int col = position[1];

        if(board[row][col] == 't') {
            multiplier = 3;
        } else if(board[row][col] == 'd') {
            multiplier = 2;
        }

        int leftRange = col;
        int rightRange = col;
        int aboveRange = row;
        int belowRange = row;

        while(leftRange > 0 && Character.isUpperCase(board[row][leftRange - 1])) leftRange--;
        while(rightRange < BOARD_SIZE - 1 && Character.isUpperCase(board[row][rightRange + 1])) rightRange++;
        while(aboveRange > 0 && Character.isUpperCase(board[aboveRange - 1][col])) aboveRange--;
        while(belowRange < BOARD_SIZE - 1 && Character.isUpperCase(board[belowRange + 1][col])) belowRange++;

        if(leftRange < rightRange) { // Horizontal word may exist
            for(int i = leftRange; i <= rightRange; i++) {
                if(i == col) {
                    score += LETTER_VALUES[board[row][col] - 'A'] * multiplier;
                } else {
                    score += LETTER_VALUES[board[row][i] - 'A'];
                }
            }
        }

        if(aboveRange < belowRange) { // Vertical word may exist
            for(int j = aboveRange; j <= belowRange; j++) {
                if(j == row) {
                    score += LETTER_VALUES[board[row][col] - 'A'] * multiplier;
                } else {
                    score += LETTER_VALUES[board[j][col] - 'A'];
                }
            }
        }

        return score;
    }

    public String getWord(int[][] positions) {
        if(positions.length == 1) return "" + positions[0][0];

        StringBuilder word = new StringBuilder();

        for(int i = 0; i < positions.length; i++) {
            int[] pos = positions[i];
            word.append(board[pos[0]][pos[1]]);
        }

        return word.toString();
    }

    public int findWordMultiplier(int[][] positions) {
        int modifier = 1;

        for(int[] p : positions) {
            char space = board[p[0]][p[1]];

            if(space == '3') {
                modifier *= 3;
            } else if(space == '2') {
                modifier *= 2;
            }
        }

        return modifier;
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
        int row, col;

        for(row = 0; row < BOARD_SIZE; row += 7) {
            for(col = 0; col < BOARD_SIZE; col += 7) {
                board[row][col] = '3';
            }
        }
    }

    public void setDoubleWordScores() {
        int row, col;

        for(row = 1; row < 5; row++) {
            for(col = 1; col < 5; col++) {
                if(row == col) {
                    board[row][col] = '2';
                    board[BOARD_SIZE - row - 1][col] = '2';
                    board[row][BOARD_SIZE - col - 1] = '2';
                    board[BOARD_SIZE - row - 1][BOARD_SIZE - col - 1] = '2';
                }
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
