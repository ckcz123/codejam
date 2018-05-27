import java.io.PrintStream;
import java.util.*;

/**
 * KickStart 2018 Round C
 * Problem C. Kickstart Alarm
 */
public class Main {

    private static final long MOD = 1000000007L;

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), k=scanner.nextInt();
        long[] a=new long[n];
        long x1=scanner.nextLong(), y1=scanner.nextLong(), c=scanner.nextLong(),
                d=scanner.nextLong(), e1=scanner.nextLong(), e2=scanner.nextLong(),
                f=scanner.nextLong();

        for (int i=0;i<n;i++) {
            a[i]=(x1+y1)%f;
            long nx=(c*x1+d*y1+e1)%f;
            long ny=(d*x1+c*y1+e2)%f;
            x1=nx; y1=ny;
        }
        return String.valueOf(calculate(n, k, a));
    }

    private long calculate(int n, int k, long[] a) {
        long sum=0, ans=0;
        for (int p=1;p<=n;p++) {
            sum += sum(p, k);
            sum %= MOD;
            ans+=(n-p+1)%MOD*a[p-1]%MOD*sum%MOD;
            ans%=MOD;
        }
        return ans;
    }

    private long sum(int p, int k) {
        if (p==1) return k;
        return ((quickpow(p, k+1)-1) * quickpow(p-1, MOD-2) + MOD - 1) % MOD;
    }

    private long quickpow(long x, long n) {
        if (n==0) return 1;
        if (n==1) return x;
        long v=quickpow(x, n/2);
        return v*v%MOD*(n%2==1?x:1)%MOD;
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