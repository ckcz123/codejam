import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2010 Round 1C
 * Problem A. Rope Intranet
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), ans=0;
        int[][] nums=new int[n][2];
        for (int i=0;i<n;i++) nums[i]=new int[] {scanner.nextInt(), scanner.nextInt()};
        for (int i=0;i<n;i++) {
            for (int j=i+1;j<n;j++) {
                if ((nums[i][0]-nums[j][0])*(nums[i][1]-nums[j][1])<0)
                    ans++;
            }
        }
        return String.valueOf(ans);
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