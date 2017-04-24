import java.io.PrintStream;
import java.util.*;

/**
 * APAC 2017 Round D Problem A: Vote
 * Check README.md for explanation.
 */
public class Main {

    public String solve(Scanner scanner) {
        double n=scanner.nextDouble(), m=scanner.nextDouble();
        return String.format("%.9f", (n-m)/(n+m));
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
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.));
    }
}
