import java.util.Scanner;

class RedBlackTree {
    int data;
    RedBlackTree left;
    RedBlackTree right;
    RedBlackTree parent;
    Boolean colour = true;
     
    RedBlackTree(int data) {
        this.data = data;
    }
}

public class MainRBT {
    static RedBlackTree root;

    void preOrder(RedBlackTree root) {
        if (root != null) {
            System.out.println("data -> " + root.data);
            // left
            if (root.left != null) {
                System.out.println("left -> " + root.left.data);
            } else {
                System.out.println("left null");
            }

            // right
            if (root.right != null) {
                System.out.println("right -> " + root.right.data);
            } else {
                System.out.println("right null");
            }

            // parent
            if (root.parent != null) {
                System.out.println("parent -> " + root.parent.data);
            } else {
                System.out.println("parent null");
            }
            // uncle
            uncle(root);
            System.out.println("colour -> " + root.colour);
            System.out.println("_");

            preOrder(root.left);
            preOrder(root.right);
        }
    }

    RedBlackTree colourChange(RedBlackTree root) {     
        if (root.parent == null) {
            root.colour = false; 
            return root;
        }
        if (root.parent.parent == null) {
            return root;
        }
    
        RedBlackTree uncleNode = uncle(root);
        if (uncleNode != null && uncleNode.colour) {
            root.parent.colour = false;
            uncleNode.colour = false;
            root.parent.parent.colour = true;
        } 
        if(uncleNode == null){
            // left left
            if (root.parent == root.parent.parent.left && root == root.parent.left) {
                rightRotate(root.parent.parent);
                root.parent.colour = false;
                root.parent.right.colour = true; 

            } 

            // right right
            else if (root.parent == root.parent.parent.right && root == root.parent.right) {
                leftRotate(root.parent.parent);
                root.parent.colour = false;
                root.parent.parent.colour = true;
            } 
            
            // left right
            else if (root.parent == root.parent.parent.left && root == root.parent.right) {
                leftRotate(root.parent);
                rightRotate(root.parent.parent);
                root.colour = false;
                root.parent.parent.colour = true;
            } 
            
            // right left 
            else if (root.parent == root.parent.parent.right && root == root.parent.left) {
                rightRotate(root.parent);
                leftRotate(root.parent.parent);
                root.colour = false;
                root.parent.parent.colour = true;
            }
        }
        
        return root;
    }
    
    public RedBlackTree insertData(int val, RedBlackTree node) {
        if (node == null) {
            RedBlackTree newNode = new RedBlackTree(val);
            System.out.println(val + " was successfully added.");
            return newNode;
        } else {
            if (val < node.data) {
                node.left = insertData(val, node.left);
                node.left.parent = node;
                colourChange(node.left);
            } 
            else if (val > node.data) { 
                node.right = insertData(val, node.right);
                node.right.parent = node;
                colourChange(node.right);
            } 
            else {
                System.out.println(val + " already exists.");
            }
            return node;
        }
    }

    RedBlackTree uncle(RedBlackTree root) {
        if (root == null || root.parent == null || root.parent.parent == null) {
            System.out.println("uncle is null");
            return null;
        }
        if (root.parent == root.parent.parent.left) {
            if (root.parent.parent.right != null) {
                System.out.println("uncle: " + root.parent.parent.right.data);
                return root.parent.parent.right;
            } else {
                System.out.println("uncle is null");
                return null;
            }
        } 
        else {
            if (root.parent.parent.left != null) {
                System.out.println("uncle: " + root.parent.parent.left.data);
                return root.parent.parent.left;
            } else {
                System.out.println("uncle is null");
                return null;
            }
        }
    }
    

    RedBlackTree leftRotate(RedBlackTree root) {
        RedBlackTree newRoot = root.right;
        newRoot.parent = null;
        root.right = newRoot.left;

        if (root.right != null) {
            root.right.parent = root;
        }

        newRoot.left = root;
        root.parent = newRoot;
        return newRoot;
    }

    RedBlackTree rightRotate(RedBlackTree root) {
        RedBlackTree newRoot = root.left;
        newRoot.parent = null;    // newRoot.parent = rroot.parent;
        root.left = newRoot.right;

        if (root.left != null) {
            root.left.parent = root;
        }

        newRoot.right = root;
        root.parent = newRoot;
        return newRoot;
    }


    public static void main(String[] args) {
        MainRBT B1 = new MainRBT();
        int userInput;
        Scanner textField = new Scanner(System.in);

        do {
            System.out.println("---------------Binary Tree----------------");
            System.out.println("1 -> Insert");
            System.out.println("2 -> Pre-order");
            System.out.println("3 -> Left Rotate");
            System.out.println("4 -> Right Rotate");
            System.out.println("5 -> uncleRed");
            System.out.println("6 -> Exit");
            System.out.print("Enter Your Option: ");

            userInput = textField.nextInt();

            switch (userInput) {
                case 1:
                    System.out.print("Enter the insert value: ");
                    int insertValue = textField.nextInt();
                    root = B1.insertData(insertValue, root);
                    root.colour = false; 
                    break;
                case 2:
                    B1.preOrder(root);
                    break;
                case 3:
                    root = B1.leftRotate(root);
                    break;
                case 4:
                    root = B1.rightRotate(root);
                    break;
                case 5:
                    root = B1.colourChange(root);
                    break;
                default:
                    System.out.println("Wrong choice");
                    break;
            }
        } while (userInput != 6);
        textField.close();
    }
}