import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), k=scanner.nextInt();
        int[] s=new int[n-k+1];
        for (int i=0;i<=n-k;i++) s[i]=scanner.nextInt();
        int[] a=new int[n];
        for (int i=0;i<n-k;i++) {
            a[i+k]=a[i]+s[i+1]-s[i];
        }
        int sum=s[0];
        PriorityQueue<Integer> priorityQueue=new PriorityQueue<>();
        for (int i=0;i<k;i++) {
            int max=0, min=0;
            for (int j=i;j<n;j+=k) {
                max=Math.max(max, a[j]);
                min=Math.min(min, a[j]);
            }
            sum+=min;
            priorityQueue.offer(max-min);
        }
        while (sum<0) sum+=k;
        sum%=k;
        while (sum-->0)
            priorityQueue.offer(priorityQueue.poll()+1);
        int ans=0;
        for (int e: priorityQueue)
            ans=Math.max(ans, e);
        return String.valueOf(ans);
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