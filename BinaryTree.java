import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class Node{
    int data;
    Node left;
    Node right;
    Node(){
        left=null;
        right=null;
    }
}
class Pair{
    Node temp;
    int line;
}
class Pair2{
    Node temp;
    Node parent;
}
public class BinaryTree {

    public static Node buildTree(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the val");
        int d = sc.nextInt();
        if(d==-1){
            return null;
        }

        Node root =new Node();
        root.data=d;
        root.left= buildTree();
        root.right=buildTree();
        return root;

    }
    public static void preOrder(Node root){
        if(root==null){
            return ;
        }
        System.out.print(root.data+"  ");
        preOrder(root.left);
        preOrder( root.right);
    }
    public static void inOrder(Node root){
        if(root==null){
            return ;
        }
        inOrder(root.left);
        System.out.print(root.data+"  ");
        inOrder( root.right);
    }
    public static void postOrder(Node root){
        if(root==null){
            return ;
        }
        postOrder(root.left);
        postOrder( root.right);
        System.out.print(root.data+"  ");

    }
    public static void levelOrder(Node root){
       Queue<Node> q = new LinkedList<>();
       q.add(root);

       while(q.isEmpty()!=true){
        
        Node a = q.peek();
        q.poll();
        System.out.print(a.data+"  ");
        if(a.left!=null){
            q.add(a.left);
        }
        if(a.right!=null){
            q.add(a.right);
        }
       }
    }
    public static Node createTree(Node root,int d){
        Node temp = new Node();
        temp.data=d;
        if(root==null){
            return temp;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(q.isEmpty()!=true){
            Node curr=q.peek();
            q.poll();
            if(curr.left==null){
                curr.left=temp;
                return root;
            }
            else{
                q.add(curr.left);
            }
            if(curr.right==null){
                curr.right=temp;
                return root;
            }
            else{
                q.add(curr.right);
            }
        }
        return root;
    }
    public static void  inOrderByItr(Node root){
        Stack<Node> s = new Stack<>();
        Node curr = root;
        while(curr!=null||s.isEmpty()!=true){
            while(curr!=null){
                s.push(curr);
                curr=curr.left;
            }
            curr=s.peek();
            s.pop();
            System.out.print(curr.data+"  ");
            curr=curr.right;
        }
    }
    public static void preOrderByItr(Node root){
        Stack<Node> s = new Stack<>();
        s.push(root);
        while(s.isEmpty()!=true){
            Node cur=s.peek();
            s.pop();
            System.out.print(cur.data+"  ");
            if(cur.right!=null){
                s.add(cur.right);
            }
            if(cur.left!=null){
                s.add(cur.left);
            }
        }
    }
    
    public static void postOrderByItrWith2Stack(Node root) {
        Stack<Node> s1= new Stack<>();
        Stack<Node> s2= new Stack<>();
        s1.push(root);
        while(s1.isEmpty()!=true){
            Node curr= s1.peek();
            s1.pop();
            s2.push(curr);
            if(curr.left!=null){
                s1.push(curr.left);
            }
            if(curr.right!=null){
                s1.push(curr.right);
            }
        }
        while(s2.isEmpty()!=true){
            Node curr= s2.peek();
            s2.pop();
            System.out.print(curr.data+ "  ");
        }  
    }
    public static void postOrderByItr(Node root){
        Stack<Node> s = new Stack<>();
        Node curr = root;
        while(true){
            while(curr!=null){
                s.push(curr);
                s.push(curr);
                curr= curr.left;
            }
            if(s.isEmpty()==true){
                return;
            }
            curr = s.peek();
            s.pop();
            if(s.isEmpty()!=true && s.peek()==curr){
                curr=curr.right;
            }
            else{
                System.out.print(curr.data+ "  ");
                curr=null;
            }
        }
    }
    public static void zigzagLevelOrder(Node root){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        boolean flag=true; 
        while (q.isEmpty()!=true) {
            int s =q.size();
            int i=0;
            List<Integer> temp = new ArrayList<>(s);
            while(s>0){
                Node curr = q.peek();
                q.poll();
                if(flag==true) {
                    temp.add(curr.data);
                }
                else{
                    temp.add(0, curr.data);
                }
                i++;
                if(curr.left!=null){
                    q.add(curr.left);
                }
                if(curr.right!=null){
                    q.add(curr.right);
                }
                s--;
               
            }
            for(int j=0;j<temp.size();j++) {
                System.out.print(temp.get(j)+"  ");
            }
            flag =!flag;
        }
    }
    public static boolean isCousins(Node root, int x, int y){
        Queue<Pair2> q = new LinkedList<>();
        q.add(new Pair2(root,))
    }
    public static void verticalOrder(Node root){
        Queue<Pair> q = new LinkedList<>(); 
        HashMap<Integer,ArrayList<Integer>> hs = new HashMap<>();
        Pair p = new Pair();
        p.temp=root;
        p.line=0;
        q.add(p);
        int mn=0;
        int mx=0;
        while(q.isEmpty()!=true){
            Pair curr =q.peek();
            q.poll();
            Node temp = curr.temp;
            int line = curr.line;
            if(!hs.containsKey(line)){
                hs.put(line, new ArrayList<>());
            }
            hs.get(line).add(temp.data);
            if(temp.left!=null){
                Pair ps = new Pair();
                ps.temp=temp.left;
                ps.line=line-1;
                q.add(ps);
            }
             if(temp.right!=null){
                Pair ps = new Pair();
                ps.temp=temp.right;
                ps.line=line+1;
                q.add(ps);
            }
            mn = Math.min(mn, line);
            mx = Math.max(mx, line);
        }
        // for(HashMap.Entry<Integer,ArrayList<Integer>> entry: hs.entrySet()){
        //     ArrayList<Integer> lst = entry.getValue();
        //     for(int i=0; i<lst.size(); i++){
        //         System.out.print(lst.get(i)+"  ");
        //     }
        // }
        for(int i=mn; i<=mx; i++){
            System.out.print(hs.get(i)+ "  ");
        }
    }
    public static void leftView(Node root){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!(q.isEmpty())){
            boolean flag=true;
            int s =q.size();
            while(s>0){
                Node curr= q.peek();
                q.poll();
                if(flag==true){
                    System.out.print(curr.data+"  ");
                    flag=false;
                }
                if(curr.left!=null){
                    q.add(curr.left);
                }
                if(curr.right!=null){
                    q.add(curr.right);
                }
                s--;
            }
        }
    }
    public static void rightView(Node root){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!(q.isEmpty())){
            int s =q.size();
            while(s>0){
                Node curr= q.peek();
                q.poll();
                if(s==1){
                    System.out.print(curr.data+ "  ");
                }
                if(curr.left!=null){
                    q.add(curr.left);
                }
                if(curr.right!=null){
                    q.add(curr.right);
                }
                s--;
            }
        }
    }
    public static void bottomView(Node root){
        Queue<Pair> q = new LinkedList<>();
        HashMap<Integer,Integer> hs = new HashMap<>();
        Pair p = new Pair();
        p.temp=root;
        p.line=0;
        q.add(p);
        int mn=0;
        int mx=0;
        while(q.isEmpty()!=true){
            Pair curr =q.peek();
            q.poll();
            Node temp = curr.temp;
            int line = curr.line;
            hs.put(line, temp.data);
            if(temp.left!=null){
                Pair ps = new Pair();
                ps.temp=temp.left;
                ps.line=line-1;
                q.add(ps);
            }
             if(temp.right!=null){
                Pair ps = new Pair();
                ps.temp=temp.right;
                ps.line=line+1;
                q.add(ps);
            }
            mn = Math.min(mn, line);
            mx = Math.max(mx, line);
        }
        for(int i=mn;i<=mx;i++){
            System.out.print(hs.get(i)+ " ");
        }
    }
    public static void topView(Node root){
        Queue<Pair> q = new LinkedList<>();
        HashMap<Integer,Integer> hs = new HashMap<>();
        Pair p = new Pair();
        p.temp=root;
        p.line=0;
        q.add(p);
        int mn=0;
        int mx=0;
        while(q.isEmpty()!=true){
            Pair curr =q.peek();
            q.poll();
            Node temp = curr.temp;
            int line = curr.line;
            if(!hs.containsKey(line)){
                hs.put(line, temp.data);
            }
            
            if(temp.left!=null){
                Pair ps = new Pair();
                ps.temp=temp.left;
                ps.line=line-1;
                q.add(ps);
            }
             if(temp.right!=null){
                Pair ps = new Pair();
                ps.temp=temp.right;
                ps.line=line+1;
                q.add(ps);
            }
            mn = Math.min(mn, line);
            mx = Math.max(mx, line);
        }
        for(int i=mn;i<=mx;i++){
            System.out.print(hs.get(i)+ " ");
        }
    }
    public static void printAllLeaf(Node root){
       Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!(q.isEmpty())){
            int s =q.size();
            while(s>0){
                Node curr= q.peek();
                q.poll();
                if(curr.left==null && curr.right==null){
                    System.out.print(curr.data+"  ");
                }
                if(curr.left!=null){
                    q.add(curr.left);
                }
                if(curr.right!=null){
                    q.add(curr.right);
                }
                s--;
            }
        }
    }
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
         //Node root = buildTree();
         Node root=null;
         
        Scanner sc = new Scanner(System.in);
        for(int i=0; i<6;i++){
            System.out.println("Enter the val");
            int d = sc.nextInt();
            root = createTree(root, d);
        }
        System.out.println("Inorder Traversal: " );
        bt.inOrder(root);
        System.out.println();
        System.out.println("Preorder Traversal: ");
        bt.preOrder(root);
        System.out.println();
        System.out.println("Postorder Traversal: ");
        bt.postOrder(root);
        System.out.println();
        System.out.println("Levelorder Traversal: ");
        bt.levelOrder(root);
        System.out.println();
        System.out.println("Inorder Traversal By Iterative Approarch: ");
        bt.inOrderByItr(root);
        System.out.println();
        System.out.println("Preorder Traversal By Iterative Approarch: ");
        bt.preOrderByItr(root);
        System.out.println();
        System.out.println("Postorder Traversal By Iterative Approarch with 2 Stack: ");
        bt.postOrderByItrWith2Stack(root);
        System.out.println();
        System.out.println("Postorder Traversal By Iterative Approarch: ");
        bt.postOrderByItr(root);
        System.out.println();
        System.out.println("Zigzag levelorder Traversal: ");
        bt.zigzagLevelOrder(root);
        System.out.println();
        System.out.println("Vertical Order Traversal: ");
        bt.verticalOrder(root);
        System.out.println();
        System.out.println("Left View of Binary Tree ");
        bt.leftView(root);
        System.out.println();
        System.out.println("Right View of Binary Tree ");
        bt.rightView(root);
        System.out.println();
        System.out.println("Bottom View: ");
        bt.bottomView(root);
        System.out.println();
        System.out.println("Top View ");
        bt.topView(root);
        System.out.println();
        System.out.println("All Leaf Node ");
        bt.printAllLeaf(root);
    }
}
