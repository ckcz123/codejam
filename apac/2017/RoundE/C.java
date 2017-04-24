import java.io.PrintStream;
import java.util.*;

/**
 * APAC 2017 Round E Problem C: Partioning Number
 * Check README.md for explanation.
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt(), d=scanner.nextInt();
        long ans=0;
        for (int x=d;x<=n;x+=d) {
            for (int p=1;p<=n/x;p++) {
                int t=n-p*x, range=t/(x+2);
                ans+=range/(x+1)+(range%(x+1)>=t%(x+1)?1:0);
            }
        }
        return String.valueOf(ans);
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
