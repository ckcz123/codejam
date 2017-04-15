import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    class Interval {
        int start, end;
        public Interval(int s, int e) {start=s;end=e;}
    }

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), p=scanner.nextInt();
        int[] nums=new int[n];
        ArrayList<Interval> list=new ArrayList<>();
        int min=Integer.MAX_VALUE, max=0, ll=1;
        for (int i=0;i<n;i++) {
            nums[i]=scanner.nextInt();
            min=Math.min(min, nums[i]);
            max=Math.max(max, nums[i]);
            if (min==ll && max==i+1) {
                list.add(new Interval(ll, i+1));
                min=Integer.MAX_VALUE;
                max=0;
                ll=i+2;
            }
        }
        int best=0;
        for (Interval interval: list) {
            int l=interval.start, r=interval.end, cur=l-1, top=r, res=0;
            while (cur<interval.end) {
                int nxt=findn(cur+1, top, nums);
                top-=(nxt-cur);
                if (l==cur+1) {
                    cur=nxt; continue;
                }
                res=Math.max(res, 1);
                if (nxt==r) break;
                res=Math.max(res, 2+findp(cur+1, nxt, top+1, nums));
                cur=nxt;
            }
            best=Math.max(best, res);
        }
        return String.valueOf(best+list.size());
    }

    private int findn(int cur, int top, int[] nums) {
        int min=top, cnt=0;
        while (true) {
            cnt++;
            min=Math.min(min, nums[cur-1]);
            if (min==top-cnt+1) return cur;
            cur++;
        }
    }

    private int findp(int l, int r, int bas, int[] nums) {
        int max=bas, cnt=0;
        while (true) {
            max=Math.max(max, nums[l-1]);
            cnt++;
            if (max==cnt+bas-1) {
                if (l==r) return 0;
                return findp(l+1, r, bas+cnt, nums)+1;
            }
            l++;
        }
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