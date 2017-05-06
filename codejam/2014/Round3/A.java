import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2014 Round 3
 * Problem A. Magical, Marvelous Tour
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        long p=scanner.nextLong(), q=scanner.nextLong(), r=scanner.nextLong(),
                s=scanner.nextLong(), sum=0;
        long[] a=new long[n];
        for (int i=0;i<n;i++) {
            a[i]=(p*i+q)%r+s;
            sum+=a[i];
        }
        long start=1, end=sum;
        while (start<end) {
            long mid=(start+end)/2;
            if (able(n, a, 3, mid)) end=mid;
            else start=mid+1;
        }
        return String.format("%.9f", 1-(start+.0)/sum);

    }

    private boolean able(int n, long[] a, int m, long candidate) {
        int cnt=0;
        long sum=Long.MAX_VALUE/10;
        for (int i=0;i<n;i++) {
            if (a[i]>candidate) return false;
            if (sum+a[i]>candidate) {
                cnt++;
                sum=a[i];
            }
            else {
                sum+=a[i];
            }
            if (cnt>m) break;
        }
        return cnt<=m;
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