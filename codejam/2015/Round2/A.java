import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2015 Round 2
 * Problem A. Pegman
 */
public class Main {

    private String solve(Scanner scanner) {
        int r=scanner.nextInt(), c=scanner.nextInt();
        String[] map=new String[r];
        for (int i=0;i<r;i++) map[i]=scanner.next();
        int[][] dir=new int[][] {{1,0},{-1,0},{0,1},{0,-1}};
        boolean[][][] danger=new boolean[r][c][4];
        for (int i=0;i<c;i++) {
            getDanger(0,i,0,r,c,danger,map,dir);
            getDanger(r-1,i,1,r,c,danger,map,dir);
        }
        for (int i=0;i<r;i++) {
            getDanger(i,0,2,r,c,danger,map,dir);
            getDanger(i,c-1,3,r,c,danger,map,dir);
        }
        int cnt=0;
        for (int i=0;i<r;i++) {
            for (int j=0;j<c;j++) {
                char ch=map[i].charAt(j);
                if (ch=='.') continue;
                if (danger[i][j][0] && danger[i][j][1] && danger[i][j][2] && danger[i][j][3])
                    return "IMPOSSIBLE";
                if ((ch=='^' && danger[i][j][0]) || (ch=='>' && danger[i][j][3])
                        || (ch=='<' && danger[i][j][2]) || (ch=='v' && danger[i][j][1]))
                    cnt++;
            }
        }
        return String.valueOf(cnt);
    }

    private void getDanger(int x, int y, int d, int r, int c,
                           boolean[][][] danger, String[] map, int[][] dir) {
        if (x<0 || x>=r || y<0 || y>=c) return;
        danger[x][y][d]=true;
        if (map[x].charAt(y)=='.') getDanger(x+dir[d][0], y+dir[d][1], d,
                r, c, danger, map, dir);
    }


    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("E:\\desktop\\output.txt"));
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