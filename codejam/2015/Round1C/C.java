import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2015 Round 1C
 * Problem C. Less Money, More Problems
 */
public class Main {

    private String solve(Scanner scanner) {
        int c=scanner.nextInt(), d=scanner.nextInt(), v=scanner.nextInt();
        long[] ans=new long[d];
        for (int i=0;i<d;i++) ans[i]=scanner.nextLong();
        Arrays.sort(ans);
        long curr=1;
        int index=0, res=0;
        while (curr<=v) {
            if (index>=d || ans[index]>curr) {
                res++;
                curr*=(c+1);
            }
            else
                curr+=c*ans[index++];
        }
        return String.valueOf(res);
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