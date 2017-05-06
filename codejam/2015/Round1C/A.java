import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2015 Round 1C
 * Problem A. Brattleship
 */
public class Main {

    private String solve(Scanner scanner) {
        int r=scanner.nextInt(), c=scanner.nextInt(), w=scanner.nextInt();
        return String.valueOf(c/w*r+w-(c%w==0?1:0));
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