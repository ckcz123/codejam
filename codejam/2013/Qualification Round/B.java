import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2013 Qualification Round
 * Problem B. Lawnmower
 */
public class Main {

    private String solve(Scanner scanner) throws Throwable {
        int r=scanner.nextInt(), c=scanner.nextInt();
        int[][] a=new int[r][c];
        for (int i=0;i<r;i++) for (int j=0;j<c;j++) a[i][j]=scanner.nextInt();
        for (int i=0;i<r;i++) {
            for (int j=0;j<c;j++) {
                boolean able=true;
                for (int w=0;w<r;w++) {
                    if (a[w][j]>a[i][j]) {
                        able=false;
                    }
                }
                if (able) continue;
                able=true;
                for (int w=0;w<c;w++) {
                    if (a[i][w]>a[i][j]) {
                        able=false;
                    }
                }
                if (able) continue;
                return "NO";
            }
        }
        return "YES";
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