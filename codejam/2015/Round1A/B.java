import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2015 Round 1A
 * Problem B. Haircut
 */
public class Main {

    private String solve(Scanner scanner) {
        int b=scanner.nextInt(), n=scanner.nextInt();
        long[] m=new long[b];
        for (int i=0;i<b;i++) m[i]=scanner.nextLong();

        long start=0, end=m[0]*n;
        while (start<end) {
            long mid=(start+end)/2;
            if (cal(mid, m)>=n) end=mid;
            else start=mid+1;
        }
        n-=cal(start-1, m);
        for (int i=0;i<b;i++) {
            if (start%m[i]==0) {
                if (--n==0) return String.valueOf(i+1);
            }
        }
        return "0";
    }

    private long cal(long x, long[] m) {
        if (x<0) return 0;
        long ans=0;
        for (long t: m) ans+=x/t+1;
        return ans;
    }


    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("E:\\desktop\\output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=scanner.nextInt();
        for (int t=1;t<=times;t++) {
            System.out.println(String.format("Case #%d: %s", t, new Main().solve(scanner)));
        }

    }

}