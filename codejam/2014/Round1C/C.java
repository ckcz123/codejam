import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2014 Round 1C
 * Problem C. Enclosure
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), m=scanner.nextInt(), k=scanner.nextInt();
        if (n<=2 || m<=2 || k<=4) return String.valueOf(k);
        if (n<m) {int t=n;n=m;m=t;}
        if (k>m*n-4) return String.valueOf(2*m+2*n-4+k-m*n);
        return String.valueOf(solve(m,n,k));
    }

    private int solve(int m, int n, int k) {
        if (m==3&&n==3) return 4;
        int min=Integer.MAX_VALUE;
        for (int l=2;l<=n-2;l++) {
            for (int x=1;x<=m-2;x++) {
                int empty=(m-2)-x;
                int up=empty/2, down=empty-up;
                int totalSpace=x*l+cal(l-2, up)+cal(l-2, down), stone=2*(x+l);
                if (totalSpace+stone>=k) min=Math.min(min, stone);
                if (totalSpace+stone+1+(cal(l-1, down)-cal(l-2, down))>=k)
                    min=Math.min(min, stone+1);
            }
        }
        return min;
    }

    private int cal(int l, int bound) {
        int s=0;
        while (l>=1 && bound-->0) {
            s+=l;l-=2;
        }
        return s;
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