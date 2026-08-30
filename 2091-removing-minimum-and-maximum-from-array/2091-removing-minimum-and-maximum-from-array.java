class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        int minIdx = -1;
        int maxIdx = -1;
    
        for(int i=0; i<n; i++){
            int num = nums[i];
            if(num < min) {
                minIdx = i;
                min = num;
            }
            if(num > max) {
                maxIdx = i;
                max = num;
            } 
        }
        
        int left = Math.min(minIdx,maxIdx);
        int right = Math.max(minIdx,maxIdx);
        
        return Math.min(n-left , Math.min(right+1, (left+1) +(n-right)));
    }
}