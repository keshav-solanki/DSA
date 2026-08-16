class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] cnt = new int[3];
        for (int stone : stones) {
            cnt[stone % 3]++;
        }
        
        int cnt0 = cnt[0];
        int cnt1 = cnt[1];
        int cnt2 = cnt[2];
        
        // If count of 0-mod stones is even, Alice needs at least one 1-mod and one 2-mod stone.
        if (cnt0 % 2 == 0) {
            return cnt1 >= 1 && cnt2 >= 1;
        }
        
        // If count of 0-mod stones is odd, Alice wins only if the absolute difference 
        // between 1-mod and 2-mod stones is greater than 2.
        return Math.abs(cnt1 - cnt2) > 2;
    }
}