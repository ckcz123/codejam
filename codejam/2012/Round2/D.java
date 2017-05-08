import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2012 Round 2
 * Problem D. Descending in the Dark
 * Only small solved.
 */
public class Main {

    class Point {
        int x,y;
        public Point(int _x, int _y) {x=_x;y=_y;}
        public int hashCode() {return 10000*x+y;}
        public boolean equals(Object another) {
            if (!(another instanceof Point)) return false;
            Point p=(Point)another;
            return x==p.x&&y==p.y;
        }
        public String toString() {return "["+x+","+y+"]";}
    }

    private String solve(Scanner scanner) throws Throwable {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int m=scanner.nextInt(), n=scanner.nextInt(), mx=0;
        Point[] points=new Point[10];
        char[][] chars=new char[m][n];
        for (int i=0;i<m;i++) {
            chars[i]=scanner.next().toCharArray();
            for (int j=0;j<n;j++) {
                if (chars[i][j]>='0' && chars[i][j]<='9') {
                    int k=chars[i][j]-'0';
                    points[k]=new Point(i,j);
                    mx=Math.max(mx, k);
                }
            }
        }
        ArrayList<String> ans=new ArrayList<>();
        ans.add("");
        for (int i=0;i<=mx;i++) {
            ans.add(i+": "+run(m,n,chars,points[i].x,points[i].y));
        }
        return String.join("\n", ans);
    }

    private String run(int m, int n, char[][] chars, int posx, int posy) {
        boolean[][] able=calAble(m,n,chars,posx,posy);
        HashSet<Point> origin=new HashSet<>();
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if (able[i][j])
                    origin.add(new Point(i,j));
            }
        }
        int total=origin.size();
        return total+" "+(isLucky(m, n, chars, able, origin)?"Lucky":"Unlucky");
    }

    private boolean[][] calAble(int m, int n, char[][] chars, int posx, int posy) {
        boolean[][] able=new boolean[m][n];
        able[posx][posy]=true;
        Queue<Point> queue=new LinkedList<>();
        queue.offer(new Point(posx, posy));
        while (!queue.isEmpty()) {
            Point p=queue.poll();
            for (int[] d: new int[][] {{-1,0},{0,1},{0,-1}}) {
                int nx=p.x+d[0], ny=p.y+d[1];
                if (!able[nx][ny] && chars[nx][ny]!='#') {
                    able[nx][ny]=true;
                    queue.offer(new Point(nx, ny));
                }
            }
        }
        return able;
    }

    private boolean isLucky(int m, int n, char[][] chars, boolean[][] able, HashSet<Point> points) {
        HashSet<Point> set=new HashSet<>(points);
        for (Point p: set) {
            if (!able[p.x][p.y]) return false;
        }
        if (set.size()==1) return true;

        // try to go right......
        while (true) {
            HashSet<Point> right=go(set, chars, 0, 1);
            if (equals(right,set)) break;
            set=right;
        }
        if (set.size()==1) return true;

        // down & left or right
        int dy=-1;
        while (true) {
            // try down
            HashSet<Point> down=go(set, chars, 1, 0);
            if (!equals(down, set) && isLucky(m, n, chars, able, down)) return true;

            HashSet<Point> next=go(set, chars, 0, dy);
            if (equals(next,set)) {
                if (dy==-1) dy=1;
                else break;
            }
            set=next;
            if (set.size()==1) return true;
        }
        return false;
    }

    private HashSet<Point> go(HashSet<Point> points, char[][] chars, int dx, int dy) {
        HashSet<Point> set=new HashSet<>();
        for (Point p: points) {
            Point q=new Point(p.x+dx, p.y+dy);
            if (chars[q.x][q.y]=='#') q=p;
            set.add(q);
        }
        return set;
    }

    private boolean equals(HashSet<Point> set1, HashSet<Point> set2) {
        if (set1.size()!=set2.size()) return false;
        for (Point p: set1) if (!set2.contains(p)) return false;
        return true;
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