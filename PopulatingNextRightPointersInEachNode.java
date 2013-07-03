package leetcod;


public class PopulatingNextRightPointersInEachNode {
	public void connect(TreeLinkNode root) {
        if (root==null) return;
        if (root.left==null && root.right==null) return;
        if (root.left!=null) root.left.next = root.right;
        if (root.right!=null && root.next!=null) root.right.next = root.next.left;
        connect(root.left);
        connect(root.right);       
    }
	
	public void connectII(TreeLinkNode root) {
        if (root==null) return;
        if (root.left==null && root.right==null) return;
        if (root.left!=null) {
            if (root.right!=null) root.left.next = root.right;
            else {
                TreeLinkNode next = findNext(root.next, null);                
                if (next!=null) {
                    root.left.next = next;
                }
            }
        }
        if (root.right!=null) {
            TreeLinkNode next = findNext(root.next, root.left==null? null: root.left.next);
            if (next!=null) root.right.next = next;
        }
        connectII(root.right);       
        connectII(root.left);        
    }
    
    public TreeLinkNode findNext(TreeLinkNode rootsNext, TreeLinkNode isNotMe) {
        TreeLinkNode next = null;
        if (rootsNext==null) return null;
        while(rootsNext!=null) {
            if (rootsNext.left!=null && (isNotMe==null || isNotMe!=rootsNext.left)) {
                next = rootsNext.left;
                break;
            }
            if (rootsNext.right!=null && (isNotMe==null || isNotMe!=rootsNext.right)) {
                next = rootsNext.right;
                break;
            }
            rootsNext = rootsNext.next;
        }
        return next;       
    }
    
    
    public class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;

		TreeLinkNode(int x) {
			val = x;
		}
	}
    
    public static void main(String[] args) {
    	PopulatingNextRightPointersInEachNode me = new PopulatingNextRightPointersInEachNode();
    	//{2,1,3,0,7,9,1,2,#,1,0,#,#,8,8,#,#,#,#,7}
    	TreeLinkNode n2 = me.new TreeLinkNode(2);
    	TreeLinkNode n1 = me.new TreeLinkNode(1);
    	TreeLinkNode n3 = me.new TreeLinkNode(3);
    	TreeLinkNode n0 = me.new TreeLinkNode(0);
    	TreeLinkNode n7 = me.new TreeLinkNode(7);
    	TreeLinkNode n9 = me.new TreeLinkNode(9);
    	TreeLinkNode n12 = me.new TreeLinkNode(1);
    	TreeLinkNode n22 = me.new TreeLinkNode(2);
    	TreeLinkNode n13 = me.new TreeLinkNode(1);
    	TreeLinkNode n02 = me.new TreeLinkNode(0);
    	TreeLinkNode n8 = me.new TreeLinkNode(8);
    	TreeLinkNode n82 = me.new TreeLinkNode(8);
    	TreeLinkNode n72 = me.new TreeLinkNode(7);
    	n2.left = n1; n2.right = n3;
    	n1.left = n0; n1.right = n7;
    	n3.left = n9; n3.right = n12;
    	n0.left = n22;
    	n7.left = n13; n7.right = n02;
    	n12.left = n8; n12.right = n82;
    	n02.left = n72;
    	me.connectII_wrongstill(n2);
    }
}
