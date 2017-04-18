import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt(), m=scanner.nextInt(), n2=(1<<n), n3=(1<<n2);
        int[][] distance=new int[n2][n2];
        while (m-->0) {
            int x=scanner.nextInt()-1, k=scanner.nextInt(), b=scanner.nextInt();
            while (b-->0) {
                int y=scanner.nextInt()-1;
                distance[x][y]=distance[y][x]=Math.max(distance[x][y], k);
            }
        }
        return dfs(n, n3-1, new int[n+1][n3], distance)?"YES":"NO";
    }

    private boolean dfs(int n, int x, int[][] dp, int[][] distance) {
        if (dp[n][x]!=0) return dp[n][x]==1;
        if (n==0 || x==0) return (dp[n][x]=1)==1;
        ArrayList<Integer> memberList=new ArrayList<>();
        for (int i=0;i<17;i++) {
            if ((x&(1<<i))!=0) memberList.add(i);
        }
        int l=memberList.size();
        if (l>(1<<n)) return (dp[n][x]=2)==1;
        int[] members=memberList.stream().mapToInt(i->i).toArray();
        for (int i=0;i<l;i++) {
            for (int j=i+1;j<l;j++) {
                if (distance[members[i]][members[j]]>=n)
                    return (dp[n][x]=2)==1;
            }
        }
        int l2=(1<<l);
        for (int i=0;i<l2;i++) {
            int a=0,b=0;
            for (int j=0;j<l;j++) {
                if ((i&(1<<j))!=0)
                    a|=(1<<members[j]);
                else
                    b|=(1<<members[j]);
            }
            if (dfs(n-1, a, dp, distance) && dfs(n-1, b, dp, distance))
                return (dp[n][x]=1)==1;
        }
        return (dp[n][x]=2)==1;
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