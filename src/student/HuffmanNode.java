package student;


public class HuffmanNode {
    private Character data;
    private HuffmanNode one;
    private HuffmanNode zero;

    /**
     * A constructor that makes a non-leaf node
     * by providing its two child nodes.
     * @param zero the 'zero' child nodes
     * @param one the 'one' child nodes
     */
    public HuffmanNode(HuffmanNode zero, HuffmanNode one) {
        this.one = one;
        this.zero = zero;
        data = null;
    }

    /**
     * A constructor that makes a leaf node, specifying the data.
     * @param data the data in current node
     */
    public HuffmanNode(Character data) {
        this.data = data;
        one = null;
        zero = null;
    }

    /**
     * The getter methods for the private variables.
     * @return data
     */
    public Character getData() {
        return data;
    }

    /**
     * The setter methods for the private variables.
     * @param data the char in current node
     */
    public void setData(Character data) {
        this.data = data;
    }

    /**
     * Getter.
     * @return one child note
     */
    public HuffmanNode getOne() {
        return one;
    }

    /**
     * Setter.
     * @param one one child node
     */
    public void setOne(HuffmanNode one) {
        this.one = one;
    }

    /**
     * Getter.
     * @return zero child node
     */
    public HuffmanNode getZero() {
        return zero;
    }

    /**
     * Setter.
     * @param zero the zero child node
     */
    public void setZero(HuffmanNode zero) {
        this.zero = zero;
    }

    /**
     * This function check if the node is a leaf in tree.
     * @return true if a Huffman code tree has data
     */
    public boolean isLeaf() {
        return data != null;
    }

    /**
     * This function check if this node is “valid”
     * for a huffman code tree.
     * @return true if it's valid
     */
    public boolean isValidNode() {
        return data != null && one == null && zero == null
                || data == null && one != null && zero != null;
    }

    /**
     * This function check if this node and all descendant nodes
     * are valid for a Huffman coding tree.
     * @return true if all descendant nodes are valid
     */
    public boolean isValidTree() {
        if (isValidNode()) { //Check the current node
            if ((zero == null) || zero.isValidTree()) { // Check the zero subtree
                return (one == null) || one.isValidTree(); // Check the one subtree
            }
        }
        return false;
    }
}
