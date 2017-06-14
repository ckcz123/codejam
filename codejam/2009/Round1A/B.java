import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2009 Round 1A
 * Problem B. Crossing the Road
 */
public class Main {

    class Point {
        int x, y;
        long t;
        public Point(int _x, int _y, long _t) {x=_x;y=_y;t=_t;}
        public String toString() {return "["+x+","+y+"]: "+t;}
    }

    public String solve(Scanner scanner) {
        int m=scanner.nextInt(), n=scanner.nextInt();
        long[][] time=new long[2*m][2*n];
        for (int i=0;i<2*m;i++) Arrays.fill(time[i], Long.MAX_VALUE/20);
        PriorityQueue<Point> queue=new PriorityQueue<>(Comparator.comparingLong(p->p.t));
        long[][][] lights=new long[m][n][];
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                lights[i][j]=new long[] {scanner.nextLong(), scanner.nextLong(), scanner.nextLong()};
            }
        }
        queue.offer(new Point(2*m-1, 0, 0));
        while (!queue.isEmpty()) {
            Point p=queue.poll();
            if (time[p.x][p.y]<=p.t) continue;
            time[p.x][p.y]=p.t;

            int x=p.x, y=p.y; long t=p.t;
            // up
            if (x>0) {
                int nx=x-1;
                if (nx%2==0) // is light
                    queue.offer(new Point(nx,y,t+1+wait(t,lights[x/2][y/2], true)));
                else queue.offer(new Point(nx,y,t+2));
            }
            // left
            if (y>0) {
                int ny=y-1;
                if (ny%2==0) // is light
                    queue.offer(new Point(x,ny,t+1+wait(t,lights[x/2][ny/2], false)));
                else queue.offer(new Point(x,ny,t+2));
            }
            // down
            if (x<2*m-1) {
                int nx=x+1;
                if (x%2==0) // is light
                    queue.offer(new Point(nx,y,t+1+wait(t,lights[x/2][y/2], true)));
                else queue.offer(new Point(nx,y,t+2));
            }
            // right
            if (y<2*n-1) {
                int ny=y+1;
                if (y%2==0) // is light
                    queue.offer(new Point(x,ny,t+1+wait(t,lights[x/2][y/2], false)));
                else queue.offer(new Point(x,ny,t+2));
            }


        }
        return String.valueOf(time[0][2*n-1]);
    }

    private long wait(long curr, long[] light, boolean northSouth) {
        long s=light[0], w=light[1], t=light[2], total=s+w;
        if (!northSouth) {
            t+=s; long tmp=s;s=w;w=tmp;
        }
        if (s==0) return Long.MAX_VALUE/10;
        long wait=(t%total+total-curr%total)%total;
        return wait>=1&&wait<=w?wait:0;
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