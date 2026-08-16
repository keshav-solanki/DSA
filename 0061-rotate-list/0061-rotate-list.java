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
    public ListNode rotateRight(ListNode head, int k) {
        if(k == 0 || head == null || head.next == null) return head;
       
        int n =  1;
        ListNode temp = head;

       
        while(temp.next != null) {
            n++;
            temp = temp.next;
        }
        System.out.print(n);
         System.out.println(temp.val);
         
        temp.next = head;
         k = k%n;
        ListNode temp2 = head;

        for(int i=1; i<n-k; i++){
            temp2 = temp2.next;
        }

        System.out.println(temp2.val);
        ListNode newHead = temp2.next;
        temp2.next = null;
        return newHead;

    }
}