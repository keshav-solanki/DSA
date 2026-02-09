import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Graph {
    public static void adjMat(Scanner sc){
        System.out.println("Enter the Vertex size: ");
        int n=sc.nextInt();
        System.out.println("Enter the Edges size: ");
        int m = sc.nextInt();
        int adjMatrix[][] = new int[n+1][n+1];
        for(int i=0;i<m;i++){
            int u,v;
            System.out.println("enter u");
            u=sc.nextInt();
            System.out.println("enter v");
            v=sc.nextInt();

            adjMatrix[u][v]=1;
            adjMatrix[v][u]=1;
        }
        

    }
    public static void adjL(Scanner sc){
        System.out.println("Enter the Vertex size: ");
        int n=sc.nextInt();
        System.out.println("Enter the Edges size: ");
        int m = sc.nextInt();
        ArrayList<Integer> adjList[] = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++){
            System.out.println("Enter the value of u");
            int u =sc.nextInt();
            System.out.println("Enter the value of v");
            int v =sc.nextInt();
            adjList[u].add(v);
            adjList[v].add(u); 
        }
        int visited[]= new int[n+1];
        for(int i=1; i<=n;i++){
            if(visited[i]!=1){
                bfs(adjList, visited,i);
               // dfs(adjList, visited, n);
            }
        }
    }
    public static void dfs(ArrayList<Integer> adjList[], int[] visited, int start){
        System.out.print(start+" \t");
        visited[start]=1;
        for(int i=0;i<adjList[start].size();i++){
            int neb= adjList[start].get(i);
            if(visited[neb]!=1){
                dfs(adjList, visited, neb);
            }
        }
    }
    public static void bfs(ArrayList<Integer> adjList[], int[] visited, int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start]=1;
        while (q.isEmpty()!=true) {
            int curr = q.peek();
            q.poll();
            System.out.print(curr+" \t");
            for(int i=0; i<adjList[curr].size();i++){
                int neb=adjList[curr].get(i);
                if(visited[neb]!=1){
                    q.add(neb);
                    visited[neb]=1;
                }
            }
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        adjL(sc);
    }
}
