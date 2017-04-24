import java.io.PrintStream;
import java.util.*;

/**
 * APAC 2017 Round B Problem A: Sherlock and Parentheses
 * Check README.md for explanation.
 */
public class Main {

    private String solve(Scanner scanner) {
        long l=scanner.nextLong(), r=scanner.nextLong(), x=Math.min(l, r);
        return String.valueOf(x*(x+1)/2);
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("E:\\desktop\\output.txt"));
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