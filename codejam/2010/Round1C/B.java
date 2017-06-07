import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2010 Round 1C
 * Problem B. Load Testing
 */
public class Main {

    private String solve(Scanner scanner) {
        long l=scanner.nextLong(), p=scanner.nextLong(), c=scanner.nextLong();
        int cnt=0, ans=0;
        while (l*c<p) {
            l*=c;
            cnt++;
        }
        while (cnt>0) {
            ans++;
            cnt/=2;
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
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}