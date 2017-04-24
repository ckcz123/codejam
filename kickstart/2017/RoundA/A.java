import java.io.PrintStream;
import java.util.*;

/**
 * KickStart 2017 Round A Problem A: Square Counting
 * Check README.md for explanation.
 */
public class Main {

    private String solve(Scanner scanner) {
        long r=scanner.nextLong(), c=scanner.nextLong(), m=Math.min(r, c);
        long cnt=0, mod=1000000007;

        long u=(m-2)/2, su1=sum(u, mod), su2=sum2(u, mod), su3=sum3(u, mod);
        cnt+=-8*su3+2*(r+c-6)*su2%mod+2*(r+c-2)*su1%mod;
        cnt%=mod;
        if (m%2!=0) {
            long k=(m-1)/2;
            cnt+=(r+c-m-2*k)*k%mod*(k+1)%mod;
        }

        long v=(m-1)/2, sv1=sum(v, mod), sv2=sum2(v, mod), sv3=sum3(v, mod);
        cnt+=-8*sv3+2*(r+c)*sv2%mod;
        cnt%=mod;
        if (m%2==0) {
            long k=m/2;
            cnt+=(r+c-m-2*k+1)*k%mod*k%mod;
        }
        while (cnt<0) cnt+=mod;
        cnt%=mod;
        return String.valueOf(cnt);
    }

    private long sum(long u, long mod) {
        // n*(n+1)/2
        return u*(u+1)/2%mod;
    }

    private long sum2(long u, long mod) {
        // n*(n+1)*(2n+1)/6
        long a=u, b=u+1, c=2*u+1;
        if (a%2==0) a/=2;
        else b/=2;
        if (a%3==0) a/=3;
        else if (b%3==0) b/=3;
        else c/=3;
        return a*b%mod*c%mod;
    }

    private long sum3(long u, long mod) {
        // (n*(n+1)/2)^2
        long s=sum(u, mod);
        return s*s%mod;
    }

    /*private long solveSmall(int m, int n) {
        long cnt=0;
        for (int i=1;i<=r;i++) {
            for (int j=1;j<=c;j++) {
                int k=Math.min(Math.min(i-1, r-i), Math.min(j-1, c-j));
                cnt+=k*(k+1);
                cnt%=1000000007;
            }
        }
        for (int i=1;i<r;i++) {
            for (int j=1;j<c;j++) {
                int k=Math.min(Math.min(i, r-i), Math.min(j, c-j));
                cnt+=k*k;
                cnt%=1000000007;
            }
        }
        return cnt;
    }*/


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