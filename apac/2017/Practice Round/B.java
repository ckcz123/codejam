import java.io.PrintStream;
import java.util.*;

/**
 * APAC 2017 Practice Round Problem B: Robot Rock Band
 * Check README.md for explanation.
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), k=scanner.nextInt();
        int[][] num=new int[4][n];
        for (int i=0;i<4;i++) {
            for (int j=0;j<n;j++)
                num[i][j]=scanner.nextInt();
        }
        HashMap<Integer, Integer> map=new HashMap<>();
        for (int x: num[0]) {
            for (int y: num[1]) {
                int v=x^y;
                map.put(v, map.getOrDefault(v, 0)+1);
            }
        }
        long val=0;
        for (int x: num[2]) {
            for (int y: num[3]) {
                val+=map.getOrDefault(x^y^k, 0);
            }
        }
        return String.valueOf(val);
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