import java.util.Scanner;

class Node{
    int data;
    Node left;
    Node right;
    Node(){
        left=null;
        right=null;
    }
}
public class BinarySerachTree {
    public static Node createTree(Node root,int d){
        if(root==null){
            Node temp = new Node();
            temp.data=d;
            return temp;
        }
        if(root.data<d){
            root.right=createTree(root.right, d);
        }
        else{
           root.left= createTree(root.left, d);
        }
        return root;
    }
    public static void inOrder(Node root){
        if(root==null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
    }
    public static void preOrder(Node root){
        if(root==null){
            return;
        }
        System.out.print(root.data+" ");
        preOrder(root.left);
        preOrder(root.right);
    }
    public static void postOrder(Node root){
        if(root==null){
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data+" ");
    }
    public static boolean search(Node root, int target){
        if(root==null){
            return false;
        }
        if(root.data==target){
            return true;
        }
        if(root.data<target){
            return search(root.right, target);
        }
        return search(root.left, target);
    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Node root=null;
        while(true){
            System.out.println("Enter the value :");
            int d = sc.nextInt();
            if(d==-1){
                break;
            }
            root=createTree(root, d);
        }
        System.out.println("Inorder Traversal: ");
        inOrder(root);
        System.out.println();
        System.out.println("Preorder Traversal: ");
        preOrder(root);
        System.out.println();
        System.out.println("Postorder Traversal: ");
        postOrder(root);
        System.out.println();
        System.out.println("Seaching in BST: ");
        System.out.println((search(root, 60)) ? "Element Found ": "Element Not Found");
    }
}
