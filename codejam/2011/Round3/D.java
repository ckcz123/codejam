import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2011 Round 3
 * Problem D. Mystery Square
 * Only small solved.
 */
public class Main {

    public String solve(Scanner scanner) {
        char[] chars = scanner.next().toCharArray();
        dfs(chars, 0);
        return new String(chars);
    }

    private boolean dfs(char[] chars, int curr) {
        if (curr==chars.length)
            return able(new String(chars));
        if (chars[curr]!='?') return dfs(chars, curr+1);
        for (char c: "01".toCharArray()) {
            chars[curr]=c;
            if (dfs(chars, curr+1)) return true;
        }
        return false;
    }

    private boolean able(String s) {
        long v=Long.parseLong(s, 2), x=(long)Math.sqrt(v);
        return x*x==v;
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