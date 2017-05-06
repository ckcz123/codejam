import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2017 Qualification Round
 * Problem B: Tidy Numbers
 */
public class Main {

    private String solve(Scanner scanner) {
        return String.valueOf(solveLarge(scanner.next()));
    }

    private long solveLarge(String n) {
        StringBuilder builder=new StringBuilder(n);
        while (true) {
            boolean has=false;
            for (int i=0; i<builder.length()-1; i++) {
                if (builder.charAt(i)>builder.charAt(i+1)) {
                    has=true;
                    builder.setCharAt(i, (char)(builder.charAt(i)-1));
                    for (int j=i+1;j<builder.length();j++)
                        builder.setCharAt(j, '9');
                    break;
                }
            }
            if (!has) break;
        }
        return Long.parseLong(builder.toString());
    }

    /*private int solveSmall(int n) {
        for (int i=n;i>=1;i--)
            if (isTidy(i))
                return i;
        return 1;
    }

    private boolean isTidy(int n) {
        String s=String.valueOf(n);
        for (int i=0;i<s.length()-1;i++)
            if (s.charAt(i)>s.charAt(i+1))
                return false;
        return true;
    }*/

    public static void main(String[] args) throws Exception {
        //System.setOut(new PrintStream("output.txt"));
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