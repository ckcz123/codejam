import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public String solve(Scanner scanner) {
        long start=1, end=1000000000000000000L;
        int n=scanner.nextInt();
        while (n-->0) {
            long p=scanner.nextLong(), f=scanner.nextLong()*100;
            long e=(p==0?1000000000000000000L:f/p);
            long s=(p==100?f/100:f/(p+1)+1);
            start=Math.max(start, s);
            end=Math.min(end, e);
        }
        return start==end?String.valueOf(start):"-1";
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