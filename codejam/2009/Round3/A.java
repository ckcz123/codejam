import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2009 Round 3
 * Problem A. EZ-Sokoban
 */
public class Main {

    public String solve(Scanner scanner) {
        int m=scanner.nextInt(), n=scanner.nextInt();
        char[][] input=new char[m][n];
        for (int i=0;i<m;i++) input[i]=scanner.next().toCharArray();
        char[][] start=new char[m][n], end=new char[m][n];
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if (input[i][j]=='o') {
                    start[i][j]='o';
                    end[i][j]='.';
                }
                else if (input[i][j]=='x') {
                    start[i][j]='.';
                    end[i][j]='o';
                }
                else if (input[i][j]=='w') {
                    start[i][j]=end[i][j]='o';
                }
                else start[i][j]=end[i][j]=input[i][j];
            }
        }
        return String.valueOf(bfs(m,n,encode(start),encode(end)));
    }

    private int bfs(int m, int n, String start, String end) {
        if (start.equals(end)) return 0;
        Queue<String> queue=new LinkedList<>();
        queue.offer(start);
        queue.offer("0");
        HashSet<String> visited=new HashSet<>();
        visited.add(start);
        while (!queue.isEmpty()) {
            String s=queue.poll();
            int len=Integer.parseInt(queue.poll());
            char[][] map=decode(s, m, n);
            boolean stable=isStable(map, m, n);
            for (int i=0;i<m;i++) {
                for (int j=0;j<n;j++) {
                    if (map[i][j]=='o') {

                        for (int[] dir: new int[][] {{-1,0},{0,-1},{1,0},{0,1}}) {
                            int x1=i-Math.abs(dir[0]), x2=i+Math.abs(dir[0]),
                                    y1=j-Math.abs(dir[1]), y2=j+Math.abs(dir[1]);
                            if (x1<0 || x2>=m || y1<0 || y2>=n || map[x1][y1]!='.' || map[x2][y2]!='.')
                                continue;
                            int ni=i+dir[0], nj=j+dir[1];
                            char[][] next=decode(s, m, n);
                            next[i][j]='.';
                            next[ni][nj]='o';
                            if (!stable && !isStable(next, m, n)) continue;
                            String ns=encode(next);
                            if (end.equals(ns)) return len+1;
                            if (visited.contains(ns)) continue;
                            visited.add(ns);
                            queue.offer(ns);
                            queue.offer(String.valueOf(len+1));
                        }
                    }
                }
            }
        }
        return -1;
    }

    private String encode(char[][] map) {
        return String.join("", Arrays.stream(map).map(String::valueOf).collect(Collectors.toList()));
    }

    private char[][] decode(String s, int m, int n) {
        char[][] map=new char[m][n];
        for (int i=0;i<m;i++) for (int j=0;j<n;j++) map[i][j]=s.charAt(i*n+j);
        return map;
    }

    private boolean isStable(char[][] map, int m, int n) {
        int x=-1,y=-1;
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if (map[i][j]=='o') {
                    x=i;y=j;
                }
            }
        }
        boolean[][] visited=new boolean[m][n];
        Queue<Integer> queue=new LinkedList<>();
        visited[x][y]=true;
        queue.offer(x); queue.offer(y);
        while (!queue.isEmpty()) {
            int i=queue.poll(), j=queue.poll();
            for (int[] dir: new int[][] {{-1,0},{0,-1},{1,0},{0,1}}) {
                int ni=i+dir[0], nj=j+dir[1];
                if (ni<0 || ni>=m || nj<0 || nj>=n || map[ni][nj]!='o' || visited[ni][nj]) continue;
                visited[ni][nj]=true;
                queue.offer(ni);queue.offer(nj);
            }
        }
        for (int i=0;i<m;i++)
            for (int j=0;j<n;j++)
                if (map[i][j]=='o' && !visited[i][j]) return false;
        return true;
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