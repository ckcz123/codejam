import java.io.*;
import java.util.*;

/**
 * Facebook Hacker Cup 2017 Round 2
 * Problem A. Subtle Sabotage
 */
public class Main {

    public String solve(Scanner scanner) {
        int m=scanner.nextInt(), n=scanner.nextInt(), k=scanner.nextInt();
        if (m>=n) {int x=m;m=n;n=x;}
        int ans=Math.min(cal1(m,n,k),cal2(m,n,k));
        return ans==Integer.MAX_VALUE?"-1":String.valueOf(ans);
    }

    private int cal1(int m, int n, int k) {
        return m>=k+1&&n>=2*k+3?(m-1)/k+1:Integer.MAX_VALUE;
    }

    private int cal2(int m, int n, int k) {
        if (k==1)
            return m>=3&&n>=5?5:Integer.MAX_VALUE;
        return m>=2*k+1&&n>=3*k+1?4:Integer.MAX_VALUE;
    }

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
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
