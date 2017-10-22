import java.io.PrintStream;
import java.util.*;

/**
 * KickStart 2017 Round G
 * Problem A. Huge Numbers
 */
public class Main {

    public String solve(Scanner scanner) {
        long a=scanner.nextLong(), n=scanner.nextLong(), p=scanner.nextLong();
        if (p==1) return "0";
        if (a==1) return "1";
        if (n<=4) return String.valueOf(calSmall(a,n,p));

        HashMap<Long, Integer> primes=calPrimes(p);
        HashMap<Long, Long> remains=new HashMap<>();

        for (Map.Entry<Long, Integer> entry: primes.entrySet()) {
            remains.put(entry.getKey(), calRemains(a, n, entry.getKey(), entry.getValue()));
        }

        for (long i=0;i<p;i++)
            if (check(primes, remains, i))
                return String.valueOf(i);

        return "";
    }

    private HashMap<Long, Integer> calPrimes(long p) {
        HashMap<Long, Integer> primes=new HashMap<>();
        long v=p;
        for (long i=2;i*i<=p;i++) {
            int cnt=0;
            while (v%i==0) {
                cnt++; v/=i;
            }
            if (cnt>0) primes.put(i, cnt);
        }
        if (v!=1) primes.put(v, 1);
        return primes;
    }

    private long calRemains(long a, long n, long p, long v) {
        // a^(n!) % (p^v)
        if (a%p==0) return 0;
        // a^(v*(p-1)) === 1 (mod p^v)
        long u=v*(p-1);
        long tot=1;
        for (int i=1;i<=n;i++) {
            tot*=i; tot%=u;
        }
        long ans=1;
        for (int i=0;i<tot;i++) {
            ans*=a;
            ans%=p;
        }
        return ans;
    }

    private long calSmall(long a, long n, long p) {
        long tot=1, ans=1;
        for (int i=1;i<=n;i++) tot*=i;
        for (int i=0;i<tot;i++) {
            ans*=a;ans%=p;
        }
        return ans;
    }

    private boolean check(HashMap<Long, Integer> primes, HashMap<Long, Long> remains, long candidate) {
        for (Map.Entry<Long, Integer> entry: primes.entrySet()) {
            long p=entry.getKey(), ans=1;
            for (int i=0;i<entry.getValue();i++) ans*=p;
            if (candidate%ans!=remains.get(p))
                return false;
        }
        return true;
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