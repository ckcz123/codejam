import java.io.PrintStream;
import java.util.*;

/**
 * Codejam to I/O for Women 2015 Problem A: I/O Error
 * Check README.md for explanation.
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        String s=scanner.next().replace('I','1').replace('O','0');
        StringBuilder builder=new StringBuilder();
        for (int i=0;i<n;i++) {
            builder.append((char)Integer.parseInt(s.substring(8*i, 8*(i+1)), 2));
        }
        return builder.toString();
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