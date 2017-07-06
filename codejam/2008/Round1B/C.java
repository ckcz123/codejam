import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 Round 1B
 * Problem C. Mousetrap
 */
public class Main {

    public String solve(Scanner scanner) {
        int k=scanner.nextInt(), n=scanner.nextInt();
        int[] queries=new int[n];
        String[] ans=new String[n];
        for (int i=0;i<n;i++) queries[i]=scanner.nextInt();
        for (int i=1,pos=0;i<=k;i++) {
            pos=(pos+i-1)%(k-i+1);
            for (int j=0;j<n;j++) {
                if (queries[j]==pos+1) {
                    queries[j]=-1;
                    ans[j]=String.valueOf(i);
                }
                if (queries[j]>pos+1) {
                    queries[j]--;
                }
            }
        }
        return String.join(" ", ans);
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