import java.io.PrintStream;
import java.util.*;

/**
 * Codejam to I/O for Women 2016 Problem B: Dance Around The Clock
 * Check README.md for explanation.
 */
public class Main {

    private String solve(Scanner scanner) {
        int d=scanner.nextInt(), k=scanner.nextInt(), n=scanner.nextInt(), x, y;
        if (k%2==0) {
            x=k+1-2*n;
            y=k-1-2*n;
            while (x<=0) x+=d;
            while (y<=0) y+=d;
        }
        else {
            x=k+1+2*n;
            y=k-1+2*n;
            while (x>d) x-=d;
            while (y>d) y-=d;
        }
        return x+" "+y;
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
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}