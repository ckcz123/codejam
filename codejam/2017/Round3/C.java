import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2017 Round 3
 * Problem C. Mountain Tour
 * Only small solved.
 */
public class Main {

    public String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int c=scanner.nextInt();
        int[][] trips=new int[2*c][3];
        for (int i=0;i<2*c;i++)
            trips[i]=new int[] {scanner.nextInt()-1, scanner.nextInt(), scanner.nextInt()};
        int ans=Integer.MAX_VALUE/10;
        for (int state=0;state<(1<<c);state++)
            ans=Math.min(ans, cal(c, trips, state));
        return String.valueOf(ans);
    }

    private int cal(int c, int[][] trips, int state) {
        int[] visisted=new int[c];
        int ans=0, curr=0, left=2*c;
        while (left>0) {
            int[] trip;
            if (visisted[curr]==0) {
                if ((state&(1<<curr))==0) trip=trips[2*curr];
                else trip=trips[2*curr+1];
            }
            else if (visisted[curr]==1) {
                if ((state&(1<<curr))==0) trip=trips[2*curr+1];
                else trip=trips[2*curr];
            }
            else return Integer.MAX_VALUE/10;
            visisted[curr]++;
            ans+=(trip[1]+24-ans%24)%24+trip[2];
            curr=trip[0];
            left--;
        }
        return curr==0?ans:Integer.MAX_VALUE/10;
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