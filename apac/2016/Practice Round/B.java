import java.io.PrintStream;
import java.util.*;

/**
 * APAC 2016 Practice Round Problem B: Captain Hammer
 * Check README.md for explanation.
 */
public class Main {

    private String solve(Scanner scanner) {
        double v=scanner.nextDouble(), d=scanner.nextDouble();
        double x=(d*9.8)/(v*v);
        if (x>=1) x=1;
        return String.format("%.9f", Math.asin(x)*.5*180/Math.PI);
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