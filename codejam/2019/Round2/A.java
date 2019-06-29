import java.util.*;

/**
 * Codejam 2019 Round 2
 * Problem A. New Elements: Part 1
 */
public class Solution {

    class Pair {
        long p, q;
        int index;
        public Pair(long _p, long _q, int i) {p=_p; q=_q; index=i;}
    }

    ArrayList<Pair> molecules = new ArrayList<>();
    ArrayList<Pair> pqs = new ArrayList<>();

    public String solve(Scanner scanner) {
        int n = scanner.nextInt();
        for (int i = 0; i < n; ++i) {
            molecules.add(new Pair(scanner.nextLong(), scanner.nextLong(), i));
        }
        // calculate pqs
        for (int i = 0; i < n; ++i) {
            Pair p = molecules.get(i);
            for (int j = i+1; j < n; ++j) {
                Pair q = molecules.get(j);
                long delta_c = p.p - q.p;
                long delta_j = q.q - p.q;
                if (delta_c * delta_j <= 0) continue;
                pqs.add(new Pair(Math.abs(delta_j), Math.abs(delta_c), 0));
            }
        }
        // sort pqs
        pqs.add(new Pair(0,1,0));
        pqs.sort((p,q)->{
            // p.p / p.q < q.p / q.q
            // p.p * q.q < q.p * p.q
            return Long.compare(p.p * q.q, q.p * p.q);
        });
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < pqs.size(); ++i) {
            Pair pair = pqs.get(i);
            long u = pair.p, v = pair.q;
            if (i != pqs.size() - 1) {
                Pair next = pqs.get(i+1);
                if (u * next.q == v * next.p) continue;
                u += next.p; v += next.q;
            }
            else u++;
            String s = test(u, v);
            if (s != null) set.add(s);
        }
        return String.valueOf(set.size());
    }

    private String test(final long u, final long v) {
        molecules.sort(Comparator.comparingLong(p -> p.p * u + p.q * v));
        ArrayList<String> list = new ArrayList<>();
        list.add(String.valueOf(molecules.get(0).index));
        for (int i = 1; i < molecules.size(); ++i) {
            Pair p = molecules.get(i - 1), q = molecules.get(i);
            if (p.p * u + p.q * v >= q.p * u + q.q * v) return null;
            list.add(String.valueOf(q.index));
        }
        return String.join(",", list);
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner=new Scanner(System.in);
        int times=Integer.parseInt(scanner.nextLine());
        long start=System.currentTimeMillis();
        for (int t=1;t<=times;t++) {
            try {
                System.out.println(String.format("Case #%d: %s", t, new Solution().solve(scanner)));
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