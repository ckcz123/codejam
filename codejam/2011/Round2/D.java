import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2011 Round 2
 * Problem D. A.I. War
 * Only small solved.
 */
public class Main {

    private String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int n=scanner.nextInt();
        int[][] distance=new int[n][n];
        for (int i=0;i<n;i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE/10);
        }
        int m=scanner.nextInt();
        while (m-->0) {
            String[] s=scanner.next().split(",");
            int x=Integer.parseInt(s[0]), y=Integer.parseInt(s[1]);
            distance[x][y]=distance[y][x]=1;
        }
        for (int k=0;k<n;k++) {
            for (int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    distance[i][j]=Math.min(distance[i][j], distance[i][k]+distance[k][j]);
                }
            }
        }
        int d=distance[0][1];
        return String.valueOf((d-1)+" "+getThreaten(n, distance, 0, new int[d], 0, d));
    }

    private int getThreaten(int n, int[][] distance, int curr, int[] route, int l, int d) {
        if (l>=d) return Integer.MAX_VALUE/10;
        route[l]=curr;
        if (distance[curr][1]==1)
            return cal(n, distance, route);
        int max=0;
        for (int i=0;i<n;i++) {
            if (distance[i][curr]==1 && distance[i][1]==d-l-1) {
                max=Math.max(max, getThreaten(n, distance, i, route, l+1, d));
            }
        }
        return max;
    }

    private int cal(int n, int[][] distance, int[] route) {
        HashSet<Integer> set=new HashSet<>();
        for (int r: route) set.add(r);
        HashSet<Integer> ans=new HashSet<>();
        for (int r: route) {
            for (int i=0;i<n;i++) {
                if (distance[i][r]==1 && !set.contains(i))
                    ans.add(i);
            }
        }
        return ans.size();
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