class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int[] prefix = new int[n];

        prefix[0] = stones[0];
        for(int i=1; i<n; i++){
            prefix[i] = prefix[i-1] + stones[i];
        }

        return diff(1,prefix);
    }

    public int diff(int i, int[] prefix){
        if(i == prefix.length-1) return prefix[i];
        int skip = diff(i+1,prefix);
        int take = prefix[i] - skip;
        return Math.max(skip,take);
    }
}