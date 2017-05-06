import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2014 Round 2
 * Problem C. Don't Break The Nile
 * Only small solved.
 */
public class Main {

    private String solve(Scanner scanner) throws Throwable {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) throws Throwable {
        int w=scanner.nextInt(), h=scanner.nextInt();
        int[][] map=new int[h][w];
        int b=scanner.nextInt();
        for (int i=0;i<b;i++) {
            int x0=scanner.nextInt(), y0=scanner.nextInt(), x1=scanner.nextInt(),
                    y1=scanner.nextInt();
            for (int x=x0;x<=x1;x++) {
                for (int y=y0;y<=y1;y++) {
                    map[y][x]=-1;
                }
            }
        }
        int ans=0;
        for (int i=0;i<w;i++) {
            if (dfs(h, w, 0, i, 0, map, i+1))
                ans++;
        }
        return String.valueOf(ans);
    }

    private boolean dfs(int h, int w, int x, int y, int dir, int[][] map, int color) {
        if (x==h) return true;
        if (x<0) return false;
        if (map[x][y]!=0 && map[x][y]!=color) return false;
        map[x][y]=color;
        int[][] dirs=new int[][] {{1,0},{0,-1},{-1,0},{0,1}};
        for (int i=1;i>=-2;i--) {
            int d=dir+i;
            if (d<0) d+=4; if (d>=4) d-=4;
            int dx=x+dirs[d][0], dy=y+dirs[d][1];
            if (dx==h) return true;
            if (dx==-1 && dy==color-1) return false;
            if (dx>=0 && dx<h && dy>=0 && dy<w && (map[dx][dy]==0 || map[dx][dy]==color)) {
                return dfs(h, w, dx, dy, d, map, color);
            }
        }
        return false;
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
            }
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}