import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 Qualification Round
 * Problem A. Saving the Universe
 */

public class Main {

    public String solve(Scanner scanner) {
        int n=Integer.parseInt(scanner.nextLine());
        HashSet<String> set=new HashSet<>();
        for (int i=0;i<n;i++) set.add(scanner.nextLine());
        int m=Integer.parseInt(scanner.nextLine()), cnt=0;
        HashSet<String> able=new HashSet<>(set);
        while (m-->0) {
            String s=scanner.nextLine();
            able.remove(s);
            if (able.isEmpty()) {
                cnt++;
                able=new HashSet<>(set);
                able.remove(s);
            }
        }
        return String.valueOf(cnt);
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