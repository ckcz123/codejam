import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private String solve(Scanner scanner) throws Throwable {
        long e=scanner.nextInt(), r=scanner.nextInt();
        int n=scanner.nextInt();
        long[] v=new long[n], upper=new long[n], lower=new long[n];
        for (int i=0;i<n;i++) {
            v[i]=scanner.nextLong();
        }
        Arrays.fill(upper, e);
        boolean[] used=new boolean[n];
        long ans=0;
        for (int k=0;k<n;k++) {
            long mx=0;
            int idx=-1;
            for (int i=0;i<n;i++) {
                if (!used[i] && mx<v[i]) {
                    mx=v[i]; idx=i;
                }
            }
            ans+=mx*(upper[idx]-lower[idx]);
            used[idx]=true;
            for (int i=idx+1;i<n;i++) {
                upper[i]=Math.min(upper[i], lower[idx]+(i-idx)*r);
            }
            for (int i=idx-1;i>=0;i--) {
                lower[i]=Math.max(lower[i], upper[idx]-(idx-i)*r);
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
            try {
                System.out.println(String.format("Case #%d: %s", t, new Main().solve(scanner)));
            }
            catch (Throwable e) {
                System.err.println("ERROR in case #"+t);
            }
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}