import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 Qualification Round
 * Problem C. Fly Swatter
 */
public class Main {

    public String solve(Scanner scanner) {
        double f=scanner.nextDouble(), r=scanner.nextDouble(), t=scanner.nextDouble(),
                d=scanner.nextDouble(), g=scanner.nextDouble();
        if (2*f>=g || r-t-f<=0) return "1.0";

        double ans=.0;
        for (int i=0;i<=1500;i++) {
            for (int j=0;j<=1500;j++) {
                double x1=(2*d+g)*i+d+f, x2=x1+g-2*f,
                        y1=(2*d+g)*j+d+f, y2=y1+g-2*f;
                ans+=cal(x1, x2, y1, y2, r-t-f);
            }
        }
        return String.format("%.9f", 1-4*ans/Math.PI/r/r);
    }

    private double cal(double x1, double x2, double y1, double y2, double r) {
        if (x1*x1+y1*y1>=r*r) return .0;
        if (x1*x1+y2*y2>=r*r)
            return cal(x1, Math.min(x2, Math.sqrt(r*r-y1*y1)), y1, r);
        if (x2*x2+y2*y2<=r*r)
            return (y2-y1)*(x2-x1);
        double x=Math.sqrt(r*r-y2*y2);
        return (x-x1)*(y2-y1)+cal(x, Math.min(x2, Math.sqrt(r*r-y1*y1)), y1, r);
    }

    private double cal(double x1, double x2, double y, double r) {
        double y1=Math.sqrt(r*r-x1*x1), y2=Math.sqrt(r*r-x2*x2);
        double o1=Math.acos(x1/r), o2=Math.acos(x2/r), o=o1-o2;
        return x2*y2/2+o*r*r/2-x1*y1/2-y*(x2-x1);
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