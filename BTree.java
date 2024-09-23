import java.util.Scanner;

class BTree {

    int data;
    int height;
    BTree left;
    BTree right;

    public BTree(int data) {
        this.data = data;
        this.height = 1;
    }
}

class AVL {

    static BTree root;
    boolean isDelete;
    boolean isAddAlreadyData;

    public AVL() {
        this.root = null;
    }

    void inOrder(BTree root) {
        if (root != null) {
            inOrder(root.left);
            System.out.println("height of " + root.data + " is " + root.height);
            inOrder(root.right);
        }
    }

    void preOrder(BTree root) {
        if (root != null) {
            System.out.println(root.data);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    void postOrder(BTree root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.data);
        }
    }

    BTree insertData(int val, BTree node) {
        if (node == null) {
            BTree newNode = new BTree(val);
            System.out.println("Successfully added");
            return newNode;
        } else {
            if (val < node.data) {
                node.left = insertData(val, node.left);
            } else if (val > node.data) {
                node.right = insertData(val, node.right);
            } else {
                System.out.println("The value already exists");
                return node;
            }
            heightCheck(node);
            return node;
        }
    }

    static BTree rotateRight(BTree node) {
        BTree newRoot = node.left;
        BTree leftNode = newRoot.right;
        if (leftNode == null) {
            newRoot.right = node;
            node.left = null;
        } else {
            node.left = leftNode;
            newRoot.right = node;
        }
        heightCheck(node);
        return newRoot;
    }

    static BTree rotateLeft(BTree node) {
        BTree newRoot = node.right;
        BTree rightNode = newRoot.left;
        if (rightNode == null) {
            newRoot.left = node;
            node.right = null;
        } else {
            node.right = rightNode;
            newRoot.left = node;
        }
        heightCheck(node);
        return newRoot;
    }

    static BTree leftRightRotate(BTree node) {
        BTree leftNode = node.left;
        BTree newRoot = leftNode.right;
        if (newRoot.right == null) {
            node.left = null;
        } else {
            node.left = newRoot.right;
        }
        if (newRoot.left == null) {
            leftNode.right = null;
        } else {
            leftNode.right = newRoot.left;
        }
        newRoot.left = leftNode;
        newRoot.right = node;
        heightCheck(node);
        heightCheck(leftNode);
        heightCheck(newRoot);
        return newRoot;
    }

    static void heightCheck(BTree node) {
        int leftNodeHeight = node.left == null ? 0 : node.left.height;
        int rightNodeHeight = node.right == null ? 0 : node.right.height;
        node.height = Math.max(leftNodeHeight, rightNodeHeight) + 1;
    }

    public void update(int replaceValue, int newValue) {
        deleteNode(root, replaceValue);
        if (isDelete) {
            insertData(newValue, root);
            if (isAddAlreadyData) {
                insertData(replaceValue, root);
            }
        } else {
            System.out.println("The delete value is not in the list");
        }
    }

    public int getBalance(BTree node) {
        if (node == null) return 0;
        int leftHeight = (node.left == null) ? 0 : node.left.height;
        int rightHeight = (node.right == null) ? 0 : node.right.height;
        return leftHeight - rightHeight;
    }

    public BTree deleteNode(BTree root, int delValue) {
        isDelete = false;
        if (root == null) return null;

        if (delValue < root.data) {
            root.left = deleteNode(root.left, delValue);
        } else if (delValue > root.data) {
            root.right = deleteNode(root.right, delValue);
        } else {
            isDelete = true;
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            BTree currentNode = root.right;
            while (currentNode.left != null) currentNode = currentNode.left;

            root.data = currentNode.data;
            root.right = deleteNode(root.right, currentNode.data);
        }

        if (root != null) {
            root.height = Math.max(
                    (root.left == null) ? 0 : root.left.height,
                    (root.right == null) ? 0 : root.right.height) + 1;

            int balance = getBalance(root);
            if (balance > 1 && getBalance(root.left) > 0) {
                return rotateRight(root);
            }
            if (balance > 1 && getBalance(root.left) < 0) {
                root.left = rotateLeft(root.left);
                return rotateRight(root);
            }
            if (balance < -1 && getBalance(root.right) < 0) {
                return rotateLeft(root);
            }
            if (balance < -1 && getBalance(root.right) > 0) {
                root.right = rotateRight(root.right);
                return rotateLeft(root);
            }
        }

        return root;
    }

    public static void main(String[] args) {
        AVL B1 = new AVL();
        int userInput;
        Scanner textFeild = new Scanner(System.in);

        do {
            System.out.println("---------------Binary Tree----------------");
            System.out.println("1 -> Insert Data");
            System.out.println("2 -> Show Inorder");
            System.out.println("3 -> Show Preorder");
            System.out.println("4 -> Show Postorder");
            System.out.println("5 -> Delete Node");
            System.out.println("6 -> Update Value");
            System.out.println("7 -> Exit");
            System.out.print("Enter Your Option: ");

            userInput = textFeild.nextInt();

            switch (userInput) {
                case 1:
                    System.out.print("Enter the insert value: ");
                    int insertValue = textFeild.nextInt();
                    if (root == null) {
                        BTree newNode = new BTree(insertValue);
                        root = newNode;
                    } else {
                        B1.insertData(insertValue, root);
                    }
                    break;
                case 2:
                    B1.inOrder(root);
                    break;
                case 3:
                    B1.preOrder(root);
                    break;
                case 4:
                    B1.postOrder(root);
                    break;
                case 5:
                    System.out.print("Enter the delete value: ");
                    int deleteValue = textFeild.nextInt();
                    B1.root = B1.deleteNode(B1.root, deleteValue);
                    break;
                case 6:
                    System.out.print("Enter the replace value: ");
                    int replaceVal = textFeild.nextInt();
                    System.out.print("Enter the new value: ");
                    int newVal = textFeild.nextInt();
                    B1.update(replaceVal, newVal);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.err.println("Invalid choice!");
            }
        } while (userInput != 7);

        textFeild.close();
    }
}
