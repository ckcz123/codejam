import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2010 Round 3
 * Problem A. De-RNG-ed
 */
public class Main {

    public String solve(Scanner scanner) {
        int d=scanner.nextInt(), k=scanner.nextInt();
        long md=1; while (d-->0) md*=10;
        long[] nums=new long[k];
        for (int i=0;i<k;i++) nums[i]=scanner.nextLong();
        initPrimes();
        if (k==1) return "I don't know.";
        if (nums[0]==nums[1]) return String.valueOf(nums[0]);
        if (k==2) return "I don't know.";
        HashSet<Long> set=new HashSet<>();

        for (long p: primes) {
            // choose p
            if (p>=md) break;

            // check if all number < p
            boolean able=true;
            for (long n: nums)
                if (n>=p) {
                    able=false;break;
                }
            if (!able) continue;

            // calculate a
            long d1=nums[1]-nums[0], d2=nums[2]-nums[1];
            if (d1<0) d1+=p; if (d2<0) d2+=p;
            // d[1]!=0, p|(a*d1-d2)
            // p|(d1*rd1-1)
            long rd1=quickPow(d1, p-2, p);
            long a=rd1*d2%p;

            // calculate b
            // p|(a*nums[0]+b-nums[1])
            long b=nums[1]-a*nums[0]%p;
            if (b<0) b+=p;

            // calculate others
            able=true;
            for (int i=0;i<k-1;i++) {
                if ((a*nums[i]+b)%p!=nums[i+1])
                    able=false;
            }
            if (!able) continue;
            set.add((a*nums[k-1]+b)%p);
        }

        return set.size()==1?String.valueOf(set.iterator().next()):"I don't know.";
    }

    private long quickPow(long d, long x, long p) {
        if (x==0) return 1;
        if (x==1) return d;
        long pow=quickPow(d,x/2,p);
        long ans=pow*pow%p;
        if (x%2!=0) ans=ans*d%p;
        return ans;
    }

    private static ArrayList<Long> primes;
    private void initPrimes() {
        if (primes==null || primes.isEmpty()) {
            primes=new ArrayList<>();
            primes.add(2L); primes.add(3L);
            for (long i=5;i<=1000000;i+=2) {
                boolean isPrime=true;
                for (int j=0;j<primes.size();j++) {
                    long p=primes.get(j);
                    if (i%p==0) {
                        isPrime=false;break;
                    }
                    if (p*p>i) break;
                }
                if (isPrime) primes.add(i);
            }
        }
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