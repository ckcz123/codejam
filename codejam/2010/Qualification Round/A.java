import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2010 Qualification Round
 * Problem A. Snapper Chain
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt(), k=scanner.nextInt(), need=(1<<n)-1;
        return (k-need)%(1<<n)==0?"ON":"OFF";
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