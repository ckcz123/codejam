import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * KickStart 2017 Round B Problem A: Math Encoder
 * Check README.md for explanation.
 */
public class Main {

    private long pow(long x, int k, long mod) {
        if (k==0) return 1;
        long p=pow(x, k/2, mod)%mod;
        return p*p%mod*(k%2!=0?x:1)%mod;
    }

    private String solve(Scanner scanner) throws Throwable {
        int n=scanner.nextInt();
        long[] a=new long[n];
        for (int i=0;i<n;i++) a[i]=scanner.nextLong();
        Arrays.sort(a);
        long mod=1000000007L, ans=0;
        HashMap<Integer, Long> map=new HashMap<>();
        for (int i=0;i<=10000;i++) map.put(i, pow(2L, i, mod));
        for (int i=0;i<n;i++) {
            for (int j=i+1;j<n;j++) {
                ans+=(a[j]-a[i])*map.get(j-i-1);
                ans%=mod;
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
                e.printStackTrace();
            }
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}