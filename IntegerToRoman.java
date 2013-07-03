package leetcod;

public class IntegerToRoman {
	public String intToRoman(int num) {
		String[] hundreds = new String[]{"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
        String[] tens = new String[]{"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
        String[] ones = new String[]{"","I","II","III","IV","V","VI","VII","VIII","IX"};
        
        StringBuilder sb = new StringBuilder();
        int ks = num/1000;
        for (int i=0;i<ks;i++) {
            sb.append('M');
        }
        
        num-=ks*1000;
        sb.append(hundreds[num/100]);
        num-=(num/100)*100;
        sb.append(tens[num/10]);
        num-=(num/10)*10;
        sb.append(ones[num]);
        return sb.toString();
    }
	
	public static void main(String[] args) {
		IntegerToRoman itr = new IntegerToRoman();
		itr.intToRoman(900);
	}
}
