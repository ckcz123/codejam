import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2011 Round 1C
 * Problem C. Perfect Harmony
 * Only small solved.
 */
public class Main {

    public String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int n=scanner.nextInt(); long l=scanner.nextLong(), h=scanner.nextLong();
        long[] v=new long[n];
        for (int i=0;i<n;i++) v[i]=scanner.nextLong();
        for (long x=l;x<=h;x++) {
            boolean able=true;
            for (long a: v) {
                if (a%x!=0 && x%a!=0) {
                    able=false;break;
                }
            }
            if (able) return String.valueOf(x);
        }
        return "NO";
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