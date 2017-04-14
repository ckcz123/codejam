import java.io.PrintStream;
import java.util.*;

public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        ArrayList<int[]> nums=new ArrayList<>();
        for (int i=0;i<n;i++) {
            nums.add(new int[] {scanner.nextInt(), scanner.nextInt()});
        }
        return able(nums)?"YES":"NO";
    }

    private boolean able(ArrayList<int[]> nums) {
        int mx1=0, mx2=0, n=nums.size();
        if (n==0) return false;
        for (int[] num: nums) {
            mx1=Math.max(mx1, num[0]);
            mx2=Math.max(mx2, num[1]);
        }
        for (int[] num: nums) {
            if (num[0]==mx1 && num[1]==mx2)
                return true;
        }
        ArrayList<int[]> ans=new ArrayList<>();
        for (int[] num: nums) {
            if (num[0]!=mx1 && num[1]!=mx2)
                ans.add(num);
        }
        return able(ans);
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
