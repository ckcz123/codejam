import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2015 Round 3
 * Problem E. River Flow
 */
public class Main {

    private String solve(Scanner scanner) throws Throwable {
        int n=scanner.nextInt(), d=scanner.nextInt();
        int[] v=new int[n]; for (int i=0;i<n;i++) v[i]=scanner.nextInt();
        int ans=check(n, d, v);
        if (ans==-1) return "CHEATERS!";
        return String.valueOf(ans);
    }

    private int check(int n, int d, int[] v) {
        for (int i=2*d;i<n;i++) if (v[i]!=v[i-2*d]) return -1;
        int ans=0;
        for (int k=d;k>=1;k/=2) {
            int[] diff=new int[2*d];
            for (int i=0;i<2*d;i++) {
                diff[i]=v[(i+1)%(2*d)]-v[i];
            }
            int[] on=new int[2*k+1];
            for (int i=0;i<k;i++) {
                int a=diff[i+k]-diff[i];
                if (a%2!=0) return -1;
                a/=2;
                ans+=Math.abs(a);
                on[i+1]=-a;
                on[i+k+1]=a;
                if (a>=0) on[0]+=a;
            }
            int s=0;
            for (int i=0;i<2*k;i++) {
                s+=on[i];
                v[i]-=s;
                if (v[i]<0) return -1;
            }
        }
        return ans;
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