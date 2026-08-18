/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        // Node temp = head;
        // HashMap<Node,Node> map = new HashMap<>();

        // while(temp != null){
        //     Node node = new Node(temp.val);
        //     map.put(temp,node);
        //     temp = temp.next;
        // }

        // temp = head;
        // while(temp != null){
        //     Node copyNode = map.get(temp);
        //     copyNode.next = map.get(temp.next);
        //     copyNode.random = map.get(temp.random);
        //     temp = temp.next;
        // }
        // return map.get(head);
        if(head == null) return null;

        Node temp = head;
        while(temp != null){
            Node node = new Node(temp.val);
            Node next = temp.next;
            temp.next = node;
            node.next = next;
            temp = next;
        }

        temp = head;
        while(temp != null && temp.next != null){
            Node copyRandom = (temp.random == null) ? null : temp.random.next;

            temp.next.random = copyRandom;
            temp = temp.next.next;
        }


        temp = head;
        Node newHead = head.next;
        while(temp != null){
            Node copy = temp.next;

            temp.next = copy.next;

            if(copy.next != null){
                copy.next = copy.next.next;
            }
            temp = temp.next;
        }
        return newHead;
    }
}