import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2015 Round 2
 * Problem B. Kiddie Pool
 */
public class Main {

    private class Source {
        double r,c;
        public Source(double _r, double _c) {r=_r;c=_c;}
    }

    private static final double EPOS=1e-16;

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        double v=scanner.nextDouble(), x=scanner.nextDouble();
        LinkedList<Source> sources=new LinkedList<>();
        for (int i=0;i<n;i++)
            sources.add(new Source(scanner.nextDouble(), scanner.nextDouble()-x));
        sources.sort(Comparator.comparingDouble(o->o.c));
        double start=0, end=1e17;
        for (int i=0;i<2000;i++) {
            double mid=(start+end)/2, min=cal(sources.iterator(), v, mid),
                    max=cal(sources.descendingIterator(), v, mid);
            if (max>=-EPOS && min<=EPOS) end=mid;
            else start=mid;
        }
        return start>1e16?"IMPOSSIBLE":String.format("%.9f", start);
    }

    private double cal(Iterator<Source> iterator, double v, double t) {
        double ans=0;
        while (iterator.hasNext()) {
            Source source=iterator.next();
            double vi=Math.min(v, source.r*t);
            ans+=source.c*vi;
            v-=vi;
        }
        return v>=EPOS?-1:ans;
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("E:\\desktop\\output.txt"));
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