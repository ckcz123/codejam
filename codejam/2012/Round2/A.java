import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2012 Round 2
 * Problem A. Swinging Wild
 */
public class Main {

    class Range {
        TreeMap<Integer, Integer> treeMap;
        ArrayList<int[]> list;
        public Range() {
            treeMap=new TreeMap<>();
            list=new ArrayList<>();
        }
        public void addRange(int s, int e) {
            if (s>e) return;
            list.add(new int[] {s, e});
        }
        public void merge() {
            list.sort(Comparator.comparingInt(o->o[0]));
            treeMap.clear();
            int s=Integer.MIN_VALUE,e=Integer.MIN_VALUE;
            for (int[] val: list) {
                if (val[0]>e) {
                    if (e!=Integer.MIN_VALUE) treeMap.put(s, e);
                    s=val[0];e=val[1];
                }
                else e=Math.max(e, val[1]);
            }
            if (e!=Integer.MIN_VALUE) treeMap.put(s, e);
        }
        public boolean inRange(int val) {
            Map.Entry<Integer, Integer> entry=treeMap.floorEntry(val);
            return entry!=null && entry.getValue()>=val;
        }
    }

    private String solve(Scanner scanner) throws Throwable {
        int n=scanner.nextInt();
        int[][] vines=new int[n][2];
        for (int i=0;i<n;i++)
            vines[i]=new int[] {scanner.nextInt(), scanner.nextInt()};
        int d=scanner.nextInt();
        Range[] ranges=new Range[n];
        for (int i=n-1;i>=0;i--) {
            ranges[i]=new Range();
            int di=vines[i][0], li=vines[i][1];
            for (int j=i+1;j<n;j++) {
                int dj=vines[j][0], lj=vines[j][1], delta=dj-di;
                if (ranges[j].inRange(Math.max(di, dj-lj))) {
                    ranges[i].addRange(di-li, di-delta);
                }
            }
            ranges[i].addRange(di-li, di-(d-di));
            ranges[i].merge();
        }
        return ranges[0].inRange(0)?"YES":"NO";
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
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