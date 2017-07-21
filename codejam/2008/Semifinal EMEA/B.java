import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 EMEA Semifinal
 * Problem B. Painting a Fence
 */
public class Main {

    class Interval {
        int start, end;
        String color;
        public Interval(String _color, int _start, int _end) {
            color=_color; start=_start; end=_end;
        }
    }

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        Interval[] intervals=new Interval[n];
        for (int i=0;i<n;i++)
            intervals[i]=new Interval(scanner.next(), scanner.nextInt(), scanner.nextInt());
        int ans=dfs(intervals, 1, new HashSet<>());
        return ans>=Integer.MAX_VALUE/10?"IMPOSSIBLE":String.valueOf(ans);
    }

    private int dfs(Interval[] intervals, int index, HashSet<String> used) {
        if (used.size()>=4) return Integer.MAX_VALUE/10;
        if (index>10000) return 0;
        int min=Integer.MAX_VALUE/10;
        for (Interval interval: intervals) {
            if (index>=interval.start && index<=interval.end) {
                HashSet<String> next=new HashSet<>(used);
                next.add(interval.color);
                min=Math.min(min, 1+dfs(intervals, interval.end+1, next));
            }
        }
        return min;
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
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