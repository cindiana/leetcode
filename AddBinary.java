package leetcod;

public class AddBinary {
	public String addBinary_20130623(String a, String b) {
        if (a==null || a.length()==0) return b;
        if (b==null || b.length()==0) return a;
        
        int carry = 0;
        int pa = a.length()-1;
        int pb = b.length()-1;
        StringBuilder sb = new StringBuilder();
        while(pa>=0 && pb>=0) {
            int va = a.charAt(pa)-'0';
            int vb = b.charAt(pb)-'0';
            int sum = va+vb+carry;
            sb.insert(0,sum%2);
            carry = sum/2;
            pa--; pb--;
        }
        while(pa>=0) {
            int va = a.charAt(pa)-'0';
            int sum = va+carry;
            sb.insert(0,sum%2);
            carry = sum/2;
            pa--;
        }
        while(pb>=0) {
            int vb = b.charAt(pb)-'0';
            int sum = vb+carry;
            sb.insert(0,sum%2);
            carry = sum/2;
            pb--;
        }
        while(carry>0) {
            sb.insert(0, carry%2);
            carry = carry/2;
        }
        return sb.toString();
    }
	public String addBinary(String a, String b) {
        if (a==null || a.length()==0) return b;
        if (b==null || b.length()==0) return a;
        
        int pa = a.length()-1;
        int pb = b.length()-1;
        int carry = 0;
        List<Integer> list = new ArrayList<Integer>();
        
        while(pa>=0 && pb>=0) {
            int sum = a.charAt(pa)-'0'+b.charAt(pb)-'0' + carry;
            carry = sum/2;
            list.add(0, sum%2);
            pa--;
            pb--;
        }
        while(pa>=0) {
            int sum = a.charAt(pa)-'0'+ carry;
            carry = sum/2;
            list.add(0, sum%2);
            pa--;
        }
        while(pb>=0) {
            int sum = b.charAt(pb)-'0' + carry;
            carry = sum/2;
            list.add(0, sum%2);
            pb--;
        }
        if (carry>0) {
            list.add(0, carry);
        }
        StringBuilder sb = new StringBuilder();
        for (int i:list) {
            sb.append(i);
        }
        return sb.toString();
    }
}
