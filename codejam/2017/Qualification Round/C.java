import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        long n=scanner.nextLong(), k=scanner.nextLong();
        /*
        String small=solveSmall(n, k), large=solveLarge(n,k);
        if (!small.equals(large)) {
            System.err.println(String.format("ERROR: n=%d, k=%d, small=%s, large=%s",
                    n,k,small,large));
        }
        */
        return solveLarge(n, k);
    }

    private String solveLarge(long n, long k) {
        TreeMap<Long, Long> map=new TreeMap<>();
        map.put(n, 1L);
        while (map.lastEntry().getValue()<k) {
            Map.Entry<Long, Long> lastEntry=map.pollLastEntry();
            long u=lastEntry.getKey(), a=u/2, b=u-1-a, v=lastEntry.getValue();
            map.put(a, map.getOrDefault(a, 0L)+v);
            map.put(b, map.getOrDefault(b, 0L)+v);
            k-=v;
        }
        long u=map.lastKey(), a=u/2, b=u-1-a;
        return Math.max(a,b)+" "+Math.min(a,b);
    }

    private String solveSmall(long n, long k) {
        PriorityQueue<Long> priorityQueue=new PriorityQueue<>((o1,o2)->Long.compare(o2,o1));
        priorityQueue.offer(n);
        for (long i=1;i<=k;i++) {
            long u=priorityQueue.poll(), a=u/2, b=u-1-a;
            if (i==k) return Math.max(a, b)+" "+Math.min(a, b);
            priorityQueue.offer(a);
            priorityQueue.offer(b);
        }
        return "";
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