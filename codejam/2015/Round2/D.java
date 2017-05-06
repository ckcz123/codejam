import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2015 Round 2
 * Problem D. Drum Decorator
 */
public class Main {

    public String solve(Scanner scanner) {
        int r=scanner.nextInt(), c=scanner.nextInt();
        int mod=1000000007;
        long[][][] dp=new long[r+1][13][2];
        dp[0][1][0]=dp[0][1][1]=1;
        for (int x=0;x<r;x++) {
            for (int y=1;y<=12;y++) {
                for (int is3=0;is3<2;is3++) {
                    long v=dp[x][y][is3];
                    if (v==0) continue;
                    if (is3==1) {
                        if (x+2<=r) {
                            dp[x+2][y][0]+=v;
                            dp[x+2][y][0]%=mod;
                        }
                        continue;
                    }
                    if (x+1<=r) {
                        dp[x+1][y][1]+=v;
                        dp[x+1][y][1]%=mod;
                    }
                    if (x+2<=r && c%3==0) {
                        dp[x+2][lcm(y, 3)][1]+=3*v;
                        dp[x+2][lcm(y, 3)][1]%=mod;
                    }
                    if (x+2<=r && c%6==0) {
                        dp[x+2][lcm(y, 6)][1]+=6*v;
                        dp[x+2][lcm(y, 3)][1]%=mod;
                    }
                    if (x+3<=r && c%4==0) {
                        dp[x+3][lcm(y, 4)][1]+=4*v;
                        dp[x+3][lcm(y, 4)][1]%=mod;
                    }
                }
            }
        }
        long res=0;
        for (int l=1;l<=12;l++) {
            for (int is3=0;is3<2;is3++) {
                long v=dp[r][l][is3];
                while (v%l!=0) v+=mod;
                v/=l;
                res+=v;
                res%=mod;
            }
        }
        return String.valueOf(res);
    }

    private int lcm(int a, int b)  {
        for (int i=1;;i++) if (i%a==0 && i%b==0) return i;
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
