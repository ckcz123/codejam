import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2016 Qualification Round
 * Problem A. Counting Sheep
 */
public class Main {

    private String solve(Scanner scanner) {
        long n=scanner.nextLong();
        if (n==0) return "INSOMNIA";
        boolean[] chars=new boolean[10];
        int cnt=0;
        for (long v=n;;v+=n) {
            for (char c: String.valueOf(v).toCharArray()) {
                if (!chars[c-'0']) {
                    chars[c-'0']=true;cnt++;
                }
            }
            if (cnt==10) return String.valueOf(v);
        }
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