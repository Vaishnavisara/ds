import java.util.Scanner;

public class BinaryTree {

    private void inOrder(AVLTree root) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    class Node {
        int data;
        Node left, right, next;

        public Node(int value) {
            data = value;
            left = right = null;
            next = null;
        }
    }

    class Queues {
        Node head, tail;
        int size = 0;
        int queueSize = 20;
    }

    Node root;

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scanner = new Scanner(System.in);

        int choice = 0;
        while (choice != 5) {
            System.out.println("1. Add");
            System.out.println("2. DFS");
            System.out.println("3. SHOW DFS");
            System.out.println("4. BFS");
            System.out.println("5. Exit");
            System.out.println("enter the choice : ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the value to be added: ");
                    int value = scanner.nextInt();
                    tree.insertData(value);
                    break;
                case 2:
                    System.out.print("Enter the value to be found: ");
                    int val = scanner.nextInt();
                    boolean found = tree.DFS(tree.root, val);
                    if (!found) {
                        System.out.println("Value not found.");
                    }
                    break;
                case 3:
                    tree.showDFS(tree.root);
                    break;
                case 4:
                    tree.BFS(tree.root);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    // public void insertData(int data) {
    //     Node newNode = new Node(data);
    //     if (root == null) {
    //         root = newNode;
    //         return;
    //     }
    //     else{
    //     Queues queue = new Queues();
    //     enqueue(queue, root);
    
    //     while (true) {
    //         Node currentNode = dequeue(queue);
        
    //         if (currentNode.left == null) {
    //             currentNode.left = newNode;
    //             enqueue(queue, currentNode.left);
    //             break;
    //         } 
    //         else {
    //             enqueue(queue, currentNode.left);
    //         }
            
    //         if (currentNode.right == null) {
    //             currentNode.right = newNode;
    //             enqueue(queue, currentNode.right);
    //             break;
    //         } 
    //         else {
    //             enqueue(queue, currentNode.right);
    //         }
    //     }
    // }
    // }
    
    Queues queue = new Queues();
       public void insertData(int data) {
        Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
            enqueue(queue, root);
        }
        else{
            Node currentNode = peek(queue);
            if (currentNode.left == null) {
                currentNode.left = newNode;
                enqueue(queue, currentNode.left);
            }
            else{
                currentNode.right = newNode;
                enqueue(queue, currentNode.right); 
                dequeue(queue);
            }
      }
    }
    

    boolean DFS(Node root, int val) {
        if (root == null) {
            return false;
        }
        if (root.data == val) {
            System.out.println("Found value: " + val);
            return true;
        }
        return DFS(root.left, val) || DFS(root.right, val);
    }

    void showDFS(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            showDFS(root.left);
            showDFS(root.right);
        }
    }

    public void BFS(Node root) {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }

        Queues queue = new Queues();
        enqueue(queue, root);
        while (queue.head != null) {
            Node dequeuedNode = dequeue(queue);
            System.out.print(dequeuedNode.data + " ");

            if (dequeuedNode.left != null) {
                enqueue(queue, dequeuedNode.left);
            }

            if (dequeuedNode.right != null) {
                enqueue(queue, dequeuedNode.right);
            }
        }
    }

    public void enqueue(Queues queue, Node newNode) {
        if (queue.size == queue.queueSize) {
            System.out.println("Queue is full");
            return;
        }
        if (queue.head == null) {
            queue.head = newNode;
            queue.tail = newNode;
        } else {
            queue.tail.next = newNode;
            queue.tail = newNode;
        }
        queue.size++;
    }

    public Node dequeue(Queues queue) {
        if (queue.head == null) {
            System.out.println("Queue is empty");
            return null;
        }
        else{
            Node dequeuedNode = queue.head;
            queue.head = queue.head.next;
            queue.size--;
            return dequeuedNode;
        }
    }
    public Node peek(Queues queue){
        return queue.head;
    }
       
}