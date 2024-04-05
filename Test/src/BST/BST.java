package BST;

public class BST {
    Node root;
    public BST() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    public boolean isEmptyTree(Node node) {
        return node == null;
    }

    private Node createNode(int value) {
        return new Node(value);
    }

    private void insertAux(Node node, int value) {
        if (value <= node.getValue()) {
            if (node.left == null) {
                node.left = createNode(value);
                node.left.parent = node;
            } else {
                insertAux(node.left, value);
            }
        } else {
            if (node.right == null) {
                node.right = createNode(value);
                node.right.parent = node;
            } else {
                insertAux(node.right, value);
            }
        }
    }

    public void insert(int value) {
        if (isEmptyTree(root)) root = createNode(value);
        else
            insertAux(root, value);
    }

    private void showPreOrder(Node node) {
        if (isEmptyTree(node)) {
            System.out.print(" - ");
            return;
        }

        System.out.print(node.getValue());
        showPreOrder(node.left);
        showPreOrder(node.right);

    }

    private void showPostOrder(Node node) {
        if (isEmptyTree(node)) {
            System.out.print(" - ");
            return;
        }

        showPostOrder(node.left);
        showPostOrder(node.right);
        System.out.print(node.getValue());
    }

    private void showInOrder(Node node) {
        if (isEmptyTree(node)) {
            System.out.print(" - ");
            return;
        }

        showInOrder(node.left);
        System.out.print(node.getValue());
        showInOrder(node.right);
    }

    public void show(Node root, Order order) {
        System.out.print("[ ");
        switch (order) {
            case IN -> showInOrder(root);
            case PRE -> showPreOrder(root);
            case POST -> showPostOrder(root);
        }
        System.out.print(" ]");
    }

    public boolean find(Node node, int value) {
        if (node == null) return false;

        if (value == node.getValue()) return true;

        if (value < node.getValue()) return find(node.left, value);
        else return find(node.right, value);
    }

    public Node extractNode(Node node, int value) {
        if (value == node.getValue()) return node;

        if (value < node.getValue()) return extractNode(node.left, value);
        else return extractNode(node.right, value);
    }

    public Position checkPosition(Node node) {
        if (node.parent == null) return Position.NONE;

        if (node.parent.left == node) return Position.LEFT;
        else return Position.RIGHT;
    }
}