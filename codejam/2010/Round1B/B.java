import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2010 Round 1B
 * Problem B. Picking Up Chicks
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), k=scanner.nextInt();
        long b=scanner.nextLong(); int t=scanner.nextInt();
        long[] x=new long[n], v=new long[n];
        for (int i=0;i<n;i++) x[i]=scanner.nextLong();
        for (int i=0;i<n;i++) v[i]=scanner.nextLong();
        int cnt=0, not=0;
        for (int i=n-1;i>=0;i--) {
            if (k==0) break;
            if (v[i]*t<b-x[i]) {
                not++;continue;
            }
            cnt+=not;
            k--;
        }
        return k==0?String.valueOf(cnt):"IMPOSSIBLE";
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