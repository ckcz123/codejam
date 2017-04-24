import java.io.PrintStream;
import java.util.*;

/**
 * APAC 2016 Practice Round Problem A: Bad Horse
 * Check README.md for explanation.
 */
public class Main {

    private String solve(Scanner scanner) {
        HashMap<String, Integer> map=new HashMap<>();
        int n=0, m=scanner.nextInt();
        String[][] strings=new String[m][2];
        for (int i=0;i<m;i++) {
            strings[i][0]=scanner.next();
            strings[i][1]=scanner.next();
            if (!map.containsKey(strings[i][0])) map.put(strings[i][0], n++);
            if (!map.containsKey(strings[i][1])) map.put(strings[i][1], n++);
        }
        boolean[][] linked=new boolean[n][n];
        for (String[] s: strings) {
            int x=map.get(s[0]), y=map.get(s[1]);
            linked[x][y]=linked[y][x]=true;
        }
        return dfs(n, linked, 0, 1, new int[n])?"Yes":"No";
    }

    private boolean dfs(int n, boolean[][] linked, int curr, int color, int[] mark) {
        if (mark[curr]!=0) {
            if (mark[curr]!=color) return false;
            return true;
        }
        mark[curr]=color;
        for (int i=0;i<n;i++) {
            if (linked[curr][i] && !dfs(n, linked, i, -color, mark)) return false;
        }
        return true;
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