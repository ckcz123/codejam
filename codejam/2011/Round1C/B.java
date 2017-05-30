import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2011 Round 1C
 * Problem B. Space Emergency
 */
public class Main {

    public String solve(Scanner scanner) {
        int l=scanner.nextInt();
        long t=scanner.nextLong();
        int n=scanner.nextInt(), c=scanner.nextInt();
        long[] dis=new long[n];
        for (int i=0;i<n;i++) {
            if (i>=c) dis[i]=dis[i-c];
            else dis[i]=scanner.nextLong();
        }
        long run=t/2;
        ArrayList<Long> list=new ArrayList<>();
        long ans=0;
        for (long v: dis) {
            if (run<v) {
                ans+=2*run;
                list.add(v-run);
                run=0;
            }
            else {
                ans+=2*v;
                run-=v;
            }
        }
        list.sort((o1, o2)->Long.compare(o2, o1));
        for (long v: list) {
            if (l>0) {
                ans+=v;
                l--;
            }
            else ans+=2*v;
        }
        return String.valueOf(ans);
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