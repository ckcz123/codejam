import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;

/**
 * Codejam 2010 Qualification Round
 * Problem B. Fair Warning
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        BigInteger[] nums=new BigInteger[n];
        for (int i=0;i<n;i++)
            nums[i]=new BigInteger(scanner.next());
        BigInteger gcd=BigInteger.ZERO;
        for (int i=0;i<n-1;i++)
            gcd=gcd.gcd(nums[i].subtract(nums[i+1]));
        BigInteger need=nums[0].mod(gcd);
        if (need.equals(BigInteger.ZERO)) return "0";
        return gcd.subtract(need).toString();
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