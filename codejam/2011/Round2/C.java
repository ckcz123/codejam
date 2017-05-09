import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2012 Round 2
 * Problem C. Expensive Dinner
 */
public class Main {

    private String solve(Scanner scanner) {
        long n=scanner.nextLong();
        if (n==1) return "0";
        init();
        int max=1, min=0;
        for (long p: primes) {
            if (p*p>n) break;
            min++;
            for (long v=1;;max++) {
                v*=p;
                if (v>n) break;
            }
        }
        return String.valueOf(max-min);
    }

    // all primes smaller than 1000000
    private static ArrayList<Long> primes;
    private void init() {
        if (primes==null) {
            primes=new ArrayList<>();
            primes.add(2L);
            for (long i=3;i<=1000000;i+=2) {
                for (long v: primes) {
                    if (v*v>i) {
                        primes.add(i);
                        break;
                    }
                    if (i%v==0) break;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        long times=scanner.nextInt();
        long start=System.currentTimeMillis();
        for (long t=1;t<=times;t++) {
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