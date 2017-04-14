import java.io.PrintStream;
import java.util.*;

public class Main {

    public String solve(Scanner scanner) {
        long n=scanner.nextLong();
        for (int b=64;b>=2;b--) {
            long candidate=(long)(Math.pow(n, 1./b));
            if (candidate!=1 && cal(candidate, b)==n)
                return String.valueOf(candidate);
        }
        return String.valueOf(n-1);
    }

    private long cal(long v, int b) {
        long ans=1, p=1;
        for (int i=1;i<=b;i++) {
            p*=v;
            ans+=p;
        }
        return ans;
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
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.));
    }
}
