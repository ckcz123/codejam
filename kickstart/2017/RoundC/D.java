import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * KickStart 2017 Round C Problem D. The 4M Corporation
 * Check README.md for explanation.
 */
public class Main {

    public String solve(Scanner scanner) {
        long min=scanner.nextLong(), max=scanner.nextLong(),
                mean=scanner.nextLong(), median=scanner.nextLong();
        if (min>mean || min>median || max<mean || max<median) return "IMPOSSIBLE";
        if (min==max) return "1";
        if (mean==median && min+max==mean*2) return "2";
        if (2*mean-min-median<=0 || median+max-2*mean<=0) return "IMPOSSIBLE";
        long ans=Math.min(testOdd(min, max, mean, median), testEven(min, max, mean, median));
        return ans==Long.MAX_VALUE?"IMPOSSIBLE":String.valueOf(ans);
    }

    private long testOdd(long min, long max, long avg, long s) {
        long ans=Math.max(3, Math.max(ceil(2*max-min-s, 2*avg-min-s), ceil(s+max-2*min, s+max-2*avg)));
        return ans%2==0?ans+1:ans;
    }

    private long testEven(long min, long max, long avg, long s) {
        long ans=Math.max(4, Math.max(ceil(2*max-2*min, 2*avg-min-s), ceil(2*max-2*min, s+max-2*avg)));
        return ans%2==0?ans:ans+1;
    }

    private long ceil(long a, long b) {
        return a/b+(a%b==0?0:1);
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=Integer.parseInt(scanner.nextLine());
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