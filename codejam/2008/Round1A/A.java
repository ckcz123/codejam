import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 Round 1A
 * Problem A. Minimum Scalar Product
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        long[] a=new long[n], b=new long[n];
        for (int i=0;i<n;i++) a[i]=scanner.nextLong();
        for (int i=0;i<n;i++) b[i]=scanner.nextLong();
        Arrays.sort(a); Arrays.sort(b);
        long ans=0;
        for (int i=0;i<n;i++)
            ans+=a[i]*b[n-1-i];
        return String.valueOf(ans);
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=Integer.parseInt(scanner.nextLine());
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