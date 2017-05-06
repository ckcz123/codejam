import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2013 Round 1A
 * Problem A. Bullseye
 */
public class Main {

    private String solve(Scanner scanner) throws Throwable {
        long r=scanner.nextLong(), t=scanner.nextLong();
        long a=2, b=2*r+3, c=2*r+1-t;
        long start=0, end=4000000000000000000L;
        while (start<end) {
            long mid=(start+end+1)/2;
            if (test(a,b,c,mid)) start=mid;
            else end=mid-1;
        }
        return String.valueOf(start+1);
    }

    private boolean test(long a, long b, long c, long x) {
        return BigInteger.valueOf(a).multiply(BigInteger.valueOf(x)).multiply(BigInteger.valueOf(x))
                .add(BigInteger.valueOf(b).multiply(BigInteger.valueOf(x)))
                .add(BigInteger.valueOf(c)).compareTo(BigInteger.ZERO)<=0;
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
            }
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}