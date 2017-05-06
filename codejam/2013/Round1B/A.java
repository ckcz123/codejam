import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2013 Round 1B
 * Problem A. Osmos
 */
public class Main {

    public String solve(Scanner scanner) {
        long a=scanner.nextLong(); int n=scanner.nextInt();
        long[] size=new long[n];
        for (int i=0;i<n;i++) size[i]=scanner.nextLong();
        Arrays.sort(size);
        if (a==1) return String.valueOf(n);
        int ans=n, cnt=0;
        for (int i=0;i<n;i++) {
            while (a<=size[i]) {
                cnt++;
                a+=(a-1);
            }
            a+=size[i];
            ans=Math.min(ans, n-1-i+cnt);
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
            catch (Exception e) {System.err.println("ERROR #"+t);e.printStackTrace();}
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.));
    }

}