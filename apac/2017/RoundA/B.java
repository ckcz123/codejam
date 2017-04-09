import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        final int r=scanner.nextInt(), c=scanner.nextInt();
        final int[][] h=new int[r][c];
        for (int i=0;i<r;i++) for (int j=0;j<c;j++) h[i][j]=scanner.nextInt();
        PriorityQueue<Integer> priorityQueue=new PriorityQueue<>(Comparator.comparingInt(o->h[o/c][o%c]));

        boolean[][] visited=new boolean[r][c];
        for (int i=0;i<r;i++) {
            for (int j: new int[]{0, c-1}) {
                visited[i][j]=true;
                priorityQueue.offer(i*c+j);
            }
        }
        for (int j=0;j<c;j++) {
            for (int i: new int[]{0, r-1}) {
                visited[i][j]=true;
                priorityQueue.offer(i*c+j);
            }
        }

        int ans=0;
        while (!priorityQueue.isEmpty()) {
            int pos=priorityQueue.poll(), x=pos/c, y=pos%c, cur=h[x][y];
            for (int[] dir: new int[][] {{0,1},{0,-1},{1,0},{-1,0}}) {
                int xx=x+dir[0], yy=y+dir[1];
                if (xx<0 || xx>=r || yy<0 || yy>=c || visited[xx][yy]) continue;
                visited[xx][yy]=true;
                if (h[xx][yy]<cur) {ans+=cur-h[xx][yy];h[xx][yy]=cur;}
                priorityQueue.offer(xx*c+yy);
            }
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("E:\\desktop\\output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=scanner.nextInt();
        for (int t=1;t<=times;t++) {
            System.out.println(String.format("Case #%d: %s", t, new Main().solve(scanner)));
        }

    }

}