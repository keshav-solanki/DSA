/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if(head.next.next == null) return new int[]{-1,-1};

        ListNode curr = head.next;
        ListNode prev = head;
        int idx = 2;
        ArrayList<Integer> list = new ArrayList<>();

        while(curr.next != null){
            ListNode next = curr.next;
            if((prev.val < curr.val && curr.val > next.val) 
            || (prev.val > curr.val && curr.val < next.val) ) 
                list.add(idx);

            prev = curr;
            curr = curr.next;
            idx++;
        }

        if(list.size() == 1 || list.size() == 0) return new int[]{-1,-1};
        
        int min = Integer.MAX_VALUE;
        for(int i= 0; i<list.size()-1; i++){
            min = Math.min(list.get(i+1) - list.get(i),min);
        }
        int max = list.get(list.size()-1) - list.get(0);
        return new int[]{min,max};
    }
}