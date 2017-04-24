import java.io.PrintStream;
import java.util.*;

/**
 * APAC 2017 Round B Problem B: Sherlock and Watson Gym Secrets
 * Check README.md for explanation.
 */
public class Main {

    private final static long MOD = 1000000007L;

    private int pow(long n, int k, int mod) {
        if (k==0) return 1%mod;
        if (k==1) return (int)(n%mod);
        long ans=pow(n, k/2, mod)%mod;
        return (int)(ans*ans%mod*(k%2==0?1:n)%mod);
    }

    private String solve(Scanner scanner) {
        int a=scanner.nextInt(), b=scanner.nextInt();
        long n=scanner.nextLong();
        int k=scanner.nextInt();

        long[] cnta=new long[k], cntb=new long[k];
        int[] rema=new int[k], remb=new int[k];
        for (int i=0;i<k;i++) {
            rema[i]=pow(i,a,k);
            remb[i]=pow(i,b,k);
        }
        for (int i=0;i<k;i++) {
            long times=(n/k+(i!=0&&i<=n%k?1:0))%MOD;
            cnta[rema[i]]=(cnta[rema[i]]+times)%MOD;
            cntb[remb[i]]=(cntb[remb[i]]+times)%MOD;
        }
        long ans=cnta[0]*cntb[0]%MOD;
        for (int i=1;i<k;i++) {
            ans+=cnta[i]*cntb[k-i]%MOD;
            ans%=MOD;
        }

        long invalid=0;
        for (int i=0;i<k;i++) {
            if ((rema[i]+remb[i])%k==0)
                invalid+=(n/k+(i!=0&&i<=n%k?1:0))%MOD;
            invalid%=MOD;
        }
        ans-=invalid;
        if (ans<0) ans+=MOD;

        return String.valueOf(ans);
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("E:\\desktop\\output.txt"));
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