import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2010 Round 1C
 * Problem C. Making Chess Boards
 */
public class Main {

    class Point {
        int x, y, l;
        public Point(int _x, int _y, int _l) {
            x=_x;y=_y;l=_l;
        }
    }
    private String solve(Scanner scanner) {
        PriorityQueue<Point> queue=new PriorityQueue<>(
                (p1, p2) -> p1.l!=p2.l?p2.l-p1.l:p1.x!=p2.x?p1.x-p2.x:p1.y-p2.y
        );
        int m=scanner.nextInt(), n=scanner.nextInt();
        int[][] map=new int[m][n];
        for (int i=0;i<m;i++) {
            String s=scanner.next();
            for (int j=0;j<n/4;j++) {
                int c=Integer.parseInt(s.substring(j,j+1), 16);
                for (int k=0;k<4;k++) {
                    if ((c&(1<<(3-k)))!=0) map[i][4*j+k]=1;
                    else map[i][4*j+k]=0;
                }
            }
        }
        int[][] cnt=new int[m][n];
        for (int i=m-1;i>=0;i--) {
            for (int j=n-1;j>=0;j--) {
                if (i==m-1 || j==n-1) cnt[i][j]=1;
                else {
                    if (map[i][j]==map[i][j+1] || map[i][j]==map[i+1][j]
                            || map[i][j]!=map[i+1][j+1]) cnt[i][j]=1;
                    else cnt[i][j]=1+Math.min(Math.min(cnt[i][j+1], cnt[i+1][j]), cnt[i+1][j+1]);
                }
                queue.offer(new Point(i,j,cnt[i][j]));
            }
        }
        TreeMap<Integer, Integer> treeMap=new TreeMap<>();
        while (!queue.isEmpty()) {
            Point p=queue.poll();
            if (cnt[p.x][p.y]!=p.l || p.l==0) continue;
            treeMap.put(p.l, treeMap.getOrDefault(p.l, 0)+1);

            // update
            for (int i=Math.max(0, p.x-p.l);i<p.x+p.l;i++) {
                for (int j=Math.max(0, p.y-p.l);j<p.y+p.l;j++) {
                    cnt[i][j]=Math.min(cnt[i][j], Math.max(0, Math.max(p.x-i, p.y-j)));
                    queue.offer(new Point(i,j,cnt[i][j]));
                }
            }
        }
        ArrayList<String> list=new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: treeMap.entrySet()) {
            list.add(entry.getKey()+" "+entry.getValue());
        }
        Collections.reverse(list);
        return list.size()+"\n"+String.join("\n", list);
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
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}