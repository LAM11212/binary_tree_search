public class BinarySearchTree {

    Node root;

    /**
     * inserts a node into a binary tree
     * @param node
     */
    public void insert(Node node) {

        root = insertHelper(root, node);
    }

    /**
     *
     * @param root
     * @param node
     * @return returns the root node after adding the new node into either the left/right branch.
     */
    private Node insertHelper(Node root, Node node) {

        int data = node.data;

        if(root == null) {
            root = node;
            return root;
        } else if(data < root.data) {
            root.left = insertHelper(root.left, node);
        } else {
            root.right = insertHelper(root.right, node);
        }
        return root;
    }

    public void display() {
        displayHelper(root);
    }
    private void displayHelper(Node root) {
        if(root != null) {
            displayHelper(root.left);
            System.out.println(root.data);
            displayHelper(root.right);
        }
    }
    public boolean search(int data) {
        return searchHelper(root, data);
    }
    private boolean searchHelper(Node root, int data) {
        if(root == null) {
            return false;
        } else if(root.data == data) {
            return true;
        } else if(root.data > data) {
            return searchHelper(root.left, data);
        } else {
            return searchHelper(root.right, data);
        }
    }

    /**
     * remove takes an int and removes it from the binary tree by searching for the node. Once the node is found
     * the successor method is called which finds a suitable node to replace the removed node with, once this is done
     * the node is removed.
     * @param data
     */
    public void remove(int data) {
        if(search(data)) {
            removeHelper(root, data);
        } else {
            System.out.println(data + " could not be found");
        }
    }
    private Node removeHelper(Node root, int data) {

        if(root == null) {
            return root;
        } else if(data < root.data) {
            root.left = removeHelper(root.left, data);
        } else if(data > root.data) {
            root.right = removeHelper(root.right, data);
        } else { // node found
            if(root.left == null && root.right == null) {
                root = null;
            } else if(root.right != null) { // find succesor to replace node
                root.data = successor(root);
                root.right = removeHelper(root.right, root.data);
            } else {// finds predecessor to replace node
                root.data = predecessor(root);
                root.left = removeHelper(root.left, root.data);
            }
        }
        return root;
    }
    private int successor(Node root) { // find least value below right child of root node
        root = root.right;
        while(root.left != null) {
            root = root.left;
        }
        return root.data;
    }
    private int predecessor(Node root) { // find greatest value below left child of root node
        root = root.left;
        while(root.right != null) {
            root = root.right;
        }
        return root.data;
    }

}
