import java.io.PrintStream;
import java.util.*;

/**
 * APAC 2017 Practice Round Problem D: Sums of Sums
 * Check README.md for explanation.
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), query=scanner.nextInt();
        int[] nums=new int[n], s=new int[n+1];
        for (int i=0;i<n;i++) {
            nums[i]=scanner.nextInt();
            s[i+1]=nums[i]+s[i];
        }

        ArrayList<String> arrayList=new ArrayList<>();
        while (query--!=0) {
            long l=scanner.nextLong(), r=scanner.nextLong();
            arrayList.add(String.valueOf(topKth(n, nums, s, r)-topKth(n, nums, s, l-1)));
        }
        return "\n"+String.join("\n", arrayList);
    }

    private long topKth(int n, int[] nums, int[] s, long k) {
        long val=getKth(n, nums, s[n], k), sum=0, totalsum=0, ans=0;
        int start=0, end=0;
        while (start<n) {
            while (end<n && sum+nums[end]<=val) {
                sum+=nums[end++];
                totalsum+=s[end]-s[start];
            }
            ans+=totalsum;
            if (end>start) {
                sum-=nums[start];
                totalsum-=(end-start)*nums[start];
                start++;
            }
            else end=++start;
        }
        return ans-(calculate(n, nums, val)-k)*val;
    }

    private long getKth(int n, int[] nums, long max, long k) {
        if (k==0) return 0;
        long start=1, end=max;
        while (start<end) {
            long mid=(start+end)/2;
            if (calculate(n, nums, mid)>=k) end=mid;
            else start=mid+1;
        }
        return start;
    }

    private long calculate(int n, int[] nums, long candidate) {
        int start=0, end=0;
        long ans=0, sum=0;
        while (start<n) {
            while (end<n && sum+nums[end]<=candidate)
                sum+=nums[end++];
            ans+=end-start;
            if (end>start) sum-=nums[start++];
            else end=++start;
        }
        return ans;
    }

/*
    private String solveSmall(Scanner scanner) {
        int n=scanner.nextInt(), query=scanner.nextInt();
        int[] nums=new int[n], s=new int[n+1];
        for (int i=0;i<n;i++) {
            nums[i]=scanner.nextInt();
            s[i+1]=nums[i]+s[i];
        }
        int[] sums=new int[n*(n+1)/2];
        int k=0;
        for (int i=0;i<n;i++) {
            for (int j=i+1;j<=n;j++) {
                sums[k++]=s[j]-s[i];
            }
        }
        Arrays.sort(sums);
        long[] ss=new long[n*(n+1)/2+1];
        for (int i=1;i<=n*(n+1)/2;i++) {
            ss[i]=ss[i-1]+sums[i-1];
        }
        ArrayList<String> arrayList=new ArrayList<>();
        while (query--!=0) {
            int l=scanner.nextInt(), r=scanner.nextInt();
            arrayList.add(String.valueOf(ss[r]-ss[l-1]));
        }
        return "\n"+String.join("\n", arrayList);
    }
*/

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