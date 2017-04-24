import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * APAC 2016 Round C Problem D: gMatrix
 * Check README.md for explanation.
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt(), k=scanner.nextInt();
        long[][] map=new long[n][n];
        long c=scanner.nextLong(), mod=scanner.nextLong();
        long[] a=new long[n], b=new long[n];
        for (int i=0;i<n;i++) a[i]=scanner.nextLong();
        for (int i=0;i<n;i++) b[i]=scanner.nextLong();
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                map[i][j]=(a[i]*(i+1)+b[j]*(j+1)+c)%mod;
            }
        }

        long[][] mx1=new long[n][n-k+1];
        for (int i=0;i<n;i++) {
            mx1[i]=max(n, map[i], k);
        }
        long[][] mx2=new long[n-k+1][n-k+1];
        for (int i=0;i<=n-k;i++) {
            long[] v=new long[n];
            for (int j=0;j<n;j++) v[j]=mx1[j][i];
            mx2[i]=max(n, v, k);
        }
        long ans=0;
        for (int i=0;i<=n-k;i++) {
            for (int j=0;j<=n-k;j++)
                ans+=mx2[i][j];
        }
        return String.valueOf(ans);
    }

    private long[] max(int n, long[] nums, int k) {
        long[] r=new long[n-k+1];
        LinkedList<Integer> dequeue=new LinkedList<>();
        int ri=0;
        for (int i=0;i<n;i++) {
            while (!dequeue.isEmpty() && dequeue.peek()<i-k+1)
                dequeue.poll();
            while (!dequeue.isEmpty() && nums[dequeue.peekLast()]<nums[i])
                dequeue.pollLast();
            dequeue.offer(i);
            if (i>=k-1)
                r[ri++]=nums[dequeue.peek()];
        }
        return r;
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
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.));
    }
}