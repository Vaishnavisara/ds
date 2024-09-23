import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class AVLTree {
    int data;
    AVLTree left;
    AVLTree right;
    int level = 0;

    public AVLTree(int data,int level){
        this.data=data;
        this.level =level;
    }
}


class  BinaryTree{
    static AVLTree root;
    public BinaryTree(){
        this.root=null;
    }

void preOrder(AVLTree root){
    if(root!=null){
        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }
}

void insertData(int val) {
    AVLTree newNode = new AVLTree(val, 0);
    Queue<AVLTree> queue = new LinkedList<>();
    Boolean isinserted =true;
    if (root == null) {
        root = newNode;
        root.level = 3; 
        System.out.println(" root created Successfully");
        System.out.println("the nodes level "+root.level);
    } 
    else if (root.data == val) {
        System.out.println("The root value already exists");
    } 
    else {
        queue.add(root);

        while (true) {
            AVLTree curr = queue.poll();

            if (val < curr.data) {
                if (curr.left == null) {
                    curr.left = newNode;
                    if(isinserted){
                    newNode.level = curr.level - 1; 
                    isinserted = false;
                    }
                    System.out.println("value inserted in the left ");
                    System.out.println("the node's level  " + newNode.level);
                    break;
                } else {
                    queue.add(curr.left);

                }
            } else {
                if (curr.right == null) {
                    curr.right = newNode;
                    if(isinserted){
                    newNode.level = curr.level - 1;
                    isinserted = false;
                    }
                    System.out.println("value inserted in the right ");
                    System.out.println("the node's level " + newNode.level);
                    break;
                } else {
                    queue.add(curr.right);
                }
            }
        }
    }
}



    public static void main(String[] args) {
        BinaryTree B1 = new BinaryTree();
        
        int userInput;
        Scanner textFeild = new Scanner(System.in);
        do 
        { 
            System.out.println("---------------Binary Tree----------------");
            System.out.println("1 -> Insert Data");
            System.out.println("2 -> Show Inorder");
            System.out.println("3 -> Show Preorder");
            System.out.println("4 -> Show Postorder");
            System.out.println("5 -> Delete Data");
            System.out.println("6 -> Update");
            System.out.println("7 -> Exit");
            System.out.print("Enter Your Option : ");

            userInput = textFeild.nextInt();

            switch (userInput) {
                case 1:
                    System.out.print("Enter the insert value : ");
                    int insertValue = textFeild.nextInt();
                    B1.insertData(insertValue);
                    break;
                case 2:
                    B1.preOrder(root);
                    break;
                default:
                    throw new AssertionError();
            }
        } while (userInput!=7);   
    }
}
