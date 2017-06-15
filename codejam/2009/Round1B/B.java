import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2009 Round 1B
 * Problem B. The Next Number
 */
public class Main {

    public String solve(Scanner scanner) {
        String origin=scanner.next();
        char[] chars=nextPermutation(origin.toCharArray());
        int index=0;
        while (chars[index]=='0') index++;
        char c=chars[0];chars[0]=chars[index];chars[index]=c;
        String next=new String(chars);
        return next.compareTo(origin)<=0?
                next.charAt(0)+"0"+next.substring(1)
                :next;
    }

    private char[] nextPermutation(char[] nums) {
        int len=nums.length;
        if (len<=1) return nums;
        int i=len-2;
        for (;i>=0;i--) {
            if (nums[i]<nums[i+1]) break;
        }
        if (i==-1) {
            Arrays.sort(nums);
            return nums;
        }
        char minn=nums[i+1];
        int pos=i+1;
        for (int j=len-1;j>=i+1;j--) {
            if (nums[j]>nums[i] && nums[j]<=minn) {
                minn=nums[j];
                pos=j;
            }
        }
        nums[pos]=nums[i];
        nums[i]=minn;
        Arrays.sort(nums, i+1, len);
        return nums;
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