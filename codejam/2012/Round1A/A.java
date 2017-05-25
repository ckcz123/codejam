import java.io.File;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2012 Round 1A
 * Problem A. Password Problem
 */
public class Main {

    private String solve(Scanner scanner) {
        int a=scanner.nextInt(), b=scanner.nextInt();
        double[] probs=new double[a];
        for (int i=0;i<a;i++) probs[i]=scanner.nextDouble();
        double ans=b+2, p=1;
        for (int i=1;i<=a;i++) {
            p*=probs[i-1];
            int back=a-i, click=2*back+(b-a)+1;
            ans=Math.min(ans, p*click+(1-p)*(click+b+1));
        }
        return String.format("%.9f", ans);
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        // Scanner scanner=new Scanner(new File("input.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=scanner.nextInt();
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