package main;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrieTest {

    Trie trie;

    @Test
    public void basicTest() {
        trie = new Trie();

        trie.insert("Hello");
        assertTrue(trie.checkWord("Hello"));
        assertFalse(trie.checkWord("World"));
    }

    @Test
    public void caseTest() {
        trie = new Trie();

        trie.insert("ABC");
        assertTrue(trie.checkWord("abc"));
        trie.insert("def");
        assertTrue(trie.checkWord("DEF"));
    }

    @Test
    public void leafTest() {
        trie = new Trie();

        trie.insert("hello");
        assertFalse(trie.checkWord("h"));
        assertFalse(trie.checkWord("he"));
        assertFalse(trie.checkWord("hel"));
        assertFalse(trie.checkWord("hell"));
        assertTrue(trie.checkWord("hello"));
        assertFalse(trie.checkWord("helloo"));
    }

}