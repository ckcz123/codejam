import java.io.PrintStream;
import java.util.*;

/**
 * KickStart 2018 Round C
 * Problem B. Fairies and Witches
 */
public class Main {

    int ans=0;

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        int[][] edges = new int[n][n];
        for (int i=0;i<n;i++) for (int j=0;j<n;j++) edges[i][j]=scanner.nextInt();
        dfs(n, 0, new boolean[n], edges, new LinkedList<>());
        return String.valueOf(ans);
    }

    public void dfs(int n, int start, boolean[] used, int[][] edges, LinkedList<Integer> sticks) {
        if (able(sticks)) ans++;

        for (int i=start;i<n;i++) {
            if (used[i]) continue;

            used[i]=true;
            for (int j=i+1;j<n;j++) {
                if (used[j]||edges[i][j]==0) continue;
                used[j]=true;
                sticks.offerLast(edges[i][j]);
                dfs(n, i+1, used, edges, sticks);
                sticks.pollLast();
                used[j]=false;
            }
            used[i]=false;
        }
    }

    private boolean able(LinkedList<Integer> sticks) {
        if (sticks.size()<3) return false;
        int sum=0, max=0;
        for (int v: sticks) {
            sum+=v;
            if (max<v) max=v;
        }
        return sum>2*max;
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