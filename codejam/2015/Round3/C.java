import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2015 Round 3
 * Problem C. Runaway Quail
 * Only small solved.
 */
public class Main {

    class Quail {
        double pos;
        int s;
        public Quail(double _p, int _s) {pos=_p;s=_s;}
    }

    private String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int s=scanner.nextInt(), n=scanner.nextInt();
        double[] p=new double[n];
        for (int i=0;i<n;i++) p[i]=scanner.nextDouble();
        ArrayList<Quail> arrayList=new ArrayList<>();
        for (int i=0;i<n;i++) arrayList.add(new Quail(p[i], scanner.nextInt()));
        return String.format("%.9f", dfs(0, s, arrayList));
    }

    private double dfs(double x, int s, ArrayList<Quail> arrayList) {
        int n=arrayList.size();
        if (n==0) return 0;
        double[] t=new double[n];
        for (int i=0;i<n;i++) {
            Quail quail=arrayList.get(i);
            t[i]=Math.abs(quail.pos-x)/(s-quail.s);
        }

        double res=1e10;
        double tleft=choose(x, s, arrayList, n, t, true),
                tright=choose(x, s, arrayList, n, t, false);
        if (res>tleft) res=tleft;
        if (res>tright) res=tright;
        return res;
    }

    private double choose(double x, int s, ArrayList<Quail> arrayList, int n, double[] t,
                          boolean left) {
        double tval=1e10, pos=-1;
        for (int i=0;i<n;i++) {
            Quail quail=arrayList.get(i);
            if ((left && quail.pos>=x) || (!left && quail.pos<x)) continue;
            if (tval>t[i]) {
                tval=t[i]; pos=i;
            }
        }
        if (pos==-1) return 1e10;
        ArrayList<Quail> list=new ArrayList<>();
        for (int i=0;i<n;i++) {
            if (i==pos) continue;
            Quail quail=arrayList.get(i);
            list.add(new Quail(
                    quail.pos>=x?quail.pos+tval*quail.s:quail.pos-tval*quail.s, quail.s));
        }
        return tval+dfs(left?x-tval*s:x+tval*s, s, list);
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