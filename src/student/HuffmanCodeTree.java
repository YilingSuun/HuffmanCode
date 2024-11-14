package student;

import provided.BinarySequence;

public class HuffmanCodeTree {
    /**
     * The root node of the Huffman code tree.
     */
    private HuffmanNode root;

    /**
     * A constructor that creates a Huffman code tree using
     * a provided Node as root.
     * @param root a provided node
     */
    public HuffmanCodeTree(HuffmanNode root) {
        this.root = root;
    }

    /**
     * A constructor which should create a Huffman code tree
     * based on the data stored in a Huffman code book.
     * @param codebook the given code book
     */
    public HuffmanCodeTree(HuffmanCodeBook codebook) {
        //Create a new root node
        root = new HuffmanNode(null);
        //loop over the chars in the Huffman code book, get the related sequences
        //and repeatedly call the put method to update the tree with each code.
        for (int i = 0; i < codebook.getSize(); i++) {
            char eachChar = codebook.getLetters()[i];
            put(codebook.getSequence(eachChar), eachChar);
        }
    }

    /**
     * This should check if the tree formed by the root node
     * and it’s descendants is a valid Huffman code tree.
     * @return true if it's valid
     */
    public boolean isValid() {
        return root.isValidTree();
    }

    /**
     * This method should modify the binary tree structure so that the
     * node “addressed” by the binary sequence stores the given char.
     * @param seq the given binary sequence
     * @param letter the given char
     */
    public void put(BinarySequence seq, char letter) {
        HuffmanNode currentNode = root;
        for (int i = 0; i < seq.size(); i++) {
            // For each boolean in seq, find child node to move to
            if (seq.get(i)) {
                if (currentNode.getOne() == null) {
                    // The null case
                    HuffmanNode newNode = new HuffmanNode(null);
                    currentNode.setOne(newNode);
                }
                // Move to the one child node
                currentNode = currentNode.getOne();
            } else {
                if (currentNode.getZero() == null) {
                    // The null case
                    HuffmanNode newNode = new HuffmanNode(null);
                    currentNode.setZero(newNode);
                }
                // Move to the zero child node
                currentNode = currentNode.getZero();
            }
        }
        currentNode.setData(letter);
    }

    /**
     * This method should decode a BinarySequence into a string.
     * @param s the given binary sequence
     * @return the decoded string
     */
    public String decode(BinarySequence s) {
        StringBuilder str = new StringBuilder();
        HuffmanNode node = root;
        for (int i = 0; i < s.size(); i++) {
            if (s.get(i)) {
                // If the bit is true (1)
                node = node.getOne();
            } else if (!s.get(i)) {
                // If the bit is false (0)
                node = node.getZero();
            }
            // if arrive at a leaf, add the data from that leaf to output
            if (node.isLeaf()) {
                str.append(node.getData());
                // Reset node to the root
                node = root;
            }
        }
        return str.toString();
    }
}
