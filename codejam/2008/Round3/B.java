import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2008 Round 3
 * Problem B. Portal
 */
public class Main {

    public String solve(Scanner scanner) {
        int r=scanner.nextInt(), c=scanner.nextInt();
        char[][] map=new char[r][c];
        for (int i=0;i<r;i++) map[i]=scanner.next().toCharArray();

        int n=r*c;
        boolean[][] linked=new boolean[n][n];

        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                int x1=i/c, y1=i%c, x2=j/c, y2=j%c;
                if (map[x1][y1]!='#' && map[x2][y2]!='#' && Math.abs(x1-x2)+Math.abs(y1-y2)==1) {
                    linked[i][j]=true;
                }
            }
        }
        for (int x=0;x<r;x++) {
            for (int y=0;y<c;y++) {
                if (map[x][y]=='#') continue;
                // use portal
                // is near wall
                if (x==0||y==0||x==r-1||y==c-1||
                        map[x][y-1]=='#'||map[x-1][y]=='#'||map[x+1][y]=='#'||map[x][y+1]=='#') {
                    // shoot four directions
                    for (int[] dir: new int[][] {{-1,0},{0,1},{1,0},{0,-1}}) {
                        int nx=x, ny=y;
                        while (nx>=0&&ny>=0&&nx<r&&ny<c&&map[nx][ny]!='#') {
                            nx+=dir[0];
                            ny+=dir[1];
                        }
                        nx-=dir[0];
                        ny-=dir[1];
                        if (nx!=x || ny!=y) {
                            linked[x*c+y][nx*c+ny]=true;
                        }
                    }
                }
            }
        }

        // bfs
        int start=-1, end=-1;
        for (int i=0;i<n;i++) {
            if (map[i/c][i%c]=='O') start=i;
            if (map[i/c][i%c]=='X') end=i;
        }
        Queue<Integer> queue=new LinkedList<>();
        int[] len=new int[n];
        Arrays.fill(len, Integer.MAX_VALUE/10);
        len[start]=0;
        queue.offer(start);
        while (!queue.isEmpty()) {
            int curr=queue.poll();
            for (int v=0;v<n;v++) {
                if (len[v]>len[curr]+1 && linked[curr][v]) {
                    len[v]=len[curr]+1;
                    queue.offer(v);
                }
            }
        }
        return len[end]>=Integer.MAX_VALUE/10?"THE CAKE IS A LIE":String.valueOf(len[end]);
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