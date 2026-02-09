import java.util.LinkedList;
import java.util.Queue;

class Pair{
    int row;
    int col;
    int time;
    Pair(int row,int col,int time){
        this.row=row;
        this.col=col;
        this.time=time;
    }
}
class RotterOranges {
    public static void main(String[] args) {
        int grid [][]={{2,1,1},
                       {1,1,0},
                       {0,1,1}
                       };
        int visited[][]= grid;
        int m = grid.length;
        int n = grid[0].length;
        int deltaRow[] = {-1,0,1,0};
        int deltaCol[] = {0,1,0,-1};
        int count=0;
        int rotterCount=0;
        int tm=0;
        Queue<Pair> q = new LinkedList<>();

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]==2){
                    q.add(new Pair(i,j,0));
                }
                if(grid[i][j]==1){
                    count++;
                }
            }
        }
        while(q.isEmpty()!=true){
            Pair curr = q.peek();
            int row =curr.row;
            int col = curr.col;
            int time = curr.time;
            q.poll();
            tm = Math.max(tm,time);

            for(int i=0; i<4; i++){
                int nRow = row + deltaRow[i];
                int nCol = col + deltaCol[i];
                if(nRow>=0 && nRow<m && nCol>=0 && nCol<n && grid[nRow][nCol]==1 && visited[nRow][nCol]!=2){
                    visited[nRow][nCol]=2;
                    rotterCount++;
                    q.add(new Pair(nRow,nCol,time+1));
                }
            }
        }
        System.out.println(count==rotterCount? tm:-1);
    }
}