import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2011 Round 1C
 * Problem C. Perfect Harmony
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        long l=scanner.nextLong(), h=scanner.nextLong();
        long[] nums=new long[n]; for (int i=0;i<n;i++) nums[i]=scanner.nextLong();
        Arrays.sort(nums);
        long[] gcd=new long[n+1], lcm=new long[n+1];
        lcm[0]=1;
        for (int i=1;i<=n;i++) lcm[i]=lcm(lcm[i-1], nums[i-1], h);
        gcd[n]=0;
        for (int i=n-1;i>=0;i--) gcd[i]=gcd(nums[i], gcd[i+1]);

        long candidate=Long.MAX_VALUE;
        for (int i=0;i<n;i++) {
            long lower=lcm[i], higher=gcd[i];
            if (lower>h || higher<l || higher%lower!=0) continue;
            long div=higher/lower;
            for (long x=1;x*x<=div;x++) {
                if (div%x!=0) continue;
                long y=div/x;
                if (lower*x>=l && lower*x<=h)
                    candidate=Math.min(candidate, lower*x);
                if (lower*y>=l && lower*y<=h)
                    candidate=Math.min(candidate, lower*y);
            }
        }

        long lower=lcm[n];
        if (lower<=h)  {
            if (l%lower==0) candidate=Math.min(candidate, l);
            else {
                long v1=l/lower, v2=h/lower;
                if (v1<v2) candidate=Math.min(candidate, (v1+1)*lower);
            }
        }
        return candidate==Long.MAX_VALUE?"NO":String.valueOf(candidate);

    }

    private long gcd(long a, long b) {
        return b==0?a:gcd(b,a%b);
    }

    private long lcm(long a, long b, long h) {
        long g=gcd(a, b);
        a/=g;
        BigInteger l=BigInteger.valueOf(a).multiply(BigInteger.valueOf(b));
        if (l.compareTo(BigInteger.valueOf(h))<=0) return l.longValue();
        return h+1;
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