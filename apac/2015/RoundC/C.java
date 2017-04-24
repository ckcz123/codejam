import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * APAC 2015 Round C Problem C: Broken Calculator
 * Check README.md for explanation.
 */
public class Main {


    private String solve(Scanner scanner) {
        int[] a=new int[10];
        for (int i=0;i<10;i++) a[i]=scanner.nextInt();
        int x=scanner.nextInt();
        int cnt=cal(x, a, new HashMap<>());
        if (cnt>=Integer.MAX_VALUE/100) return "Impossible";
        return String.valueOf(cnt+1);
    }

    private int cal(int x, int[] a, HashMap<Integer, Integer> map) {
        if (map.containsKey(x)) return map.get(x);
        if (able(x, a)) {
            map.put(x, String.valueOf(x).length());
            return map.get(x);
        }
        int ans=Integer.MAX_VALUE/100;
        for (int i=2;i<=Math.sqrt(x);i++) {
            if (x%i!=0) continue;
            if (able(i, a))
                ans=Math.min(ans, String.valueOf(i).length()+1+cal(x/i, a, map));
            if (able(x/i, a))
                ans=Math.min(ans, String.valueOf(x/i).length()+1+cal(i, a, map));
        }
        map.put(x, ans);
        return map.get(x);
    }

    private boolean able(int x, int[] a) {
        char[] chars=String.valueOf(x).toCharArray();
        for (char c: chars) {
            if (a[c-'0']==0) return false;
        }
        return true;
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