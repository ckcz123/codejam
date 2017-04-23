import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int n=scanner.nextInt();
        HashSet<Integer>[] sets=new HashSet[n];
        for (int i=0;i<n;i++) sets[i]=new HashSet<>();
        int[] coins=new int[n];
        for (int i=0;i<n;i++)
            coins[i]=scanner.nextInt();
        for (int i=0;i<n-1;i++) {
            int j=scanner.nextInt()-1;
            sets[i].add(j);
            sets[j].add(i);
        }
        int ans=Integer.MIN_VALUE;
        for (int i=0;i<n;i++) {
            int min=Integer.MAX_VALUE;
            for (int j=0;j<n;j++) {
                min=Math.min(min, dfs(n, i, j, 0, coins, sets, new HashSet<>()));
            }
            ans=Math.max(ans, min);
        }
        return String.valueOf(ans);
    }

    private int dfs(int n, int x, int y, int turn, int[] coins, HashSet<Integer>[] sets, HashSet<String> visited) {
        String state=x+","+y+","+turn;
        if (visited.contains(state)) return 0;
        visited.add(state);
        int c=coins[x];
        coins[x]=0;
        int val=Integer.MIN_VALUE;
        ArrayList<Integer> linked=new ArrayList<>(sets[x]);
        for (int d: linked) {
            sets[x].remove(d);
            sets[d].remove(x);
            val=Math.max(val, -dfs(n, y, d, 1-turn, coins, sets, visited));
            sets[x].add(d);
            sets[d].add(x);
        }
        if (val==Integer.MIN_VALUE)
            val=-dfs(n, y, x, 1-turn, coins, sets, visited);
        coins[x]=c;
        return val+c;
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