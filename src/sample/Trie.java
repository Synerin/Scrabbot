package sample;

public class Trie {
    private TrieNode root = new TrieNode();

    // Insert a word into the trie
    public void insert(String word) {
        TrieNode start = root;
        char letter;
        int index;

        for(int i = 0; i < word.length(); i++) {
            letter = Character.toUpperCase(word.charAt(i));

            // Verify that all chars of word are letters
            if(!Character.isAlphabetic(letter)) {
                throw new IllegalArgumentException();
            }

            index = letter - 'A';

            if(start.children[index] == null) {
                start.children[index] = new TrieNode();
            }

            start = start.children[index];
        }

        start.isLeaf = true;
    }

    // Check if a word is present in the trie
    public boolean checkWord(String word) {
        TrieNode start = root;
        char letter;
        int index;

        for(int i = 0; i < word.length(); i++) {
            letter = Character.toUpperCase(word.charAt(i));

            // Verify that all chars of word are letters
            if(!Character.isAlphabetic(letter)) {
                throw new IllegalArgumentException();
            }

            index = letter - 'A';

            if(start.children[index] == null) {
                return false;
            }

            start = start.children[index];
        }

        return start.isLeaf;
    }
}

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isLeaf = false;

    public TrieNode() {
        for(int i = 0; i < 26; i++) {
            children[i] = null;
        }
    }
}
