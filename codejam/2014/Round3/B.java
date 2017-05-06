import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2014 Round 3
 * Problem B. Last Hit
 */
public class Main {

    public String solve(Scanner scanner) {
        int p=scanner.nextInt(), q=scanner.nextInt(), n=scanner.nextInt();
        int[] a=new int[n];
        int[] g=new int[n];
        for (int i=0;i<n;i++) {
            a[i]=scanner.nextInt();
            g[i]=scanner.nextInt();
        }
        return String.valueOf(solve(n,p,q,a,g,0,a[0],0,0,new HashMap<>()));
    }

    private int solve(int n, int p, int q, int[] a, int[] g, int curr, int h, int extra,
                      int turn, HashMap<String, Integer> map) {
        String state=curr+","+h+","+extra+","+turn;
        if (map.containsKey(state)) return map.get(state);
        if (h<=0) {
            int ans=curr==n-1?0:solve(n,p,q,a,g,curr+1,a[curr+1],extra,turn,map);
            map.put(state, ans);
            return ans;
        }
        if (turn==1) return solve(n,p,q,a,g,curr,h-q,extra,1-turn,map);
        int ans=Math.max(
                solve(n,p,q,a,g,curr,h,extra+1,1-turn,map),
                (h<=p?g[curr]:0)+
                        Math.max(
                                extra==0?0:
                                        solve(n,p,q,a,g,curr,h-p,extra-1,turn,map),
                                solve(n,p,q,a,g,curr,h-p,extra,1-turn,map))
        );
        map.put(state, ans);
        return ans;
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