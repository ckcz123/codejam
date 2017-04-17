import java.io.PrintStream;
import java.util.*;

public class Main {

    public String solve(Scanner scanner) {
        int b=scanner.nextInt(), l=scanner.nextInt(), n=scanner.nextInt();
        double[] curr=new double[2];
        curr[1]=750.*b;
        for (int i=1;i<l;i++) {
            int size=i*(i+1)/2, nsize=(i+1)*(i+2)/2;
            double[] nxt=new double[nsize+1];
            int row=1;
            for (int j=1;j<=size;j++) {
                if (j>row*(row+1)/2) row++;
                if (curr[j]>250.) {
                    double overflow=curr[j]-250;
                    nxt[j]+=overflow/3;
                    nxt[j+row]+=overflow/3;
                    nxt[j+row+1]+=overflow/3;
                }
            }
            curr=nxt;
        }
        return String.format("%.9f", Math.min(250., curr[n]));
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
