import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2017 Round 2
 * Problem A. Fresh Chocolate
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), p=scanner.nextInt();
        int[] ans=new int[n];
        for (int i=0;i<n;i++) ans[i]=scanner.nextInt();
        if (p==2) return String.valueOf(solve2(n, ans));
        if (p==3) return String.valueOf(solve3(n, ans));
        return String.valueOf(solve4(n, ans));
    }

    private int solve2(int n, int[] ans) {
        int even=0, odd=0;
        for (int x: ans) {
            if (x%2==0) even++;
            else odd++;
        }
        return even+(odd+1)/2;
    }

    private int solve3(int n, int[] ans) {
        int zero=0, one=0, two=0;
        for (int x: ans) {
            if (x%3==0) zero++;
            else if (x%3==1) one++;
            else two++;
        }
        int val=zero;
        while (one>0 && two>0) {
            one--;two--; val++;
        }
        if (one>0) {
            val+=(one+2)/3;
        }
        if (two>0) {
            val+=(two+2)/3;
        }
        return val;
    }

    private int solve4(int n, int[] ans) {
        int zero=0, one=0, two=0, three=0;
        for (int x: ans) {
            if (x%4==0) zero++;
            else if (x%4==1) one++;
            else if (x%4==2) two++;
            else three++;
        }
        int val=zero;
        while (one>0 && three>0) {
            one--;three--;val++;
        }
        while (two>=2) {
            two-=2; val++;
        }
        if (two>0) {
            if (one==0 && three==0) return val+1;
            if (one==1) return val+1;
            if (three==1) return val+1;
            if (one>=2) {
                one-=2;val++;
            }
            if (three>=2) {
                three-=2;val++;
            }
        }
        if (one>0) {
            val+=(one+3)/4;
        }
        if (three>0) {
            val+=(three+3)/4;
        }
        return val;
    }

    public static void main(String[] args) throws Exception {
//        System.setOut(new PrintStream("output.txt"));
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