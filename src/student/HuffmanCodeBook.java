package student;

import provided.BinarySequence;

// you may implement any interface you want here
public class HuffmanCodeBook {
    private char[] letters;
    private BinarySequence[] sequences;
    private int size;

    /**
     * A 0 argument constructor. When first created a HuffmanCodeBook
     * object contains no letters/sequences.
     */
    public HuffmanCodeBook() {
        letters = new char[1];
        sequences = new BinarySequence[1];
        size = 0;
    }

    /**
     * This function get the letters[] of HuffmanCodeBook.
     * @return the char array represent the Letters
     */
    public char[] getLetters() {
        return letters;
    }

    /**
     * This function get the size of HuffmanCodeBook.
     * @return the size of the code book
     */
    public int getSize() {
        return size;
    }

    /**
     * This method add a given character/letter and binary sequence
     * into the codeBook in small char - big char order.
     * @param c the given character
     * @param seq the given binary sequence
     */
    public void addSequence(char c, BinarySequence seq) {
        if (size == letters.length) {
            expandArray();
        }
        //Search the insert index at the small-big order.
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (c > letters[i]) {
                index++;
            }
        }
        //Move every element behind the insert index to the right
        for (int i = size; i > index; i--) {
            letters[i] = letters[i - 1];
            sequences[i] = sequences[i - 1];
        }
        //Insert the new element
        size++;
        letters[index] = c;
        sequences[index] = seq;
    }

    /**
     * This function double the index in an array.
     */
    private void expandArray() {
        BinarySequence[] newSequences = new BinarySequence[letters.length * 2];
        char[] newLetters = new char[letters.length * 2];
        //Copy elements in array.
        for (int i = 0; i < size; i++) {
            newLetters[i] = letters[i];
            newSequences[i] = sequences[i];
        }
        letters = newLetters;
        sequences = newSequences;
    }

    /**
     * This method should return true/false to indicate if the
     * codebook contains a given letter.
     * @param letter the given letter
     * @return true if the codebook contains a given letter
     */
    public boolean contains(char letter) {
        //The binary search method for runtime requirement
        int low = 0;
        int high = size - 1;
        int mid;
        while (low <= high) {
            mid = (high + low) / 2;
            if (letters[mid] < letter) {
                low = mid + 1;
            } else if (letters[mid] > letter) {
                high = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * This function can be used to see if a given
     * codeBook can handle a given piece of text.
     * @param str the input string
     * @return true only if every letter in input string contain the codebook
     */
    public boolean containsAll(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!contains(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method get the binary sequence associated with
     * the given letter using the binary search method.
     * @param c the given letter
     * @return binary sequence associated with the given letter
     */
    public BinarySequence getSequence(char c) {
        //The binary search method for runtime requirement
        int low = 0;
        int high = size - 1;
        int midIndex;
        while (low <= high) {
            midIndex = (low + high) / 2;
            char midChar = letters[midIndex];
            if (midChar < c) {
                low = midIndex + 1;
            } else if (midChar > c) {
                high = midIndex - 1;
            } else {
                return sequences[midIndex];
            }
        }
        return null;
    }

    /**
     * This function should encode the input string into a
     * binary sequence.
     * @param s the input string
     * @return a binary sequence that encode the input string
     */
    public BinarySequence encode(String s) {
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (getSequence(s.charAt(i)) != null) {
                encoded.append(getSequence(s.charAt(i)));
            }
        }
        return new BinarySequence(encoded.toString());
    }
}
