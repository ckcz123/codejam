import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2010 Round 2
 * Problem A. Elegant Diamond
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        int[][] ans=new int[2*n-1][2*n-1];
        for (int i=0;i<2*n-1;i++) {
            Arrays.fill(ans[i], -1);
            int start=Math.max(n-1-i, i+1-n), end=2*n-2-start;
            for (int x=start;x<=end;x+=2)
                ans[i][x]=scanner.nextInt();
        }
        int min=Integer.MAX_VALUE;
        for (int i=0;i<2*n-1;i++) {
            for (int j=0;j<2*n-1;j++) {
                min=Math.min(min, able(n, ans, i, j)+1);
            }
        }
        return String.valueOf(min*min-n*n);
    }

    private int able(int n, int[][] ans, int x, int y) {
        int len=0;
        for (int i=0;i<2*n-1;i++) {
            for (int j=0;j<2*n-1;j++) {
                if (ans[i][j]==-1) continue;
                int ni=2*x-i, nj=2*y-j;
                if (ni>=0 && ni<2*n-1 && ans[ni][j]!=-1
                        && ans[ni][j]!=ans[i][j])
                    return Integer.MAX_VALUE-2;
                if (nj>=0 && nj<2*n-1 && ans[i][nj]!=-1
                        && ans[i][nj]!=ans[i][j])
                    return Integer.MAX_VALUE-2;
                len=Math.max(len, Math.abs(x-i)+Math.abs(y-j));
            }
        }
        return len;
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