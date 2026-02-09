import java.util.LinkedList;
import java.util.Queue;

class Pair{
    int row;
    int col;
    Pair(int row, int col){
        this.row = row;
        this.col = col;
    }
}    

public class FloodFill {
    public static void dfs(int[][] image,int[][] grid,int deltaRow[],int deltaCol[],int m, int n, int sr, int sc, int color,int intialColor){

        grid[sr][sc]=color;
        for(int i=0; i<4; i++){
            int nRow = sr+ deltaRow[i];
            int nCol = sc+ deltaCol[i];

            if(nRow>=0 && nRow<m && nCol>=0 && nCol<n && grid[nRow][nCol]==intialColor && grid[nRow][nCol]!=color){
                grid[nRow][nCol]=color;
                dfs(image,grid,deltaRow,deltaCol,m,n,nRow,nCol,color,intialColor);
            }
        }
    }
     public static void bfs(int[][] image,int[][] grid,int deltaRow[],int deltaCol[],int m, int n, int sr, int sc, int color, int intialColor){
        grid[sr][sc]=color;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(sr,sc));
        
        while(q.isEmpty()!=true){
            Pair curr = q.peek();
            int row = curr.row;
            int col = curr.col;
            q.poll();

            for(int i=0; i<4; i++){
                int nRow = row+ deltaRow[i];
                int nCol = col+ deltaCol[i];

                if(nRow>=0 && nRow<m && nCol>=0 && nCol<n && grid[nRow][nCol]==intialColor && grid[nRow][nCol]!=color){
                    grid[nRow][nCol]=color;
                    q.add(new Pair(nRow,nCol));
                }
            }
        }

    }
    public static void main (String [] args) {
        int[][] image ={{1,1,1},
                        {1,1,0},
                        {1,0,1}
                        };
        int sc =1;
        int sr=1;
        int color=2;                
        int[][] grid = image;
        int m = image.length;
        int n = image[0].length;
        int deltaRow[] ={-1,0,1,0};
        int deltaCol[] ={0,1,0,-1};
        int intialColor =grid[sr][sc];
        //bfs(image, grid, deltaRow, deltaCol, m, n, sr, sc, color, intialColor);
        dfs(image, grid, deltaRow, deltaCol, m, n, sr, sc, color, intialColor);
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                System.out.print(grid[i][j]+ " ");
            }
            System.out.println();
        }
   }
}

