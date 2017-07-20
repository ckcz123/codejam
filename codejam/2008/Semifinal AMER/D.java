import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 AMER Semifinal
 * Problem D. King
 * Only small solved.
 */
public class Main {

    public String solve(Scanner scanner) throws Exception {
        int r=scanner.nextInt(), c=scanner.nextInt(), x=0, y=0;
        char[][] map=new char[r][c];
        for (int i=0;i<r;i++) {
            map[i]=scanner.next().toCharArray();
            for (int j=0;j<c;j++) {
                if (map[i][j]=='K') {
                    map[i][j]='.';
                    x=i;y=j;
                }
            }
        }
        return dfs(r, c, map, x, y)?"A":"B";
    }

    private boolean dfs(int r, int c, char[][] map, int x, int y) {
        map[x][y]='#';
        for (int dx=-1;dx<=1;dx++) {
            for (int dy=-1;dy<=1;dy++) {
                int nx=x+dx, ny=y+dy;
                if (nx>=0 && nx<r && ny>=0 && ny<c && map[nx][ny]=='.') {
                    if (!dfs(r, c, map, nx, ny)) {
                        map[x][y]='.';
                        return true;
                    }
                }
            }
        }
        map[x][y]='.';
        return false;
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