import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * An Integer Binary Search Tree
 * @author: Your Name Here
 * @version: Date
 */

public class BST {
    private BSTNode root;

    public BSTNode getRoot() {
        return this.root;
    }

    public void setRoot(BSTNode root) {
        this.root = root;
    }

    /**
     * Sets up a binary search tree
     * with some default values
     */
    public void setupTestData() {
        this.root = new BSTNode(10);
        this.root.setLeft(new BSTNode(5));
        this.root.setRight(new BSTNode((15)));
        this.root.getLeft().setLeft(new BSTNode(3));
        this.root.getLeft().setRight(new BSTNode(9));
    }

    /**
     * Prints the provided ArrayList of nodes
     * in the form node1-node2-node3
     * @param nodes ArrayList of BSTNodes
     */
    public static void printNodes(ArrayList<BSTNode> nodes) {
        for(int i=0; i<nodes.size()-1; i++) {
            System.out.print(nodes.get(i) + "-");
        }
        System.out.println(nodes.get(nodes.size()-1));
    }

    /**
     * A function that searches for a value in the tree
     * @param val integer value to search for
     * @return true if val is in the tree, false otherwise
     */
    public boolean search(int val)
    {
        return find(val, root);
    }

    // Helper function for search function
    // Returns true if target value has been found
    public boolean find(int target, BSTNode bstRoot)
    {
        // Base case for if equal
        if (bstRoot.getVal() == target)
        {
            return true;
        }

        // Base case for if not equal
        /* This would mean that we are trying to recurse,
        but there is not an available node for us to go to */
        if (target < bstRoot.getVal() && bstRoot.getLeft() == null)
        {
            return false;
        }
        if (target > bstRoot.getVal() && bstRoot.getRight() == null)
        {
            return false;
        }

        // If not equal, check if the node is greater or less than the target
        // If the node is greater, then we recurse to the left
        // If the node is less, then we recurse to the right
        if (bstRoot.getVal() < target)
        {
            return find(target, bstRoot.getRight());
        }
        return find(target, bstRoot.getLeft());
    }

    /**
     * @return ArrayList of BSTNodes in inorder
     */
    public ArrayList<BSTNode> getInorder()
    {
        // Create ArrayList to hold values in order
        ArrayList<BSTNode> inOrderList = new ArrayList<>();

        inOrderList = inOrder(inOrderList, root);
        return inOrderList;
    }

    // Returns ArrayList of BSTNodes in order
    public ArrayList<BSTNode> inOrder(ArrayList<BSTNode> order, BSTNode root)
    {
        // First go as far to the left as possible
        if (root.getLeft() != null)
        {
            inOrder(order, root.getLeft());
        }

        // Then we document the root we have visited
        order.add(root);

        // Go as far right as possible
        if (root.getRight() != null)
        {
            inOrder(order, root.getRight());
        }

        return order;
    }

    /**
     * @return ArrayList of BSTNodes in preorder
     */
    public ArrayList<BSTNode> getPreorder()
    {
        // Create ArrayList to hold values in pre order
        ArrayList<BSTNode> preOrderList = new ArrayList<>();

        preOrderList = preOrder(preOrderList, root);
        return preOrderList;
    }

    // Returns ArrayList of BSTNodes in pre order
    public ArrayList<BSTNode> preOrder(ArrayList<BSTNode> order, BSTNode root)
    {
        // Add root first
        order.add(root);

        // Go as far left as possible
        if (root.getLeft() != null)
        {
            inOrder(order, root.getLeft());
        }

        // Go as far right as possible
        if (root.getRight() != null)
        {
            inOrder(order, root.getRight());
        }

        return order;
    }

    /**
     * @return ArrayList of BSTNodes in postorder
     */
    public ArrayList<BSTNode> getPostorder()
    {
        // Create ArrayList to hold values in post order
        ArrayList<BSTNode> postOrderList = new ArrayList<>();

        postOrderList = postOrder(postOrderList, root);
        return postOrderList;
    }

    // Returns ArrayList of BSTNodes in post order
    public ArrayList<BSTNode> postOrder(ArrayList<BSTNode> order, BSTNode root)
    {
        // First we go as far left as possible
        if (root.getLeft() != null)
        {
            inOrder(order, root.getLeft());
        }

        // Then we go as far right as possible
        if (root.getRight() != null)
        {
            inOrder(order, root.getRight());
        }

        // Then add the root to the ArrayList (to go backwards)
        order.add(root);

        return order;
    }

    /**
     * Inserts the given integer value to the tree
     * if it does not already exist. Modifies the
     * root instance variable to be the root of the new modified tree.
     * @param val The value ot insert
     */
    public void insert(int val)
    {
        /* We turn the int into a BSTNode because we can only set
         new values in the BSTNode root with another BSTNode */
        BSTNode insertVal = new BSTNode(val);

        traverse(insertVal, root);
    }

    /* Traverse does not return anything, it will instead
    insert val into the root BSTNode at the correct spot
     if val does not already exist in the BST */
    public void traverse(BSTNode val, BSTNode root)
    {
        // Check to see if val already exists in root
        if (root.getVal() == val.getVal())
        {
            return;
        }

        // Base case for if the node we are trying to go to does not exist
        // If this is the case then this is where we insert the value
        if (root.getVal() < val.getVal() && root.getRight() == null)
        {
            root.setRight(val);
            return;
        }
        if (root.getVal() > val.getVal() && root.getLeft() == null)
        {
            root.setLeft(val);
            return;
        }

        // Recursive step (similar to search method)
        if (root.getVal() > val.getVal())
        {
            traverse(val, root.getLeft());
        }
        if (root.getVal() < val.getVal())
        {
            traverse(val, root.getRight());
        }
    }

    /**
     * Determines if the current BST is
     * a valid BST.
     * @return true if valid false otherwise
     */
    public boolean isValidBST() {
        // TODO: Optional Challenge!
        return false;
    }

    public static void main(String[] args) {
        // Tree to help you test your code
        BST tree = new BST();
        tree.setupTestData();

        System.out.println("\nSearching for 15 in the tree");
        System.out.println(tree.search(15));

        System.out.println("\nSearching for 22 in the tree");
        System.out.println(tree.search(22));

        System.out.println("\nPreorder traversal of binary tree is");
        ArrayList<BSTNode> sol = tree.getPreorder();
        printNodes(sol);

        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);

        System.out.println("\nPostorder traversal of binary tree is");
        sol = tree.getPostorder();
        printNodes(sol);

        tree.insert(8);
        System.out.println("\nInorder traversal of binary tree is");
        sol = tree.getInorder();
        printNodes(sol);
    }
}
