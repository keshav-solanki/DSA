class Solution {
    public boolean sumGame(String num) {
        int n= num.length();
        int lSum =0;
        int rSum =0;
        int q1 = 0;
        int q2 = 0;

        for(int i=0; i<n; i++){
            char c = num.charAt(i);
            if(i<n/2){
                if(c == '?') q1++;
                else lSum += c-'0';
            }else{
                if(c == '?') q2++;
                else rSum += c-'0';
            }
        }
        
        return (lSum - rSum) * 2 != (q2- q1) * 9;
    }
}