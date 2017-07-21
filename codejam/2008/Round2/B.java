import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public String solve(Scanner scanner) {
        int m = scanner.nextInt(), n = scanner.nextInt(), a = scanner.nextInt();
        if (a>m*n) return "IMPOSSIBLE";
        if (a%m==0)
            return String.format("0 0 %d %d %d %d", m, 0, 0, a/m);
        int ceil=a/m*m+m, delta=ceil-a;
        return String.format("0 0 %d %d %d %d", m, 1, delta, ceil/m);
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