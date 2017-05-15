import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2009 Round 2
 * Problem C. Stock Charts
 * Only small solved.
 */
public class Main {

    private String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int n=scanner.nextInt(), k=scanner.nextInt();
        long[][] prices=new long[n][k];
        for (int i=0;i<n;i++) {
            for (int j=0;j<k;j++)
                prices[i][j]=scanner.nextLong();
        }
        boolean[][] able=new boolean[n][n];
        for (int i=0;i<n;i++) {
            for (int j=i+1;j<n;j++) {
                boolean can=true;
                for (int w=0;w<k-1;w++) {
                    if ((prices[i][w]-prices[j][w])*(prices[i][w+1]-prices[j][w+1])<=0) {
                        can=false;break;
                    }
                }
                able[i][j]=able[j][i]=can;
            }
        }
        HashSet<Integer> set=new HashSet<>();
        for (int i=1;i<(1<<n);i++) {
            ArrayList<Integer> list=new ArrayList<>();
            for (int j=0;j<n;j++) {
                if ((i&(1<<j))!=0) list.add(j);
            }
            boolean can=true;
            for (int x=0;x<list.size();x++) {
                for (int y=x+1;y<list.size();y++) {
                    if (!able[list.get(x)][list.get(y)]) {
                        can=false;break;
                    }
                }
                if (!can) break;
            }
            if (can)
                set.add(i);
        }

        int[] dp=new int[1<<n];
        for (int i=1;i<(1<<n);i++) {
            int ans=Integer.MAX_VALUE/10;
            if (set.contains(i)) {
                dp[i]=1;
                continue;
            }
            for (int l: set) {
                if ((i&l)!=l) continue;
                ans=Math.min(ans, dp[i^l]);
                if (ans==1) break;
            }
            dp[i]=1+ans;
        }
        return String.valueOf(dp[(1<<n)-1]);
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        long times=scanner.nextInt();
        long start=System.currentTimeMillis();
        for (long t=1;t<=times;t++) {
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



