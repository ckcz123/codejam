import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

/**
 * KickStart 2017 Round E
 * Problem C. Blackhole
 */
public class Main {

    public String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    public String solveSmall(Scanner scanner) {
        double[][] points=new double[3][3];
        for (int i=0;i<3;i++) for (int j=0;j<3;j++) points[i][j]=scanner.nextDouble();
        double max=-1e10, min=1e10;
        for (int i=0;i<3;i++) {
            max=Math.max(max, points[i][0]);
            min=Math.min(min, points[i][0]);
        }
        return String.format("%.9f", (max-min)/6);
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