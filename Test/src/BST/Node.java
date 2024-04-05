package BST;

public class Node {
    private int value;
    Node left, right, parent;

    public Node(int value) {
        this.value = value;
        parent = left = right = null;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}