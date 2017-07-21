import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 APAC Semifinal
 * Problem C. Millionaire
 */
public class Main {

    class SegmentTree {
        int start, end;
        double p;
        boolean pure;
        SegmentTree left, right;
        public SegmentTree(int _start, int _end) {
            start=_start; end=_end;
            pure=true;
            p=0.;

            if (start!=end) {
                int mid=(start+end)/2;
                left=new SegmentTree(start, mid);
                right=new SegmentTree(mid+1, end);
            }
        }

        public void update(int l, int r, double val) {
            if (l==start && r==end && pure) {
                p=Math.max(p, val);
                return;
            }
            pure=false;
            int mid=(start+end)/2;
            if (l<=mid)
                left.update(l, Math.min(mid, r), val);
            if (r>=mid+1)
                right.update(Math.max(l, mid+1), r, val);
        }

        public double query(int x) {
            if (pure) return p;
            int mid=(start+end)/2;
            return x<=mid?left.query(x):right.query(x);
        }

        public ArrayList<Interval> toIntervals() {
            ArrayList<Interval> list=new ArrayList<>();
            if (pure)
                list.add(new Interval(start, end, p));
            else {
                list.addAll(left.toIntervals());
                list.addAll(right.toIntervals());
            }
            return list;
        }


    }

    class Interval {
        int start, end;
        double p;
        public Interval(int _start, int _end, double _p) {
            start=_start; end=_end; p=_p;
        }
    }

    public String solve(Scanner scanner) {
        int m=scanner.nextInt();
        double p=scanner.nextDouble();
        int x=scanner.nextInt();

        SegmentTree tree=new SegmentTree(0, 1000000);
        tree.update(1000000, 1000000, 1.);

        while (m-->0)
            tree=update(tree, p);

        return String.format("%.9f", tree.query(x));

    }

    private SegmentTree update(SegmentTree tree, double p) {

        ArrayList<Interval> list=tree.toIntervals();
        list.sort(Comparator.comparingInt(x->x.start));

        // merge list
        ArrayList<Interval> merge=new ArrayList<>();
        int ls=-10, ld=-10;
        double lp=.0;
        for (Interval interval: list) {
            if (interval.start==ld+1 && Math.abs(interval.p-lp)<1e-10) {
                ld=interval.end;
            }
            else {
                if (ls>=0)
                    merge.add(new Interval(ls, ld, lp));
                ls=interval.start;
                ld=interval.end;
                lp=interval.p;
            }
        }
        if (ls>=0)
            merge.add(new Interval(ls, ld, lp));

        list=merge;

        SegmentTree next=new SegmentTree(0, 1000000);
        int len=list.size();
        for (int i=0;i<len;i++) {
            for (int j=i;j<len;j++) {
                Interval x=list.get(i), y=list.get(j);
                int start=(x.start+y.start+1)/2, end=(x.end+y.end)/2;
                if (start>end) continue;
                next.update(start, end, x.p*(1-p)+y.p*p);
            }
        }
        return next;
    }

    public static void main(String[] args) throws Exception {
//        System.setOut(new PrintStream("output.txt"));
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