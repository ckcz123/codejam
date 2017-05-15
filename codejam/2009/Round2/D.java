import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2009 Round 2
 * Problem D. Watering Plants
 * Only small solved.
 */
public class Main {

    private String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int n=scanner.nextInt();
        int[][] plants=new int[n][3];
        for (int i=0;i<n;i++)
            plants[i]=new int[] {scanner.nextInt(), scanner.nextInt(), scanner.nextInt()};
        if (n==1 || n==2) {
            int max=0;
            for (int[] p: plants) max=Math.max(max, p[2]);
            return String.valueOf(max);
        }

        // n==3
        return String.format("%.9f",
                Math.min(getR(plants[0],plants[1],plants[2]),
                        Math.min(getR(plants[1],plants[0],plants[2]),
                                getR(plants[2],plants[0],plants[1]))));
    }

    private double getR(int[] x, int[] y, int[] z) {
        return Math.max(x[2]+.0,
                (Math.sqrt((y[0]-z[0])*(y[0]-z[0])+(y[1]-z[1])*(y[1]-z[1]))
                        +y[2]+z[2])/2.);
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        long times=scanner.nextInt();
        long start=System.currentTimeMillis();
        for (long t=1;t<=times;t++) {
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



