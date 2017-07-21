import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 Round 2
 * Problem C. Star Wars
 */
public class Main {

    public String solve(Scanner scanner) throws Exception {
        int n=scanner.nextInt();
        int[][] data=new int[n][4];
        for (int i=0;i<n;i++)
            for (int j=0;j<4;j++)
                data[i][j]=scanner.nextInt();
        double ans=.0;
        for (int i=0;i<n;i++) {
            for (int j=i+1;j<n;j++) {
                int sum=0;
                for (int k=0;k<3;k++)
                    sum+=Math.abs(data[i][k]-data[j][k]);
                ans=Math.max(ans, sum/(data[i][3]+data[j][3]+.0));
            }
        }
        return String.format("%.9f", ans);
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