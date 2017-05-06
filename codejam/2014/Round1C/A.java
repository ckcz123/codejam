import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2014 Round 1C
 * Problem A. Part Elf
 */
public class Main {

    private String solve(Scanner scanner) {
        String s=scanner.next();
        String[] ss=s.split("/");
        long p=Long.parseLong(ss[0]), q=Long.parseLong(ss[1]), g=gcd(p, q);
        p/=g; q/=g;
        if ((q&(q-1))!=0) return "impossible";
        int cnt=0;
        while (p<q) {
            cnt++;q/=2;
        }
        return cnt<=40?String.valueOf(cnt):"impossible";
    }

    private long gcd(long x, long y) {
        return y==0?x:gcd(y, x%y);
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