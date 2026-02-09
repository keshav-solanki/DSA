class Node{
    int data;
    Node left;
    Node right;
    int height;
    Node(int d){
        data=d;
        left=null;
        right=null;
        height=1;
    }
}
public class AVLTree {
    public static int getHeight(Node root){
        if(root==null){
            return 0;
        }
        return root.height;
    }
    public static int getBalanced(Node root){
        if(root==null){
            return 0;
        }
        return getHeight(root.left) - getHeight(root.right);
    }
    public static Node rightRotation(Node y){
        Node x = y.left;
        Node t2 = x.right;

        x.right=y;
        y.left=t2;
        x.height=Math.max(getHeight(x.left), getHeight(x.right))+1;
        y.height=Math.max(getHeight(y.left), getHeight(y.right))+1;
        return x;
    }
    public static Node leftRotation(Node x){
        Node y = x.right;
        Node t2=y.left;

        y.left=x;
        x.right=t2;
        x.height=Math.max(getHeight(x.left), getHeight(x.right))+1;
        y.height=Math.max(getHeight(y.left), getHeight(y.right))+1;
        return y;
    }
    public static void preOrder(Node root){
        if(root==null){
            return;
        }
        System.out.print(root.data+" ");
        preOrder(root.left);
        preOrder(root.right);
    }
    public static Node createAVLTree(Node root,int d){
        if(root==null){
            Node temp = new Node(d);
            return temp;
        }
        else if (root.data<d) {
           root.right= createAVLTree(root.right, d); 
        }
        else{
            root.left= createAVLTree(root.left, d);
        }

        root.height = Math.max(getHeight(root.left), getHeight(root.right))+1;
        int balance = getBalanced(root);

        if(balance>1 && root.left.data>d){
            return rightRotation(root);
        }
        if(balance<-1 && root.right.data<d){
            return leftRotation(root);
        }
        if(balance>1 && root.left.data<d){
            root.left= leftRotation(root.left);
            return rightRotation(root);
        }
        if(balance<-1 && root.right.data>d){
            root.right= rightRotation(root.right);
            return leftRotation(root);
        }
        
        return root;
    }
    public static void main(String[] args) {
        Node root = null;
        root = createAVLTree(root, 50);
        root = createAVLTree(root, 40);
        root = createAVLTree(root, 45);
        root = createAVLTree(root, 70);
        root = createAVLTree(root, 90);
        root = createAVLTree(root, 30);
        root = createAVLTree(root, 95);
        root = createAVLTree(root, 170);
        root = createAVLTree(root, 20);
        preOrder(root);
    }
}
