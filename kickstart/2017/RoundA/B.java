import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        String s=scanner.next(), t=scanner.next();
        int l1=s.length(), l2=t.length();
        boolean[][] dp=new boolean[l1+1][l2+1];
        dp[0][0]=true;
        for (int i=1;i<=l1;i++) {
            if (s.charAt(i-1)=='*') dp[i][0]=true;
            else break;
        }
        for (int j=1;j<=l2;j++) {
            if (t.charAt(j-1)=='*') dp[0][j]=true;
            else break;
        }
        for (int i=1;i<=l1;i++) {
            for (int j=1;j<=l2;j++) {
                if (s.charAt(i-1)=='*') {
                    int cnt=0;
                    for (int k=j;k>=0;k--) {
                        if (dp[i-1][k]) {
                            dp[i][j]=true;
                            break;
                        }
                        if (k>0 && t.charAt(k-1)!='*') cnt++;
                        if (cnt>4) break;
                    }
                }
                if (t.charAt(j-1)=='*') {
                    int cnt=0;
                    for (int k=i;k>=0;k--) {
                        if (dp[k][j-1]) {
                            dp[i][j]=true;
                            break;
                        }
                        if (k>0 && s.charAt(k-1)!='*') cnt++;
                        if (cnt>4) break;
                    }
                }
                if (s.charAt(i-1)==t.charAt(j-1) && dp[i-1][j-1])
                    dp[i][j]=true;
            }
        }
        return dp[l1][l2]?"TRUE":"FALSE";
    }


    public static void main(String[] args) throws Exception {
        //System.setOut(new PrintStream("output.txt"));
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