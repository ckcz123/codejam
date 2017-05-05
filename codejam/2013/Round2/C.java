import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private String solve(Scanner scanner) throws Throwable {
        int n=scanner.nextInt();
        int[] a=new int[n], b=new int[n];
        for (int i=0;i<n;i++)
            a[i]=scanner.nextInt();
        for (int i=0;i<n;i++)
            b[i]=scanner.nextInt();
        boolean[][] more=new boolean[n][n];
        for (int i=0;i<n;i++) {
            int index=-1;
            for (int j=0;j<i;j++) {
                if (a[j]>=a[i]) more[j][i]=true;
                if (a[j]==a[i]-1) index=j;
            }
            if (index!=-1) more[i][index]=true;
        }
        for (int i=n-1;i>=0;i--) {
            int index=-1;
            for (int j=n-1;j>i;j--) {
                if (b[j]>=b[i]) more[j][i]=true;
                if (b[j]==b[i]-1) index=j;
            }
            if (index!=-1) more[i][index]=true;
        }
        int[] cnt=new int[n];
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (more[i][j]) cnt[i]++;
            }
        }
        String[] ans=new String[n];
        boolean[] used=new boolean[n];
        for (int i=1;i<=n;i++) {
            for (int j=0;j<n;j++) {
                if (!used[j] && cnt[j]==0) {
                    used[j]=true;
                    ans[j]=String.valueOf(i);
                    for (int k=0;k<n;k++) {
                        if (more[k][j]) cnt[k]--;
                    }
                    break;
                }
            }
        }
        return String.join(" ", ans);
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
            }
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}