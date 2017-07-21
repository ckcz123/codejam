import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 EMEA Semifinal
 * Problem A. Scaled Triangle
 */
public class Main {

    public String solve(Scanner scanner) {

        /*
          (A1, B1, C1) -> (A2, B2, C2)
          A1(x1, y1), A1B1: (a1, b1), A1C1: (c1, d1)
          A2(x2, y2), A2B2: (a2, b2), A2C2: (c2, d2)

          Required: P(x, y)
          Then, there exists p & q:
          P = A1+p*A1B1+q*A1C1 = A2+p*A2B2+q*A2C2

          => x1+pa1+qc1 = x2+pa2+qc2 = x
             y1+pb1+qd1 = y2+pb2+qd2 = y

          => (a1-a2)*p+(c1-c2)*q=x2-x1
             (b1-b2)*p+(d1-d2)*q=y2-y1

          => u1p+v1q=r1
             u2p+v2q=r2

          => p=(r1*v2-r2*v1)/(u1*v2-u2*v1), q=(u1*r2-u2*r1)/(u1*v2-u2*v1)

          => x=x1+pa1+qc1, y=y1+pb1+qd1
         */

        double x1=scanner.nextDouble(), y1=scanner.nextDouble();
        double a1=scanner.nextDouble()-x1, b1=scanner.nextDouble()-y1,
                c1=scanner.nextDouble()-x1, d1=scanner.nextDouble()-y1;
        double x2=scanner.nextDouble(), y2=scanner.nextDouble();
        double a2=scanner.nextDouble()-x2, b2=scanner.nextDouble()-y2,
                c2=scanner.nextDouble()-x2, d2=scanner.nextDouble()-y2;

        double u1=a1-a2, v1=c1-c2, u2=b1-b2, v2=d1-d2, r1=x2-x1, r2=y2-y1;
        double p=(r1*v2-r2*v1)/(u1*v2-u2*v1), q=(u1*r2-u2*r1)/(u1*v2-u2*v1);

        double x=x1+p*a1+q*c1, y=y1+p*b1+q*d1;
        return String.format("%.9f %.9f", x, y);
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