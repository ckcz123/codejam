import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public String solve(Scanner scanner) throws Exception {
        int n=scanner.nextInt(), x=Math.abs(scanner.nextInt()), y=scanner.nextInt();
        int sum=0, level=0;
        while (sum+(2*level+1)<=n) {
            sum+=2*level+1;
            level+=2;
        }
        int remain=n-sum;
        if (x+y<level) return "1.0";
        if (x+y>level || x==0) return "0.0";
        double[][] dp=new double[level+1][level+1];
        for (int i=0;i<=level;i++) {
            for (int j=0;j<=level;j++) {
                if (i==0 && j==0) dp[i][j]=1.;
                else if (i==0) dp[i][j]=.5*dp[i][j-1];
                else if (j==0) dp[i][j]=.5*dp[i-1][j];
                else dp[i][j]=(i==level?1:.5)*dp[i][j-1]+(j==level?1:.5)*dp[i-1][j];
            }
        }
        double p=.0;
        for (int i=Math.max(remain-level, level-x+1);i<=Math.min(remain, level);i++) {
            p+=dp[remain-i][i];
        }
        return String.format("%.9f", p);
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
            catch (Exception e) {System.err.println("ERROR #"+t);e.printStackTrace();}
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.));
    }

}