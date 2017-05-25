import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2012 Round 1A
 * Problem B. Kingdom Rush
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        int[] one=new int[n], two=new int[n], state=new int[n];
        for (int i=0;i<n;i++) {
            one[i]=scanner.nextInt();
            two[i]=scanner.nextInt();
        }
        int cnt=0, number=0;
        while (number<2*n) {
            boolean has=false;
            for (int i=0;i<n;i++) {
                if (state[i]==1 && two[i]<=number) {
                    number++; cnt++; state[i]=2; has=true; break;
                }
            }
            if (has) continue;
            has=false;
            for (int i=0;i<n;i++) {
                if (state[i]==0 && two[i]<=number) {
                    number+=2; cnt++; state[i]=2; has=true; break;
                }
            }
            if (has) continue;

            int candidate=-1, mx=-1;
            for (int i=0;i<n;i++) {
                if (state[i]==0 && one[i]<=number && mx<two[i]) {
                    candidate=i;mx=two[i];
                }
            }
            if (candidate==-1) return "Too Bad";
            number++; cnt++; state[candidate]=1;

        }
        return String.valueOf(cnt);
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