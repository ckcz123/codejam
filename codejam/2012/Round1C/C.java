import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2012 Round 1C
 * Problem C. Box Factory
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt(), m=scanner.nextInt();
        long[] a=new long[n], b=new long[m];
        int[] ta=new int[n], tb=new int[m];
        for (int i=0;i<n;i++) {
            a[i]=scanner.nextLong(); ta[i]=scanner.nextInt();
        }
        for (int i=0;i<m;i++) {
            b[i]=scanner.nextLong(); tb[i]=scanner.nextInt();
        }
        return String.valueOf(dfs(a,b,ta,tb,n-1,a[n-1],m-1,b[m-1],new HashMap<>()));
    }

    private long dfs(long[] a, long[] b, int[] ta, int[] tb, int n, long l1, int m, long l2,
                     HashMap<String, Long> map) {
        if (l1==0) {
            if (n==0) return 0;
            return dfs(a,b,ta,tb,n-1,a[n-1], m, l2, map);
        }
        if (l2==0) {
            if (m==0) return 0;
            return dfs(a,b,ta,tb,n,l1,m-1, b[m-1], map);
        }
        String state=n+"~"+l1+","+m+"~"+l2;
        if (map.containsKey(state)) return map.get(state);

        long ans=0;
        if (ta[n]==tb[m]) {
            long mn=Math.min(l1, l2);
            ans=mn+dfs(a,b,ta,tb,n,l1-mn,m,l2-mn,map);
        }
        else {
            ans=Math.max(dfs(a,b,ta,tb,n,0,m,l2,map),dfs(a,b,ta,tb,n,l1,m,0,map));
        }
        map.put(state, ans);
        return ans;
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