import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2012 Round 3
 * Problem C. Quality Food
 * Only small solved.
 */
public class Main {

    public String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    class Meal {
        long price, time;
        public Meal(long _price, long _time) {
            price=_price; time=_time;
        }
    }

    private String solveSmall(Scanner scanner) {
        long m=scanner.nextLong(), f=scanner.nextLong();
        int n=scanner.nextInt();
        Meal[] meals=new Meal[n];
        for (int i=0;i<n;i++) {
            meals[i]=new Meal(scanner.nextLong(), scanner.nextLong());
        }
        ArrayList<Long> list=new ArrayList<>();
        list.add(0L);
        for (int i=0;;i++) {
            long last=i==0?f:list.get(i);
            long p=Long.MAX_VALUE;
            for (Meal meal: meals) {
                if (meal.time>=i && meal.price<p) {
                    p=meal.price;
                }
            }
            if (p==Long.MAX_VALUE) break;
            list.add(last+p);
            if (last+p>m) break;
        }

        long mx=0;
        for (int i=1;i<list.size()-1;i++) {
            mx=Math.max(mx, cal(list.get(i), list.get(i+1), i, i+1, m));
        }
        return String.valueOf(mx);
    }

    private long cal(long u, long v, long a, long b, long m) {
        long mx=0;
        for (long x=0;u*x<=m;x++) {
            long l=m-u*x, y=l/v;
            mx=Math.max(mx, a*x+b*y);
        }
        return mx;
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