import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * APAC 2016 Round A Problem B: gCube
 * Check README.md for explanation.
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), m=scanner.nextInt();
        double[] a=new double[n];
        for (int i=0;i<n;i++)
            a[i]=scanner.nextDouble();

        ArrayList<String> arrayList=new ArrayList<>();
        while (m-->0) {
            int l=scanner.nextInt(), r=scanner.nextInt(), d=r-l+1;
            double ans=1.;
            for (int i=l;i<=r;i++)
                ans*=Math.pow(a[i], 1./d);
            arrayList.add(String.format("%.9f", ans));
        }
        return "\n"+String.join("\n", arrayList);
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