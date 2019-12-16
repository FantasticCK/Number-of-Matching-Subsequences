package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}

// Trie
class Solution {
    class TrieNode{
        TrieNode[] children;
        String word;
        int count;

        TrieNode() {
            children = new TrieNode[26];
            word = "";
            count = 0;
        }
    }

    class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        public void insert(String s) {
            int i = 0;
            TrieNode curr = root;
            while (i < s.length()) {
                int c = s.charAt(i) - 'a';
                if (curr.children[c] == null) {
                    curr.children[c] = new TrieNode();
                }
                curr = curr.children[c];
                i++;
            }
            curr.word = s;
            curr.count++;
        }

        public String find(TrieNode curr) {
            return curr.word;
        }
    }

    public int numMatchingSubseq(String S, String[] words) {
        Trie trie = new Trie();
        if (S.length() == 0 || words.length == 0) {
            return 0;
        }

        for (String s : words) {
            trie.insert(s);
        }

        TrieNode curr = trie.root;
        return dfs(S, 0, curr);

    }

    private int dfs(String s, int st, TrieNode root) {
        int result = 0;
        if(root.word.length() != 0)
            result += root.count;
        if(st >= s.length())
            return result;
        for(int i = 0; i < 26; i++) {
            if(root.children[i] != null) {
                int nextStart = s.indexOf((char)(i + 'a'), st);
                if(nextStart != -1)
                    result += dfs(s, nextStart + 1, root.children[i]);
            }
        }
        return result;
    }
}


// Next Letter Pointer
class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        int ans = 0;
        ArrayList<Node>[] heads = new ArrayList[26];
        for (int i = 0; i < 26; ++i)
            heads[i] = new ArrayList<Node>();

        for (String word: words)
            heads[word.charAt(0) - 'a'].add(new Node(word, 0));

        for (char c: S.toCharArray()) {
            ArrayList<Node> old_bucket = heads[c - 'a'];
            heads[c - 'a'] = new ArrayList<Node>();

            for (Node node: old_bucket) {
                node.index++;
                if (node.index == node.word.length()) {
                    ans++;
                } else {
                    heads[node.word.charAt(node.index) - 'a'].add(node);
                }
            }
            old_bucket.clear();
        }
        return ans;
    }

}

class Node {
    String word;
    int index;
    public Node(String w, int i) {
        word = w;
        index = i;
    }
}