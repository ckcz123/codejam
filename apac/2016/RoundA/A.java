import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * APAC 2016 Round A Problem A: Googol String
 * Check README.md for explanation.
 */
public class Main {

    private String solve(Scanner scanner) {
        return String.valueOf(solve(scanner.nextLong()));
    }

    private int solve(long k) {
        if ((k&(k-1))==0) return 0;
        long u=1;
        while (u<k) u*=2;
        return 1-solve(u-k);
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