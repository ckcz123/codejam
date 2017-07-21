import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 APAC Semifinal
 * Problem B. Apocalypse Soon
 */
public class Main {

    public String solve(Scanner scanner) throws Exception {
        int c=scanner.nextInt(), r=scanner.nextInt(), y=scanner.nextInt()-1, x=scanner.nextInt()-1;
        int[][] data=new int[r][c];
        for (int i=0;i<r;i++)
            for (int j=0;j<c;j++)
                data[i][j]=scanner.nextInt();
        int ans=dfs(r, c, data, x, y);
        return ans<Integer.MAX_VALUE/10?String.format("%d day(s)", ans):"forever";
    }

    private int dfs(int r, int c, int[][] data, int x, int y) {
        int[][] dirs=new int[][] {{1,0},{0,1},{0,-1},{-1,0}};

        ArrayList<int[]> list=new ArrayList<>();
        for (int[] dir: dirs) {
            int dx=x+dir[0], dy=y+dir[1];
            if (dx>=0 && dx<r && dy>=0 && dy<c && data[dx][dy]>0)
                list.add(new int[]{dx,dy});
        }

        if (list.isEmpty()) return Integer.MAX_VALUE/10;

        int[][] next=clone(data);
        for (int i=0;i<r;i++) {
            for (int j=0;j<c;j++) {
                if (data[i][j]==0) continue;
                if (i==x && j==y) continue;

                int nx=-1, ny=-1, max=1;
                for (int[] dir: dirs) {
                    int dx=i+dir[0], dy=j+dir[1];
                    if (dx>=0 && dx<r && dy>=0 && dy<c && data[dx][dy]>=max) {
                        nx=dx; ny=dy; max=data[dx][dy];
                    }
                }
                if (nx<0) continue;
                next[nx][ny]=Math.max(0, next[nx][ny]-data[i][j]);
            }
        }

        if (next[x][y]==0) return 0;

        int max=dfs(r, c, clone(next), x, y);
        for (int[] v: list) {
            int[][] tmp=clone(next);
            tmp[v[0]][v[1]]=Math.max(0, tmp[v[0]][v[1]]-data[x][y]);
            max=Math.max(max, dfs(r, c, tmp, x, y));
        }
        return 1+max;
    }

    private int[][] clone(int[][] origin) {
        int[][] ans=new int[origin.length][];
        for (int i=0;i<origin.length;i++)
            ans[i]=origin[i].clone();
        return ans;
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=Integer.parseInt(scanner.nextLine());
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