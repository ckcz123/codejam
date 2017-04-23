import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int n=scanner.nextInt();
        boolean[] enter=new boolean[n];
        int[] id=new int[n];
        HashSet<Integer> all=new HashSet<>();
        for (int i=0;i<n;i++) {
            enter[i]=scanner.next().charAt(0)=='E';
            id[i]=scanner.nextInt()-1;
            if (id[i]>=0) all.add(id[i]);
        }
        ArrayList<Integer> list=new ArrayList<>(all);
        for (int i=0;i<n;i++) {
            if (id[i]>=0) id[i]=list.indexOf(id[i]);
        }

        boolean[][] dp=new boolean[n+1][1<<n];
        for (int i=0;i<(1<<n);i++) dp[0][i]=true;
        for (int i=0;i<n;i++) {
            for (int j=0;j<(1<<n);j++) {
                if (dp[i][j]) {
                    if (id[i]==-1) {
                        for (int w=0;w<n;w++) {
                            // enter & not contains
                            if (enter[i] && (j&(1<<w))==0) {
                                dp[i+1][j^(1<<w)]=true;
                            }
                            // leave & contains
                            if (!enter[i] && (j&(1<<w))!=0) {
                                dp[i+1][j^(1<<w)]=true;
                            }
                        }
                    }
                    else {
                        int w=id[i];
                        // enter & not contains
                        if (enter[i] && (j&(1<<w))==0) {
                            dp[i+1][j^(1<<w)]=true;
                        }
                        // leave & contains
                        if (!enter[i] && (j&(1<<w))!=0) {
                            dp[i+1][j^(1<<w)]=true;
                        }
                    }
                }
            }
        }
        int min=Integer.MAX_VALUE;
        for (int i=0;i<(1<<n);i++) {
            if (dp[n][i]) {
                int cnt=0;
                for (int j=0;j<n;j++) {
                    if ((i&(1<<j))!=0) cnt++;
                }
                min=Math.min(min, cnt);
            }
        }
        return min==Integer.MAX_VALUE?"CRIME TIME":String.valueOf(min);
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