class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int i =0;
        int ones = 0;
        String ans = "";

        for(int j =0; j<n; j++){
            char c = s.charAt(j);
            if(c == '1') ones++;

            while(ones > k && s.charAt(i) == '1') {
                ones--;
                i++;
            }

            if(ones == k ){
                while(i<j && s.charAt(i) == '0') i++;
                String curr = s.substring(i,j+1);

                if(ans.length() == 0 || curr.length() < ans.length() || (curr.length() == ans.length() && curr.compareTo(ans) < 0)) {
                    ans = curr;
                }
            }

        }
        return ans;
    }
}