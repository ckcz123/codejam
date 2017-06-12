import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2017 Round 3
 * Problem D. Slate Modern
 * Only small solved.
 */
public class Main {

    public String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int r=scanner.nextInt(), c=scanner.nextInt(), n=scanner.nextInt();
        long d=scanner.nextLong();
        long[][] upper=new long[r][c], lower=new long[r][c];
        for (int i=0;i<r;i++) {
            Arrays.fill(upper[i], Long.MAX_VALUE/100);
            Arrays.fill(lower[i], 1);
        }
        while (n-->0)
            bfs(r, c, d, scanner.nextInt()-1, scanner.nextInt()-1, scanner.nextLong(), upper, lower);

        long ans=0;
        for (int i=0;i<r;i++) {
            for (int j=0;j<c;j++) {
                if (upper[i][j]<lower[i][j]) return "IMPOSSIBLE";
                ans+=upper[i][j];
            }
        }
        return String.valueOf(ans%1000000007);
    }

    private void bfs(int r, int c, long d, int x, int y, long b, long[][] upper, long[][] lower) {
        boolean[][] visited=new boolean[r][c];
        upper[x][y]=Math.min(upper[x][y], b);
        lower[x][y]=Math.max(lower[x][y], b);
        visited[x][y]=true;
        Queue<int[]> queue=new LinkedList<>();
        queue.offer(new int[] {x,y});
        while (!queue.isEmpty()) {
            int[] v=queue.poll();
            for (int[] dir: new int[][] {{-1,0},{0,-1},{1,0},{0,1}}) {
                int nx=v[0]+dir[0], ny=v[1]+dir[1];
                if (nx<0 || nx>=r || ny<0 || ny>=c || visited[nx][ny]) continue;
                visited[nx][ny]=true;
                upper[nx][ny]=Math.min(upper[nx][ny], upper[v[0]][v[1]]+d);
                lower[nx][ny]=Math.max(lower[nx][ny], lower[v[0]][v[1]]-d);
                queue.offer(new int[]{nx,ny});
            }
        }
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