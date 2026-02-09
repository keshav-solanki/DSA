import java.util.LinkedList;
import java.util.Queue;

class Pair{
    int first;
    int second;
    Pair(int first, int second){
        this.first=first;
        this.second=second;
    }
}    

public class NumberOfIslands {
   public static void bfs(char[][] grid,int visited[][],int m, int n, int startRow, int startCol){
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(startRow,startCol));
        visited[startRow][startCol]=1;

        int deltaRow[]={-1,0,1,0};
        int deltaCol[]={0,1,0,-1};
        while(q.isEmpty()!=true){
            Pair curr = q.peek();
            int first = curr.first;
            int second = curr.second;
            q.poll();
            for(int i=0;i<4;i++){
                int nRow = first+ deltaRow[i];
                int nCol = second+ deltaCol[i];
                if(nRow >=0 && nRow<m && nCol >=0 && nCol<n && visited[nRow][nCol]!=1 && grid[nRow][nCol]=='1'){
                   visited[nRow][nCol]=1;
                   q.add(new Pair(nRow,nCol)); 
                }
            }
        }
    }
    public static void main(String[] args) {
        char[][] grid={{'1','1','1','1','0'},
                       {'1','1','0','1','0'},
                       {'1','1','0','0','0'},
                       {'0','0','0','0','0'}
                      };
        int m = grid.length;
        int n = grid[0].length;
        int visited[][]=new int[m][n];
        int count=0;
        for(int i=0; i<m;i++){
            for(int j=0; j<n; j++){
                if(visited[i][j]!=1 && grid[i][j]=='1'){
                    bfs(grid,visited,m,n,i,j);
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}

