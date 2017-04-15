import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private String solve(Scanner scanner) {
        int m=scanner.nextInt(), n1=0, n2=0;
        String[][] strings=new String[m][2];
        HashMap<String, Integer> mp1=new HashMap<>(), mp2=new HashMap<>();
        for (int i=0;i<m;i++) {
            strings[i][0]=scanner.next();
            strings[i][1]=scanner.next();
            if (!mp1.containsKey(strings[i][0]))
                mp1.put(strings[i][0], n1++);
            if (!mp2.containsKey(strings[i][1]))
                mp2.put(strings[i][1], n2++);
        }
        boolean[][] g=new boolean[n1][n2];
        for (String[] s: strings)
            g[mp1.get(s[0])][mp2.get(s[1])]=true;
        int res=0;
        int[] linked=new int[n2];
        Arrays.fill(linked, -1);
        for (int u=0;u<n1;u++) {
            if (dfs(u, new boolean[n2], linked, g, n2))
                res++;
        }
        boolean[] usedu=new boolean[n1], usedv=new boolean[n2];
        for (int i=0;i<n2;i++) {
            if (linked[i]!=-1) {
                usedv[i]=true;
                usedu[linked[i]]=true;
            }
        }
        for (String[] s: strings) {
            int u=mp1.get(s[0]), v=mp2.get(s[1]);
            if (!usedu[u]) {
                res++;
                usedu[u]=true;
            }
            if (!usedv[v]) {
                res++;
                usedv[v]=true;
            }
        }
        return String.valueOf(m-res);
    }

    private boolean dfs(int u, boolean[] used, int[] linked, boolean[][] g, int n2) {
        for (int v=0;v<n2;v++) {
            if (g[u][v] && !used[v]) {
                used[v]=true;
                if (linked[v]==-1 || dfs(linked[v], used, linked, g, n2)) {
                    linked[v]=u;
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
//        System.setOut(new PrintStream("output.txt"));
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