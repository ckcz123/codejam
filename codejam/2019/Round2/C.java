import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2019 Round 1C
 * Problem C. New Elements: Part 2
 * Only small solved.
 */
public class Solution {

    public String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    public String solveSmall(Scanner scanner) {
        int n = scanner.nextInt();
        int[][] molecules = new int[n][2];
        for (int i = 0; i < n; ++i){
            molecules[i][0] = scanner.nextInt();
            molecules[i][1] = scanner.nextInt();
        }
        for (int i = 1; i < 300; ++i) {
            for (int j = 1; j < 300; ++j) {
                if (testPQ(n, molecules, i, j))
                    return i + " " + j;
            }
        }
        return "IMPOSSIBLE";
    }

    private boolean testPQ(int n, int[][] molecules, int p, int q) {
        for (int i = 0; i < n - 1; ++i) {
            if (molecules[i][0] * p + molecules[i][1] * q >= molecules[i+1][0] * p + molecules[i+1][1] * q)
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner=new Scanner(System.in);
        int times=Integer.parseInt(scanner.nextLine());
        long start=System.currentTimeMillis();
        for (int t=1;t<=times;t++) {
            try {
                System.out.println(String.format("Case #%d: %s", t, new Solution().solve(scanner)));
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