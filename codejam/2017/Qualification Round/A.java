import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2017 Qualification Round
 * Problem A: Oversized Pancake Flipper
 */
public class Main {

    private String solve(Scanner scanner) {
        String s=scanner.next();
        int k=scanner.nextInt();
        int v=solve(s.toCharArray(), s.length(), k);
        return v>=0?String.valueOf(v):"IMPOSSIBLE";
    }

    private int solve(char[] c, int n, int k) {
        if (n==0) return 0;
        if (c[n-1]=='+') return solve(c, n-1, k);
        if (n<k) return Integer.MIN_VALUE;
        for (int i=n-1;i>=n-k;i--)
            c[i]=(char)('+'+'-'-c[i]);
        return 1+solve(c, n-1, k);
    }


    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=scanner.nextInt();
        long start=System.currentTimeMillis();
        for (int t=1;t<=times;t++) {
            System.out.println(String.format("Case #%d: %s", t, new Main().solve(scanner)));
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}