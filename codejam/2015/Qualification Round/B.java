import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2015 Qualification Round
 * Problem B. Infinite House of Pancakes
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        int[] num=new int[n];
        for (int i=0;i<n;i++) num[i]=scanner.nextInt();
        int ans=Integer.MAX_VALUE;
        for (int x=1;x<=1000;x++) {
            int cnt=0;
            for (int p: num) {
                cnt+=(p-1)/x;
            }
            ans=Math.min(ans, cnt+x);
        }
        return String.valueOf(ans);
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
