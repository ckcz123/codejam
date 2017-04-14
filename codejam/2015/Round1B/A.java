import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        return String.valueOf(solveLarge(scanner.nextLong()));
    }

    /*private int[] solveSmall() {
        int[] dp=new int[1000000];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1]=1;
        LinkedList<Integer> linkedList=new LinkedList<>();
        linkedList.offer(1);
        while (!linkedList.isEmpty()) {
            int x=linkedList.poll(), cnt=dp[x];
            if (x+1<1000000 && dp[x+1]>cnt+1) {
                dp[x+1]=cnt+1;
                linkedList.offer(x+1);
            }
            int y=Integer.parseInt(new StringBuilder().append(x).reverse().toString());
            if (dp[y]>cnt+1) {
                dp[y]=cnt+1;
                linkedList.offer(y);
            }
        }
        return dp;
    }*/

    private long solveLarge(long n) {
        if (n<=9) return n;
        if (n%10==0) return 1+solveLarge(n-1);
        long mod=1;
        while (mod*10<=n) mod*=10;

        String s=String.valueOf(n);
        int len=s.length();
        boolean reverse=false;
        long cnt=0, base=1;
        for (int i=0;i<len/2;i++) {
            cnt+=(s.charAt(i)-'0'+s.charAt(len-1-i)-'0')*base;
            base*=10;
            if (s.charAt(i)>'0'+(i==0?1:0)) reverse=true;
        }
        if (len%2!=0) {
            cnt+=(s.charAt(len/2)-'0')*base;
        }
        if (!reverse) cnt--;
        return cnt+solveLarge(mod);
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("E:\\desktop\\output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=scanner.nextInt();
        for (int t=1;t<=times;t++) {
            System.out.println(String.format("Case #%d: %s", t, new Main().solve(scanner)));
        }

    }

}