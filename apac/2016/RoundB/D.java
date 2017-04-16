import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private String solve(Scanner scanner) {
        String s=scanner.next();
        int len=s.length();
        long mod=1000000007;
        long[][][][] dp=new long[len+1][len/2+3][len/2+3][2];
        dp[0][0][0][0]=1;
        for (int i=1;i<=len;i++) { // 长度
            for (int j=0;j<=len/2;j++) { // a数量
                for (int k=0;k<=len/2;k++) { // b数量
                    for (int l=0;l<2;l++) { // 是否开始处理c或d
                        if (s.charAt(i-1)=='a') {
                            if (k==0) {
                                dp[i][j+1][k][0]+=dp[i-1][j][k][l];
                                dp[i][j+1][k][0]%=mod;
                            }
                        }
                        if (s.charAt(i-1)=='b') {
                            if (j!=0 && l==0) {
                                dp[i][j][k+1][0]+=dp[i-1][j][k][l];
                                dp[i][j][k+1][0]%=mod;
                            }
                        }
                        if (s.charAt(i-1)=='c') {
                            if (j!=0 && k!=0) {
                                dp[i][j-1][k][1]+=dp[i-1][j][k][l];
                                dp[i][j-1][k][1]%=mod;
                            }
                        }
                        if (s.charAt(i-1)=='d') {
                            if (j==0 && k!=0 && l!=0) {
                                dp[i][j][k-1][1]+=dp[i-1][j][k][l];
                                dp[i][j][k-1][1]%=mod;
                            }
                        }
                        dp[i][j][k][l]+=dp[i-1][j][k][l];
                        dp[i][j][k][l]%=mod;
                    }
                }
            }
        }
        return String.valueOf(dp[len][0][0][1]);
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
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}