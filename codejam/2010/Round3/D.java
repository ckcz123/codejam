import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2010 Round 3
 * Problem D. Different Sum
 * Only small solved.
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt(), b=scanner.nextInt(), l=Integer.toString(n, b).length();
        return String.valueOf(dfs(n, b, n, new boolean[l][b]));
    }

    private int dfs(int n, int b, int last, boolean[][] used) {
        if (n==0) return 1;
        int ans=0;
        for (int i=Math.min(n, last);i>=1;i--) {
            char[] chars=new StringBuilder(Integer.toString(i, b)).reverse().toString().toCharArray();
            boolean able=true;
            for (int j=0;j<chars.length;j++) {
                if (used[j][chars[j]-'0']) {
                    able=false;break;
                }
            }
            if (!able) continue;
            for (int j=0;j<chars.length;j++) used[j][chars[j]-'0']=true;
            ans+=dfs(n-i, b, i-1, used);
            for (int j=0;j<chars.length;j++) used[j][chars[j]-'0']=false;
        }
        return ans;
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