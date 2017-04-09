import java.io.PrintStream;
import java.util.*;

public class Main {

    class Inteval implements Comparable<Inteval> {
        long start, end;
        public Inteval(long s, long e) {start=s;end=e;}
        public int compareTo(Inteval o) {
            return start==o.start?
                    Long.compare(o.end, end):Long.compare(start, o.start);
        }
    }

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        long x=scanner.nextLong(), y=scanner.nextLong(), a=scanner.nextLong(),
                b=scanner.nextLong(), c1=scanner.nextLong(), c2=scanner.nextLong(),
                m=scanner.nextLong();
        Inteval[] intevals=new Inteval[n];
        intevals[0]=new Inteval(x, y);
        for (int i=1;i<n;i++) {
            long x1=(a*x+b*y+c1)%m;
            long y1=(a*y+b*x+c2)%m;
            intevals[i]=new Inteval(Math.min(x1, y1), Math.max(x1, y1));
            x=x1;y=y1;
        }
        Arrays.sort(intevals);
        long total=calTotal(intevals);

        TreeMap<Long, Inteval> treeMap=new TreeMap<>();
        calTreeMap(intevals, treeMap);
        long ans=0;
        for (Inteval inteval: intevals) {
            long l=0;
            Map<Long, Inteval> subTree=treeMap.subMap(inteval.start, true,
                    inteval.end, true);
            for (Inteval v: subTree.values())
                l+=v.end-v.start+1;
            ans=Math.max(ans, l);
        }
        return String.valueOf(total-ans);
    }

    private long calTotal(Inteval[] intevals) {
        long start=0, end=-1, ans=0;
        for (Inteval inteval: intevals) {
            if (inteval.start>end) {
                ans+=end-start+1;
                start=inteval.start;
                end=inteval.end;
            }
            else end=Math.max(end, inteval.end);
        }
        return ans+end-start+1;
    }

    private void calTreeMap(Inteval[] intevals, TreeMap<Long, Inteval> treeMap) {
        long start=0, end=-1;
        for (Inteval inteval: intevals) {
            if (inteval.start>end) {
                attemptAdd(treeMap, start, end);
                start=inteval.start;
                end=inteval.end;
            }
            else if (inteval.start>start) {
                attemptAdd(treeMap, start, inteval.start-1);
                start=Math.min(end, inteval.end)+1;
                end=Math.max(end, inteval.end);
            } else if (inteval.end>=start) {
                start=Math.min(end, inteval.end)+1;
                end=Math.max(end, inteval.end);
            }
        }
        attemptAdd(treeMap, start, end);
    }
    private void attemptAdd(TreeMap<Long, Inteval> treeMap, long start, long end) {
        if (end<start) return;
        treeMap.put(start, new Inteval(start, end));
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