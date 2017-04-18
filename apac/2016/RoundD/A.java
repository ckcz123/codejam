import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public String solve(Scanner scanner) {
        int r=scanner.nextInt(), c=scanner.nextInt();
        char[][] map=new char[r][];
        for (int i=0;i<r;i++) map[i]=scanner.next().toCharArray();
        ArrayList<String> ans=new ArrayList<>();
        ans.add("");
        int q=scanner.nextInt();
        while (q-->0) {
            String type=scanner.next();
            if (type.charAt(0)=='M') {
                int x=scanner.nextInt(), y=scanner.nextInt();
                map[x][y]=scanner.next().charAt(0);
            }
            else ans.add(String.valueOf(query(map, r, c)));
        }
        return String.join("\n", ans);
    }

    private int query(char[][] map, int r, int c) {
        boolean[][] visited=new boolean[r][c];
        int cnt=0;
        for (int i=0;i<r;i++) {
            for (int j=0;j<c;j++) {
                if (map[i][j]=='0' || visited[i][j]) continue;
                cnt++;
                Queue<Integer> queue=new LinkedList<>();
                visited[i][j]=true;
                queue.offer(i);queue.offer(j);
                while (!queue.isEmpty()) {
                    int x=queue.poll(), y=queue.poll();
                    for (int[] dir: new int[][] {{0,-1},{-1,0},{0,1},{1,0}}) {
                        int dx=x+dir[0], dy=y+dir[1];
                        if (dx>=0 && dx<r && dy>=0 && dy<c && map[dx][dy]=='1' && !visited[dx][dy]) {
                            visited[dx][dy]=true;
                            queue.offer(dx);queue.offer(dy);
                        }
                    }
                }
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