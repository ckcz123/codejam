import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private String solve(Scanner scanner) throws Throwable {
        long b=scanner.nextLong();
        int n=scanner.nextInt();
        long[] origin=new long[37];
        for (int i=0;i<n;i++) origin[i]=scanner.nextLong();
        Arrays.sort(origin);
        double ans=.0;
        for (int i=0;i<=36;i++) {
            long height=binarySearch(origin, i, b);
            if (height==-1) continue;
            long[] curr=new long[37];
            for (int j=0;j<=i;j++) curr[j]=height;
            for (int j=i+1;j<=36;j++) curr[j]=Math.max(origin[j], height+1);
            ans=Math.max(ans, profit(origin, curr));
        }
        return String.format("%.9f", ans);
    }

    private long binarySearch(long[] origin, int index, long b) {
        long start=origin[index], end=origin[36]+b;
        if (cal(origin, index, start)>b) return -1;
        while (start<end) {
            long mid=(start+end+1)/2;
            if (cal(origin, index, mid)>b) end=mid-1;
            else start=mid;
        }
        return start;
    }

    private long cal(long[] origin, int index, long height) {
        long use=0;
        for (int i=0;i<=index;i++) use+=height-origin[i];
        for (int i=index+1;i<=36;i++) use+=Math.max(height+1-origin[i], 0);
        return use;
    }

    private double profit(long[] origin, long[] curr) {
        long use=0, profit=0;
        for (int i=0;i<=36;i++) use+=curr[i]-origin[i];
        int cnt=0;
        for (int i=0;i<=36;i++) {
            if (curr[0]==curr[i]) {
                cnt++;
                profit+=(curr[i]-origin[i])*36;
            }
        }
        return profit/(cnt+.0)-use;
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