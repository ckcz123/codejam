import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2014 Qualification Round
 * Problem B. Cookie Clicker Alpha
 */
public class Main {

    private String solve(Scanner scanner) {
        double c=scanner.nextDouble(), f=scanner.nextDouble(), x=scanner.nextDouble();
        double speed=2.0, t=x/speed, curt=.0;
        while (true) {
            curt+=c/speed;
            speed+=f;
            if (t>curt+x/speed)
                t=curt+x/speed;
            else break;
        }
        return String.format("%.9f", t);
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