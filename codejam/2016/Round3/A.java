import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2016 Round 3
 * Problem A. Teaching Assistant
 */
public class Main {

    private String solve(Scanner scanner) {
        StringBuilder builder=new StringBuilder(scanner.next());
        int cnt=0;
        while (true) {
            boolean has=false;
            for (int i=0;i<builder.length()-1;i++) {
                if (builder.charAt(i)==builder.charAt(i+1)) {
                    builder.replace(i, i+2, "");
                    cnt+=10;
                    has=true;
                    break;
                }
            }
            if (!has) break;
        }
        return String.valueOf(cnt+builder.length()*5/2);
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