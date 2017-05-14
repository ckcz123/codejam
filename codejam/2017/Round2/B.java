import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int n=scanner.nextInt(), c=scanner.nextInt(), m=scanner.nextInt();
        ArrayList<Integer> alist=new ArrayList<>(), blist=new ArrayList<>();
        for (int i=0;i<m;i++) {
            int x=scanner.nextInt(), y=scanner.nextInt();
            if (y==1) alist.add(x);
            else blist.add(x);
        }
        int[] a=alist.stream().sorted().mapToInt(i->i).toArray();
        int[] b=blist.stream().sorted().mapToInt(i->i).toArray();
        int left=a.length, right=b.length;
        if (left==0 || right==0) return Math.max(left, right)+" 0";
        boolean[][] g=new boolean[left+1][right+1];
        for (int i=0;i<left;i++) {
            for (int j=0;j<right;j++) {
                if (a[i]!=b[j]) {
                    g[i+1][j+1]=true;
                }
            }
        }
        int[] match=new int[right+1];
        for (int i=1;i<=left;i++) {
            dfs(i, left, right, match, g, new boolean[right+1]);
        }
        int cnt=0;
        boolean[] vLeft=new boolean[left+1], vRight=new boolean[right+1];
        for (int i=1;i<=right;i++) {
            if (match[i]!=0) {
                vLeft[match[i]]=true;
                vRight[i]=true;
                cnt++;
            }
        }
        int al=0, bl=0, v=0;
        for (int i=1;i<=left;i++) {
            if (!vLeft[i]) {
                al++; v=a[i-1];
            }
        }
        for (int i=1;i<=right;i++) {
            if (!vRight[i]) {
                bl++; v=b[i-1];
            }
        }
        if (al==0 || bl==0) {
            return (cnt+al+bl)+" 0";
        }
        else if (v==1) {
            return (cnt+al+bl)+" 0";
        }
        else {
            return (cnt+Math.max(al, bl))+" "+Math.min(al, bl);
        }
    }

    private boolean dfs(int u, int left, int right, int[] match, boolean[][] g, boolean[] visit) {
        for (int i=1;i<=right;i++) {
            if (g[u][i] && !visit[i]) {
                visit[i]=true;
                if (match[i]==0 || dfs(match[i], left, right, match, g, visit)) {
                    match[i]=u;
                    return true;
                }
            }
        }
        return false;
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