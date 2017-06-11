import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2010 Round 3
 * Problem B. Fence
 * Only small solved.
 */
public class Main {

    public String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        long l=scanner.nextLong(); int n=scanner.nextInt();
        int[] b=new int[n];
        for (int i=0;i<n;i++) b[i]=scanner.nextInt();
        long max=Arrays.stream(b).max().orElse(0);
        int[] dp=new int[1000000];
        Arrays.fill(dp, Integer.MAX_VALUE/10);
        Queue<int[]> queue=new LinkedList<>();
        queue.offer(new int[]{0,0});
        long min=Long.MAX_VALUE;
        while (!queue.isEmpty()) {
            int[] v=queue.poll();
            if (dp[v[0]]<=v[1]) continue;
            dp[v[0]]=v[1];
            if ((l-v[0])%max==0)
                min=Math.min(min, (l-v[0])/max+v[1]);
            for (int x: b) {
                int next=x+v[0];
                if (next>=1000000 || dp[next]<=v[1]+1) continue;
                queue.offer(new int[]{next, v[1]+1});
            }
        }
        return min==Long.MAX_VALUE?"IMPOSSIBLE":String.valueOf(min);
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