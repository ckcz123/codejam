import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), k=scanner.nextInt();
        int[][] prices=new int[n][k];
        for (int i=0;i<n;i++) {
            for (int j=0;j<k;j++)
                prices[i][j]=scanner.nextInt();
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
                if (!can) continue;
                //able[i][j]=able[j][i]=can;
                if (prices[i][0]<prices[j][0])
                    able[i][j]=true;
                else able[j][i]=true;
            }
        }
        int[] dp=new int[1<<n];
        for (int i=1;i<(1<<n);i++) {
            dp[i]=dp(n, able, i, dp);
        }
        return String.valueOf(dp[(1<<n)-1]);

    }

    private int dp(int n, boolean[][] able, int state, int[] dp) {
        boolean[] used=new boolean[n];
        int curr=0;
        for (int i=0;i<n;i++) {
            if ((state&(1<<i))==0)
                used[i]=true;
        }
        int[] cnt=new int[n];
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (used[i] || used[j] || !able[i][j]) continue;
                cnt[j]++;
            }
        }
        for (int i=0;i<n;i++) {
            if (!used[i] && cnt[i]==0) {
                curr=i;
            }
        }
        ArrayList<ArrayList<Integer>> lists=generate(n, used, able, new ArrayList<>(), curr,
                new ArrayList<>());
        int ans=Integer.MAX_VALUE/10;
        for (ArrayList<Integer> list: lists) {
            int ns=state;
            for (int v: list) ns^=(1<<v);
            ans=Math.min(ans, dp[ns]);
        }
        return 1+ans;
    }

    private ArrayList<ArrayList<Integer>> generate(int n, boolean[] used,
                                                   boolean[][] able, ArrayList<Integer> route,
                                                   int curr, ArrayList<ArrayList<Integer>> ans) {
        ArrayList<Integer> list=new ArrayList<>(route);
        list.add(curr);
        ans.add(list);
        for (int i=0;i<n;i++) {
            if (!used[i] && able[curr][i]) {
                generate(n, used, able, list, i, ans);
            }
        }
        return ans;
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



