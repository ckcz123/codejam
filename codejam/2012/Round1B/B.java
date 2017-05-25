import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2012 Round 1B
 * Problem B. Tide Goes In, Tide Goes Out
 */
public class Main {

    class Grid {
        int x, y;
        double time;
        public Grid(int _x, int _y, double _time) {
            x=_x;y=_y;time=_time;
        }
    }

    private String solve(Scanner scanner) {
        int h=scanner.nextInt(), m=scanner.nextInt(), n=scanner.nextInt();
        int[][] floor=new int[m][n], ceil=new int[m][n];
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                ceil[i][j]=scanner.nextInt();
            }
        }
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                floor[i][j]=scanner.nextInt();
            }
        }
        double[][] time=new double[m][n];
        for (int i=0;i<m;i++) Arrays.fill(time[i], 1e9);
        PriorityQueue<Grid> queue=new PriorityQueue<>(Comparator.comparingDouble(g->g.time));
        queue.offer(new Grid(0,0,0));
        while (!queue.isEmpty()) {
            Grid grid=queue.poll();
            int x=grid.x, y=grid.y; double t=grid.time;

            if (t>=time[x][y]) continue;
            time[x][y]=t;
            double height=h-10*t;
            for (int[] dir: new int[][] {{-1,0},{1,0},{0,1},{0,-1}}) {
                int dx=x+dir[0], dy=y+dir[1];
                if (dx<0 || dy<0 || dx>=m || dy>=n || ceil[dx][dy]-floor[dx][dy]<50
                        || ceil[dx][dy]-floor[x][y]<50 || ceil[x][y]-floor[dx][dy]<50) continue;

                double waitTime=Math.max(0, (height+50-ceil[dx][dy])/10.);
                double nextTime=waitTime+t;
                if (nextTime>0) {
                    if (height-10*waitTime-floor[x][y]>=20) nextTime++;
                    else nextTime+=10;
                }
                queue.offer(new Grid(dx, dy, nextTime));
            }
        }
        return String.format("%.3f", time[m-1][n-1]);
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