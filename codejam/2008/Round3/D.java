import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 Round 3
 * Problem D. Endless Knight
 */
public class Main {

    private static final int MOD = 10007;

    public String solve(Scanner scanner) {
        init();
        int m=scanner.nextInt(), n=scanner.nextInt(), k=scanner.nextInt();
        int[][] rocks=new int[k][2];
        for (int i=0;i<k;i++) rocks[i]=new int[] {scanner.nextInt(), scanner.nextInt()};
        // sort by x
        Arrays.sort(rocks, Comparator.comparingInt(o->o[0]));

        int ans=0;
        for (int x=0;x<=k;x++) {
            int sum=0;
            ArrayList<ArrayList<Integer>> lists=generate(k, x, new ArrayList<>(), 0, new ArrayList<>());
            for (ArrayList<Integer> list: lists) {
                ArrayList<int[]> paths=new ArrayList<>();
                paths.add(new int[]{1,1});
                paths.addAll(list.stream().map(i->new int[]{rocks[i][0], rocks[i][1]}).collect(Collectors.toList()));
                paths.add(new int[]{m,n});

                long v=1;
                for (int i=0;i<paths.size()-1;i++) {
                    int[] from=paths.get(i), to=paths.get(i+1);
                    v*=cal(from[0], from[1], to[0], to[1]);
                    v%=MOD;
                    if (v==0) break;
                }
                sum+=v;
                sum%=MOD;
            }
            if (x%2==0) ans+=sum;
            else ans-=sum;
            ans=(ans+MOD)%MOD;
        }
        return String.valueOf(ans);
    }

    // from [0,1,..,n-1] choose k different ones
    private ArrayList<ArrayList<Integer>> generate(int n, int k, ArrayList<Integer> choosed, int start,
                                                   ArrayList<ArrayList<Integer>> ans) {
        if (choosed.size()==k) {
            ans.add(choosed);
            return ans;
        }
        for (int i=start;i<n;i++) {
            ArrayList<Integer> list=new ArrayList<>(choosed);
            list.add(i);
            generate(n, k, list, i+1, ans);
        }
        return ans;
    }

    // (x1, y1) -> (x2, y2)
    // Assume x1<=x2
    private int cal(int x1, int y1, int x2, int y2) {
        if (x1==x2) return y1==y2?1:0;
        if (y1>y2) return 0;
        int dx=x2-x1, dy=y2-y1;
        // 2u+v=dx, 2v+u=dy
        // u+v=(dx+dy)/3
        if ((dx+dy)%3!=0) return 0;
        // u=dx-(dx+dy)%3
        // v=dy-(dx+dy)%3
        int total=(dx+dy)/3, u=dx-total, v=dy-total;
        if (u<0 || v<0) return 0;
        // C(total, u)
        return choose(total, u, MOD);
    }

    // Fast calculation of nCk mod p
    // p must be a prime
    private int choose(int n, int k, int p) {
        if (k<0|| k>n) return 0;
        if (n>=p) return choose(n/p,k/p,p)*choose(n%p,k%p,p)%p;
        // nCk
        return c[n][k];
    }

    private static int[][] c=null;
    private void init() {
        if (c!=null) return;
        c=new int[MOD][MOD];
        for (int i=0;i<MOD;i++) {
            for (int j=0;j<=i;j++) {
                // c[i][j]
                if (j==0 || j==i) c[i][j]=1;
                else c[i][j]=(c[i-1][j]+c[i-1][j-1])%MOD;
            }
        }
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