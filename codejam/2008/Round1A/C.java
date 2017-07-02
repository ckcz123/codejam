import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 Round 1A
 * Problem C. Numbers
 */
public class Main {

    private static TreeMap<Integer, Integer> cache;

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        String s=String.valueOf((cal(n)+999)%1000);
        while (s.length()<3) s="0"+s;
        return s;
    }

    private int cal(int n) {
        if (cache==null) {
            cache=new TreeMap<>();
            cache.put(0, 2);
            cache.put(1, 6);
        }
        if (cache.containsKey(n)) return cache.get(n);
        int u=cache.lowerKey(n), v=u-1;
        int a=cache.get(v), b=cache.get(u);
        for (int i=u;i<=n;i++) {
            int c=(6*b-4*a+1000)%1000;
            a=b;b=c;
        }
        cache.put(n, a);
        cache.put(n+1, b);
        return a;
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