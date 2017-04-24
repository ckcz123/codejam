import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * APAC 2016 Round B Problem A: Travel
 * Check README.md for explanation.
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), m=scanner.nextInt(), k=scanner.nextInt();
        int[][][] distance=new int[n][n][24];
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (i!=j)
                    Arrays.fill(distance[i][j], Integer.MAX_VALUE);
            }
        }
        while (m-->0) {
            int x=scanner.nextInt()-1, y=scanner.nextInt()-1;
            for (int i=0;i<24;i++) {
                distance[x][y][i]=distance[y][x][i]
                        =Math.min(distance[x][y][i], scanner.nextInt());
            }
        }
        int[][] saved=new int[24][n];
        for (int time=0;time<24;time++) {
            saved[time]=prepare(n, distance, time);
        }
        ArrayList<String> arrayList=new ArrayList<>();
        while (k-->0) {
            int d=scanner.nextInt(), t=scanner.nextInt();
            if (d<=n && saved[t][d-1]!=Integer.MAX_VALUE)
                arrayList.add(String.valueOf(saved[t][d-1]));
            else arrayList.add("-1");
        }
        return String.join(" ", arrayList);
    }

    private int[] prepare(int n, int[][][] distance, int time) {
        int[] ans=new int[n];
        Arrays.fill(ans, Integer.MAX_VALUE);
        ans[0]=0;
        Queue<Integer> queue=new LinkedList<>();
        queue.offer(0);
        while (!queue.isEmpty()) {
            int curr=queue.poll(), t=(ans[curr]+time)%24;
            for (int i=0;i<n;i++) {
                if (distance[curr][i][t]!=Integer.MAX_VALUE) {
                    if (ans[i]>ans[curr]+distance[curr][i][t]) {
                        ans[i]=ans[curr]+distance[curr][i][t];
                        queue.offer(i);
                    }
                }
            }
        }
        return ans;
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