import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2009 Qualification Round
 * Problem C. Welcome to Code Jam
 */
public class Main {

    public String solve(Scanner scanner) {
        String s=scanner.nextLine(), text="welcome to code jam";
        int l1=s.length(), l2=text.length();
        long[][] dp=new long[l1+1][l2+1];
        for (int i=0;i<=l1;i++) dp[i][0]=1;
        for (int i=1;i<=l1;i++) {
            for (int j=1;j<=l2;j++) {
                dp[i][j]=(dp[i-1][j]+(s.charAt(i-1)==text.charAt(j-1)?dp[i-1][j-1]:0))%1000000;
            }
        }
        String ans=String.valueOf(dp[l1][l2]+1000000);
        return ans.substring(ans.length()-4);
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