import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2010 Qualification Round
 * Problem C. Theme Park
 */
public class Main {

    public String solve(Scanner scanner) {
        int r=scanner.nextInt(), k=scanner.nextInt(), n=scanner.nextInt();
        int[] a=new int[n];
        for (int i=0;i<n;i++) a[i]=scanner.nextInt();
        long[][] sum=new long[n][n];
        for (int j=0;j<n;j++)
            for (int i=0;i<n;i++)
                sum[i][j]=a[i]+(j==0?0:sum[(i+1)%n][j-1]);
        long ans=0;
        int index=0;
        while (r-->0) {
            int next=Arrays.binarySearch(sum[index], k);
            if (next<0) next=-next-2;
            ans+=sum[index][next];
            index=(index+next+1)%n;
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=scanner.nextInt();
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