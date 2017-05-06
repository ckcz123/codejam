import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private String solve(Scanner scanner) throws Throwable {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int n=scanner.nextInt(), m=scanner.nextInt(), p=scanner.nextInt();
        int[][] routes=new int[m][4];
        for (int i=0;i<m;i++) {
            routes[i]=new int[] {scanner.nextInt()-1, scanner.nextInt()-1, scanner.nextInt(),
                    scanner.nextInt()};
        }
        int[] dir=new int[p];
        for (int i=0;i<p;i++)
            dir[i]=scanner.nextInt();
        for (int i=0;i<p;i++) {
            if (!able(routes, n, m, dir, i))
                return String.valueOf(dir[i]);
        }
        return "Looks Good To Me";
    }

    private boolean able(int[][] routes, int n, int m, int[] dir, int l) {
        for (int mask=0;mask<(1<<m);mask++) {
            int[][] edges=new int[m][3];
            for (int i=0;i<m;i++) {
                edges[i][0]=routes[i][0];
                edges[i][1]=routes[i][1];
                if ((mask&(1<<i))==0)
                    edges[i][2]=routes[i][2];
                else edges[i][2]=routes[i][3];
            }
            if (check(edges, n, dir, l)) return true;
        }
        return false;
    }

    private boolean check(int[][] edges, int n, int[] dir, int l) {
        int[][] map=new int[n][n];
        for (int i=0;i<n;i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE/10);
            map[i][i]=0;
        }
        for (int[] e: edges) {
            int u=e[0], v=e[1];
            map[u][v]=Math.min(map[u][v], e[2]);
        }
        for (int k=0;k<n;k++) {
            for (int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    map[i][j]=Math.min(map[i][j], map[i][k]+map[k][j]);
                }
            }
        }
        int len=0, curr=0;
        for (int i=0;i<=l;i++) {
            len+=edges[dir[i]-1][2];
            curr=edges[dir[i]-1][1];
        }
        return map[curr][1]+len==map[0][1];

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