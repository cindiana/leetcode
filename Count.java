package leetcod;
public class Count {
	private static int CountTwo_2(int n) {      
        int sum = 0;
        int temp = n;
        int base = 1;
        int prevDig = 0;
        while(temp>10) {   

            prevDig = n - temp*base;       
            sum += base * (temp/10);
            if (temp%10 >2) {
                sum += base;
            }
            else if (temp%10==2) {
                sum += prevDig+1;
            }

            temp = temp/10;
            base*=10;
        }

        // Now deal with highest digit.
        if (temp==2) {
            sum += (n-2*base+1);
        }else if(temp>2) {
            sum += base;
        }
        return sum;
    } 
	private static int brutalForce(int n) {

        int c = 0;

        for(int i=0 ;i<=n; i++) {

            String s = String.valueOf(i);

            char[] ss = s.toCharArray();

            for (int j=0; j < ss.length;j++)
            {

                 if (ss[j]=='2') {
                    c++;
                }
            }
        }
        return c;
    }
	public static void main(String[] args) {
		for (int i=0; i<10000; i++) {
			if (CountTwo_2(i)!=brutalForce(i)) {
				System.out.println("Count " + i + ": " + brutalForce(i) + "/" + CountTwo_2(i));
			}
		}

	}

}
