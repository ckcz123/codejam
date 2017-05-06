import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2016 Round 1A
 * Problem A. The Last Word
 */
public class Main {

    private String solve(Scanner scanner) {
        char[] chars=scanner.next().toCharArray();
        StringBuilder builder=new StringBuilder();
        for (char c: chars) {
            if (builder.length()>0 && c>=builder.charAt(0))
                builder.insert(0, c);
            else builder.append(c);
        }
        return builder.toString();
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