class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
    //     boolean[][] visited = new boolean[n+1][10];

    //     for(int[] seats : reservedSeats ){
    //         int row = seats[0];
    //         int seat = seats[1];
    //         visited[row][seat] = true;
    //     }
    //     int maxGroup =0;
    //     for(int i = 1; i<=n; i++){
    //         boolean left = isFree(visited,2,5,i);
    //         boolean mid = isFree(visited,4,7,i);
    //         boolean right = isFree(visited,6,9,i);


    //         if(left && right) maxGroup += 2;
    //         else if(left || mid || right ) maxGroup += 1;

    //     } 
    //     return maxGroup;  
    // }

    // public boolean isFree(boolean [][] visited,int s, int e,int i){
    //     for(int j=s ; j<=e; j++){
    //         if(visited[i][j] ) return false;
    //     }
    //     return true;

        HashMap<Integer, boolean[]> map = new HashMap<>();

        for(int[] seats : reservedSeats ){
            int row = seats[0];
            int seat = seats[1];
            boolean[] arr = map.getOrDefault(row,new boolean[11]);
            arr[seat] = true;
            map.put(row,arr);
        }
        int maxGroup = 0;
        maxGroup += (n-map.size())*2;

        for (Map.Entry<Integer,boolean[] > entry : map.entrySet()) {
            int row = entry.getKey();
            boolean[] seats = entry.getValue();
            boolean left = isFree(seats,2,5);
            boolean mid = isFree(seats,4,7);
            boolean right = isFree(seats,6,9);


            if(left && right) maxGroup += 2;
            else if(left || mid || right ) maxGroup += 1;
        }
        return maxGroup;
    }
    public boolean isFree(boolean [] visited,int s, int e){
        for(int j=s ; j<=e; j++){
            if(visited[j] ) return false;
        }
        return true;
    }
}