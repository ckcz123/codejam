import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2009 Round 1C
 * Problem C. Bribe the Prisoners
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt(), m=scanner.nextInt();
        int[] a=new int[m];
        for (int i=0;i<m;i++) a[i]=scanner.nextInt();
        return String.valueOf(dfs(1, n, a, new HashMap<>()));
    }

    private int dfs(int start, int end, int[] a, HashMap<Integer, Integer> map) {
        if (end<start) return 0;
        int state=20000*start+end, ans=Integer.MAX_VALUE;
        if (map.containsKey(state)) return map.get(state);
        for (int v: a) {
            if (v>=start && v<=end)
                ans=Math.min(ans, end-start+dfs(start, v-1, a, map)+dfs(v+1, end, a, map));
        }
        if (ans==Integer.MAX_VALUE) ans=0;
        map.put(state, ans);
        return ans;
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