import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2008 Round 3
 * Problem C. No Cheating
 */
public class Main {

    public String solve(Scanner scanner) {
        int r=scanner.nextInt(), c=scanner.nextInt();
        char[][] map=new char[r][c];
        for (int i=0;i<r;i++) map[i]=scanner.next().toCharArray();
        ArrayList<int[]> odd=new ArrayList<>(), even=new ArrayList<>();
        for (int i=0;i<r;i++) {
            for (int j=0;j<c;j++) {
                if (map[i][j]=='.') {
                    if (j%2==0) even.add(new int[]{i,j});
                    else odd.add(new int[]{i,j});
                }
            }
        }
        int u=odd.size(), v=even.size();
        boolean[][] linked=new boolean[u][v];
        for (int i=0;i<u;i++) {
            for (int j=0;j<v;j++) {
                int[] x=odd.get(i), y=even.get(j);
                if (Math.abs(x[1]-y[1])==1 && Math.abs(x[0]-y[0])<=1)
                    linked[i][j]=true;
            }
        }

        return String.valueOf(u+v-match(u,v,linked));
    }

    private int match(int u, int v, boolean[][] linked) {
        int[] match=new int[v];
        Arrays.fill(match, -1);
        int ans=0;
        for (int i=0;i<u;i++) {
            if (dfs(v, linked, i, new boolean[v], match))
                ans++;
        }
        return ans;
    }

    private boolean dfs(int v, boolean[][] linked, int index, boolean[] visited, int[] match) {
        for (int i=0;i<v;i++) {
            if (linked[index][i] && !visited[i]) {
                visited[i]=true;
                if (match[i]==-1 || dfs(v,linked,match[i],visited,match)) {
                    match[i]=index;
                    return true;
                }
            }
        }
        return false;
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