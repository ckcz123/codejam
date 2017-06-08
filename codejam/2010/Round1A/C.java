import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2010 Round 1A
 * Problem C. Number Game
 */
public class Main {

    public String solve(Scanner scanner) {
        long a1=scanner.nextLong(), a2=scanner.nextLong(),
                b1=scanner.nextLong(), b2=scanner.nextLong(), ans=0;
        for (long a=a1;a<=a2;a++) ans+=b2-Math.min(b2+1, Math.max(b1, find(a)))+1;
        for (long b=b1;b<=b2;b++) ans+=a2-Math.min(a2+1, Math.max(a1, find(b)))+1;
        return String.valueOf(ans);
    }

    private long find(long x) {
        long start=x, end=2*x;
        while (start<end) {
            long mid=(start+end)/2;
            if ((2*mid-x)*(2*mid-x)<=5*x*x) start=mid+1;
            else end=mid;
        }
        return start;
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=scanner.nextInt();
        long start=System.currentTimeMillis();
        for (int t=1;t<=times;t++) {
            try {
                System.out.println(String.format("Case #%d: %s", t, new Main().solve(scanner)));
            }
            catch (Throwable e) {
                System.err.println("ERROR in case #"+t);
                e.printStackTrace();
            }
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}