import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2012 Round 1B
 * Problem A. Safety in Numbers
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        int[] p=new int[n];
        for (int i=0;i<n;i++) p[i]=scanner.nextInt();
        int total=Arrays.stream(p).sum(), sum=2*total;
        double[] need=new double[n];
        Arrays.fill(need, -1);

        int left=n;
        while (true) {
            boolean has=false;
            for (int i=0;i<n;i++) {
                if (need[i]<0 && left*p[i]>=sum) {
                    need[i]=0;
                    left--;
                    has=true;
                    sum-=p[i];
                    break;
                }
            }
            if (!has) break;
        }

        double avg=sum/(left+.0);
        for (int i=0;i<n;i++) {
            if (need[i]>=0) continue;
            need[i]=(avg-p[i])/total;
        }

        return String.join(" ", Arrays.stream(need).boxed()
                .map(d->String.format("%.9f", 100*d)).collect(Collectors.toList()));
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