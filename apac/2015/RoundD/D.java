import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * APAC 2015 Round D Problem D: Itz Chess
 * Check README.md for explanation.
 */
public class Main {

    public String solve(Scanner scanner) {
        char[][] map=new char[8][8];
        int m=scanner.nextInt();
        while (m-->0) {
            String[] s=scanner.next().split("-");
            int x=s[0].charAt(0)-'A', y=s[0].charAt(1)-'1';
            map[x][y]=s[1].charAt(0);
        }

        int cnt=0;
        for (int i=0;i<8;i++) {
            for (int j=0;j<8;j++) {
                if (map[i][j]==0) continue;
                if (map[i][j]=='K')
                    cnt+=checkB(map, i, j, 1)+checkR(map, i, j, 1);
                if (map[i][j]=='Q')
                    cnt+=checkB(map, i, j, 10)+checkR(map, i, j, 10);
                if (map[i][j]=='B')
                    cnt+=checkB(map, i, j, 10);
                if (map[i][j]=='R')
                    cnt+=checkR(map, i, j, 10);
                if (map[i][j]=='N')
                    cnt+=checkN(map, i, j);
                if (map[i][j]=='P') {
                    if (i<7 && j<7 && map[i+1][j+1]!=0) cnt++;
                    if (i<7 && j>0 && map[i+1][j-1]!=0) cnt++;
                }
            }
        }
        return String.valueOf(cnt);
    }

    private int checkB(char[][] map, int x, int y, int limit) {
        int cnt=0;
        for (int[] dir: new int[][] {{-1,-1},{1,-1},{1,1},{-1,1}}) {
            for (int i=1;i<=limit;i++) {
                int dx=x+i*dir[0], dy=y+i*dir[1];
                if (dx>=0 && dx<8 && dy>=0 && dy<8 && map[dx][dy]!=0) {
                    cnt++;break;
                }
            }
        }
        return cnt;
    }
    private int checkR(char[][] map, int x, int y, int limit) {
        int cnt=0;
        for (int[] dir: new int[][] {{-1,0},{0,-1},{1,0},{0,1}}) {
            for (int i=1;i<=limit;i++) {
                int dx=x+i*dir[0], dy=y+i*dir[1];
                if (dx>=0 && dx<8 && dy>=0 && dy<8 && map[dx][dy]!=0) {
                    cnt++;break;
                }
            }
        }
        return cnt;
    }
    private int checkN(char[][] map, int x, int y) {
        int cnt=0;
        for (int i=-2;i<=2;i++) {
            for (int j=-2;j<=2;j++) {
                int dx=x+i, dy=y+j;
                if (Math.abs(i*j)==2 && dx>=0 && dx<8 && dy>=0 && dy<8
                        && map[dx][dy]!=0)
                    cnt++;
            }
        }
        return cnt;
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