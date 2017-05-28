import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2011 Qualification Round
 * Problem A. Bot Trust
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        int[][] seq=new int[n][2];
        for (int i=0;i<n;i++) {
            if (scanner.next().charAt(0)=='O')
                seq[i][0]=1;
            seq[i][1]=scanner.nextInt();
        }
        return String.valueOf(solve(seq, n, 0, 1, 1));
    }

    private int solve(int[][] seq, int n, int curr, int x, int y) {
        if (curr==n) return 0;

        int nx=x, ny=y;
        for (int i=curr;i<n;i++) {
            if (seq[i][0]==0) {
                nx=seq[i][1]; break;
            }
        }
        for (int i=curr;i<n;i++) {
            if (seq[i][0]==1) {
                ny=seq[i][1]; break;
            }
        }
        if (nx>x) nx=x+1;
        if (nx<x) nx=x-1;
        if (ny>y) ny=y+1;
        if (ny<y) ny=y-1;
        return 1+solve(seq, n,
                (seq[curr][0]==0 && nx==x) || (seq[curr][0]==1 && ny==y)?curr+1:curr, nx, ny);
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