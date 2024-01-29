package com.example.noodleexaminationsystem.TrieTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Trie {
    private TrieNode root;


    private class TrieNode {
        HashMap<Character, TrieNode> children;
        boolean isEndOfWord;

        TrieNode() {
            this.children = new HashMap<>();
            this.isEndOfWord = false;
        }
    }

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;

        for (char ch : word.toCharArray()) {
            current.children.putIfAbsent(ch, new TrieNode());
            current = current.children.get(ch);
        }

        current.isEndOfWord = true;
    }

    public List<String> findAllWords() {
        List<String> result = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();

        findAllWordsHelper(root, currentWord, result);

        return result;
    }

    private void findAllWordsHelper(TrieNode node, StringBuilder currentWord, List<String> result) {
        if (node.isEndOfWord) {
            result.add(currentWord.toString());
        }

        for (char ch : node.children.keySet()) {
            currentWord.append(ch);
            findAllWordsHelper(node.children.get(ch), currentWord, result);
            currentWord.deleteCharAt(currentWord.length() - 1);
        }
    }

    private TrieNode findPrefixNode(String prefix) {
        TrieNode current = root;

        for (char ch : prefix.toCharArray()) {
            if (current.children.containsKey(ch)) {
                current = current.children.get(ch);
            } else {
                return null; // Prefix not found
            }
        }

        return current;
    }
    public List<String> findIncompleteWords(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode prefixNode = findPrefixNode(prefix);

        if (prefixNode != null) {
            StringBuilder currentWord = new StringBuilder(prefix);
            findIncompleteWordsHelper(prefixNode, currentWord, result);
        }

        return result;
    }

    private void findIncompleteWordsHelper(TrieNode node, StringBuilder currentWord, List<String> result) {
        if (node.isEndOfWord) {
            result.add(currentWord.toString());
        }

        for (char ch : node.children.keySet()) {
            currentWord.append(ch);
            findIncompleteWordsHelper(node.children.get(ch), currentWord, result);
            currentWord.deleteCharAt(currentWord.length() - 1);
        }
    }


    public TrieNode findNode(String word) {
        TrieNode current = root;

        for (char ch : word.toCharArray()) {
            if (current.children.containsKey(ch)) {
                current = current.children.get(ch);
            } else {
                return null; // Prefix not found
            }
        }

        return current;
    }
}
