import BST.BST;
import BST.Order;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
    public static void main(String[] args) {
        BST bst = new BST();
        if (bst.getRoot() == null) {
            System.out.println("The root of the bst is empty");
        } else {
            System.out.println("The root of the bst is not empty");
        }

        bst.insert(10);

        if (bst.getRoot() == null) {
            System.out.println("The root of the bst is empty");
        } else {
            System.out.printf("The root of the bst is: %d\n", bst.getRoot().getValue());
        }

        bst.insert(11);
        bst.insert(9);

        bst.show(bst.getRoot(), Order.IN);
    }
}