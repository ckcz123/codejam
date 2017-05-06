import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2016 Round 1A
 * Problem B. Rank and File
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        HashSet<Integer> set=new HashSet<>();
        for (int i=1;i<2*n;i++) {
            for (int j=1;j<=n;j++) {
                int x=scanner.nextInt();
                if (!set.add(x)) set.remove(x);
            }
        }
        return String.join(" ", set.stream().sorted().map(String::valueOf).collect(Collectors.toList()));
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