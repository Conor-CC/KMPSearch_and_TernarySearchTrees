
import java.util.LinkedList;


public class TST<Value> {

    /*
     * Bus Service Questions:
     * 1. How many unique destinations is there in the file?
     *    //334
     * 2. Is there a bus going to the destination "SOUTHSIDE"?
     *    //no
     * 3. How many records is there about the buses going to the destination beginning with "DOWN"?
     *    //70
     *
     * Google Books Common Words Questions:
     * 4. How many words is there in the file?
     *    //97565
     * 5. What is the frequency of the word "ALGORITHM"?
     *    //1433021
     * 6. Is the word "EMOJI" present?
     *   //NO
     * 7. IS the word "BLAH" present?
     *   //True
     * 8. How many words are there that start with "TEST"?
     *    //38
     */
  /* A Node in your trie containing a Value val and a pointer to its children */
    private static class TrieNode<Value> {
        private char c;                        // character
        private TrieNode<Value> left, mid, right;  // left, middle, and right subtries
        private Value val;                     // value associated with string
    }


    /* a pointer to the start of your trie */
    TrieNode<Value> root;
    int keyCount;

    public TST() {
        TrieNode root = new TrieNode();
        int keyCount = 0;
    }

    /*
     * Returns the number of words in the trie
     */
    public int size() {
        return keyCount;
    }

    /*
     * returns true if the word is in the trie, false otherwise
     */
    public boolean contains(String key) {
        if (key == null || key.length() <= 0) return false;
        if (keyCount <= 0)  return false;
        //key = key.toLowerCase();
        int d = 0;
        TrieNode n = root;
        boolean wordCouldBePresent = true;
        while (wordCouldBePresent) {
            if (n != null) {
                if (d == key.length()) {
                    if (n.mid == null && n.c == key.charAt(key.length() - 1))
                        return true;
                    return false;
                }
                if (key.charAt(d) > n.c) {
                    n = n.right;
                }
                else if (key.charAt(d) < n.c) {
                    n = n.left;
                }
                else if (d < key.length() && key.charAt(d) == n.c) {
                    d++;
                    if (n.mid != null)
                        n = n.mid;
                }
            }
            else if (n == null) {
                wordCouldBePresent = false;
            }
        }
        return false;
    }

    /*
     * return the value stored in a node with a given key, returns null if word is not in trie
     */
    public Value get(String key) {
        if (key == null || key.equals(""))    return null;
        if (keyCount <= 0)  return null;
        //key = key.toLowerCase();
        int d = 0;
        TrieNode n = root;
        boolean wordCouldBePresent = true;
        while (wordCouldBePresent) {
            if (n != null) {
                if (d == key.length() - 1) {
                    return (Value) n.val;
                }
                if (key.charAt(d) > n.c) {
                    n = n.right;
                }
                else if (key.charAt(d) < n.c) {
                    n = n.left;
                }
                else if (key.charAt(d) == n.c) {
                    d++;
                    n = n.mid;
                }
            }
            else if (n == null) {
                wordCouldBePresent = false;
            }
        }
        return null;
    }


    /*
     * stores the Value val in the node with the given key
     */
    public void put(String key, Value val) {
        if (key != null && !key.equals("")) {
            //key = key.toLowerCase();
            root = put(root, key, val, 0);
            keyCount++;
        }
    }

    private TrieNode put(TrieNode n, String key, Value val, int d) {
        char charac = key.charAt(d);
        if (n == null) {
            n = new TrieNode();
            n.c = charac;
        }
        if (charac < n.c) {
            n.left = put(n.left,  key, val, d);
        }
        else if (charac > n.c) {
            n.right = put(n.right, key, val, d);
        }
        else if (d < key.length() - 1) {
            n.mid = put(n.mid,   key, val, d+1);
        }
        else {
            n.val   = val;
        }
        return n;
    }

//    //public void printTrie() {
//        printTrie(root);
//    }

//    private void printTrie(TrieNode x) {
//        if (x == null) return;
//        printTrie(x.left);
//        System.out.print(x.c + " ");
//        printTrie(x.mid);
//        printTrie(x.right);
//    }

    /*
     * returns the linked list containing all the keys present in the trie
     * that start with the prefix passes as a parameter, sorted in alphabetical order
     */
    public LinkedList<String> keysWithPrefix(String prefix) {
        if (prefix == null || prefix.equals("")) return null;
        if (keyCount <= 0)  return null;
        LinkedList<String> toReturn;
        //prefix = prefix.toLowerCase();
        int d = 0;
        TrieNode n = root;
        boolean prefixFound = false;
        while (!prefixFound) {
            if (n != null) {
                if (d == prefix.length() - 1) {
                    prefixFound = true;
                }
                if (d < prefix.length() && prefix.charAt(d) > n.c) {
                    n = n.right;
                }
                else if (d < prefix.length() && prefix.charAt(d) < n.c) {
                    n = n.left;
                }
                else if (d < prefix.length() && prefix.charAt(d) == n.c) {
                    d++;
                    n = n.mid;
                }
            }
            else if (n == null) {
                return null;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        if (prefix.length() == 1)
            n = n.mid;
        toReturn = new LinkedList<>();
        if (n.mid == null)
            toReturn.add(prefix);
        toReturn = assembleAlphabeticalList(n, toReturn, sb, prefix);
        return toReturn;
    }

    private LinkedList<String> assembleAlphabeticalList(TrieNode n, LinkedList<String> linkedList, StringBuilder s, String prefix) {
        //String currentStr = s;
        if (n == null) {
            return null;
        }
        assembleAlphabeticalList(n.left, linkedList, s, prefix);
        if (n.val != null) {
            linkedList.add(s.toString() + n.c);
        }
        //if (prefix.length() > 2) {
        assembleAlphabeticalList(n.mid, linkedList, s.append(n.c), prefix);
        s.deleteCharAt(s.length() - 1);
        //}

//        else {
//            assembleAlphabeticalList(n.mid, linkedList, s, prefix);
//        }
        assembleAlphabeticalList(n.right, linkedList, s, prefix);
        return linkedList;
    }

}