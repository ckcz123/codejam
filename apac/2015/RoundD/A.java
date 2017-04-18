import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        int[][] data=new int[n][n];
        for (int i=0;i<n;i++)
            for (int j=0;j<n;j++)
                data[i][j]=scanner.nextInt();
        int[][] step=new int[n][n];
        int ans=0, id=n+1;
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (ans<dfs(n, data, step, i, j)
                        || (ans==step[i][j] && id>data[i][j])) {
                    ans=step[i][j];id=data[i][j];
                }
            }
        }
        return id+" "+ans;
    }

    private int dfs(int n, int[][] data, int[][] step, int x, int y) {
        if (step[x][y]!=0) return step[x][y];
        step[x][y]=1;
        for (int[] dir: new int[][] {{-1,0},{0,-1},{1,0},{0,1}}) {
            int dx=x+dir[0], dy=y+dir[1];
            if (dx>=0 && dx<n && dy>=0 && dy<n && data[dx][dy]==data[x][y]+1)
                step[x][y]+=dfs(n, data, step, dx, dy);
        }
        return step[x][y];

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
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.));
    }
}