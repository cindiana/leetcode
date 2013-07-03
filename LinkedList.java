package leetcod;

/*
 * 1. didn't deal with list of size 2
 * 2. use of variable name "root" in toString messed data up. 
 * 3. when fixing toString, accidently append extra .next and caused heap crash.
 * 4. need to set original head.next = null;
 */
public class LinkedList {
    Node root;

    public void setRoot(Node rot) {
        root = rot;
    }

    public void reverse() {
        if (root == null)
            return;
        Node curr = root;
        Node next = curr.next;
        if (next != null) {
            Node nextnext = next.next;

            while (next != null && nextnext != null) {
                next.next = curr;
                curr = next;
                next = nextnext;
                nextnext = nextnext.next;
            }
            root.next = null;
            root = next;
            next.next = curr;
        }        
    }

    @Override
    public String toString() {
        if (root == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        Node node = root;                
        
        while (node != null) {
            sb.append(node.getValue());
            sb.append('-');
            node = node.next;
        }
        return sb.toString();
    }

    class Node {
        Node next;
        int value;

        public void setNext(Node nxt) {
            next = nxt;
        }

        public Node(int val) {
            value = val;
        }

        public int getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        Node root = list.new Node(1);
        list.setRoot(root);

        Node two = list.new Node(2);
        root.next = two;
        two.next = list.new Node(3);

        System.out.println(list.toString());
        list.reverse();
        System.out.println(list.toString());
    }
}
