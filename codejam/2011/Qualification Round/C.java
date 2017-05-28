import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2011 Qualification Round
 * Problem C. Candy Splitting
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt(), sum=0, xor=0, mn=Integer.MAX_VALUE;
        for (int i=0;i<n;i++) {
            int x=scanner.nextInt();
            sum+=x;
            mn=Math.min(mn, x);
            xor^=x;
        }
        return xor!=0?"NO":String.valueOf(sum-mn);
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