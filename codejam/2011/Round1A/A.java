import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2011 Round 1A
 * Problem A. FreeCell Statistics
 */
public class Main {

    public String solve(Scanner scanner) {
        long n=scanner.nextLong();
        int pd=scanner.nextInt(), pg=scanner.nextInt();
        if (pg==0 || pg==100)
            return pd==pg?"Possible":"Broken";
        return 100/gcd(pd, 100)<=n?"Possible":"Broken";
    }

    private int gcd(int a, int b) {
        return b==0?a:gcd(b,a%b);
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