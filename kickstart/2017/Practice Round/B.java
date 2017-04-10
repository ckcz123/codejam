import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        double n=scanner.nextDouble(), m=scanner.nextDouble();
        return String.format("%.9f", (n-m)/(n+m));
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        long start=System.currentTimeMillis();
        Scanner scanner=new Scanner(System.in);
        int times=scanner.nextInt();
        for (int t=1;t<=times;t++) {
            System.out.println(String.format("Case #%d: %s", t, new Main().solve(scanner)));
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}