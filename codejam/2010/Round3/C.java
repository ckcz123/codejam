import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2010 Round 3
 * Problem C. Hot Dog Proliferation
 */
public class Main {

    public String solve(Scanner scanner) {
        // return solveSmall(scanner);
        return solveLarge(scanner);
    }

    private String solveLarge(Scanner scanner) {
        TreeMap<Long, Long> map=new TreeMap<>();
        int c=scanner.nextInt();
        long ans=0;
        while (c-->0) {
            long p=scanner.nextLong(), v=scanner.nextLong();

            while (v-->0) {

                // in some interval?
                Long key=map.floorKey(p);
                if (key==null || map.get(key)<p) {
                    // merge
                    merge(map, p, p);
                    continue;
                }
                // yes, in one interval!
                long start=key, end=map.get(start);

                // split to two intervals.
                // calculate the position
                long mid=start+end-p;
                ans+=(mid-start+1)*(end-mid+1);
                map.remove(start);
                merge(map, start-1, mid-1);
                merge(map, mid+1, end+1);
            }
        }
        return String.valueOf(ans);
    }

    private void merge(TreeMap<Long, Long> map, long start, long end) {

        // it is guaranteed that [start, end] has no intersection with map

        // merge with interval before
        Long before=map.lowerKey(start);
        if (before!=null && map.get(before)==start-1) {
            // merge
            start=before;
            map.put(before, end);
        }
        // merge with interval after
        Long after=map.higherKey(start);
        if (after!=null && after==end+1) {
            // merge
            map.put(start, map.get(after));
            map.remove(after);
        }
        // not merge
        if (!map.containsKey(start))
            map.put(start, end);

    }

    /*private String solveSmall(Scanner scanner) {
        int n=scanner.nextInt();
        HashMap<Integer, Integer> map=new HashMap<>();
        for (int i=0;i<n;i++) map.put(scanner.nextInt(), scanner.nextInt());
        int ans=0;
        while (true) {
            boolean has=false;
            for (int v: map.keySet()) {
                if (map.getOrDefault(v, 0)>=2) {
                    map.put(v, map.get(v)-2);
                    map.put(v-1, map.getOrDefault(v-1,0)+1);
                    map.put(v+1, map.getOrDefault(v+1,0)+1);
                    ans++;
                    has=true;break;
                }
            }
            if (!has) break;
        }
        return String.valueOf(ans);
    }*/

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