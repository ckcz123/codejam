import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2014 Qualification Round
 * Problem D. Deceitful War
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        double[] a=new double[n], b=new double[n];
        for (int i=0;i<n;i++) a[i]=scanner.nextDouble();
        for (int i=0;i<n;i++) b[i]=scanner.nextDouble();
        return String.format("%d %d", after(a, b), before(a, b));
    }

    private int after(double[] a, double[] b) {
        TreeSet<Double> t1=new TreeSet<>(), t2=new TreeSet<>();
        for (double x: a) t1.add(x);
        for (double x: b) t2.add(x);

        int val=0;
        for (double x: t1) {
            if (t2.first()<x || t2.last()<=x) {
                t2.pollFirst();
                val++;
            }
            else t2.pollLast();
        }
        return val;
    }

    private int before(double[] a, double[] b) {
        TreeSet<Double> t1=new TreeSet<>(), t2=new TreeSet<>();
        for (double x: a) t1.add(x);
        for (double x: b) t2.add(x);

        int val=0;
        for (double x: t1) {
            Double v=t2.ceiling(x);
            if (v==null) {
                val++;
                t2.pollFirst();
            }
            else t2.remove(v);
        }
        return val;

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