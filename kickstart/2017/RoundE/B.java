import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * KickStart 2017 Round E
 * Problem B. Trapezoid Counting
 */
public class Main {

    public String solve(Scanner scanner) {
        return solveLarge(scanner);
    }

    public String solveLarge(Scanner scanner) {
        int n=scanner.nextInt();
        long[] nums=new long[n];
        for (int i=0;i<n;i++) nums[i]=scanner.nextLong();
        Arrays.sort(nums);

        TreeMap<Long, Integer> map=new TreeMap<>();
        TreeMap<Long, Integer> index=new TreeMap<>();
        for (int i=0;i<n;i++) {
            long x=nums[i];
            map.put(x, map.getOrDefault(x, 0)+1);
            index.put(x, i+1);
        }

        long ans=0;
        // select equals number
        for (Map.Entry<Long, Integer> entry: map.entrySet()) {
            long key=entry.getKey(), value=entry.getValue();
            if (value<=1) continue;

            // two equals, select another
            for (Map.Entry<Long, Integer> another: map.entrySet()) {
                long a=another.getKey();
                if (a==key) continue;
                // b shoule in [a+1, a+2*key-1]
                long cnt=query(index, a+2*key-1)-query(index, a);
                // if key in this range, delete it
                if (a+1<=key && key<=a*2*key-1)
                    cnt-=value;
                ans+=(value*(value-1)/2)*another.getValue()*cnt;
            }

            // three equals
            if (value<=2) continue;
            long cnt=query(index, 3*key-1)-value;
            ans+=(value*(value-1)*(value-2))/6*cnt;
        }
        return String.valueOf(ans);
    }

    // how many in [0, bound]
    private int query(TreeMap<Long, Integer> index, long bound) {
        Map.Entry<Long, Integer> upper=index.floorEntry(bound);
        return upper==null?0:upper.getValue();
    }

    public String solveSmall(Scanner scanner) {
        int n=scanner.nextInt();
        long[] nums=new long[n];
        for (int i=0;i<n;i++) nums[i]=scanner.nextLong();
        long cnt=0;
        for (int i=0;i<n;i++)
            for (int j=i+1;j<n;j++)
                for (int k=j+1;k<n;k++)
                    for (int l=k+1;l<n;l++)
                        if (able(nums[i],nums[j],nums[k],nums[l]))
                            cnt++;
        return String.valueOf(cnt);
    }

    private boolean able(long a, long b, long c, long d) {
        long[] nums=new long[] {a,b,c,d};
        Arrays.sort(nums);
        if (nums[0]==nums[1] && nums[2]==nums[3]) return false;
        return (nums[0]==nums[1] || nums[1]==nums[2] || nums[2]==nums[3])
                && nums[0]+nums[1]+nums[2]>nums[3];
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