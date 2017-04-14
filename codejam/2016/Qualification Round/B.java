import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        String s=scanner.next();
        return String.valueOf(solve(s.toCharArray()));
    }

    private int solve(char[] chars) {
        if (chars[0]=='+') {
            for (int i=0;i<chars.length;i++) {
                if (chars[i]=='-') return 1+solve(chars);
                else chars[i]='-';
            }
            return 0;
        }
        for (int i=0;i<chars.length;i++) {
            if (chars[i]=='+') return 1+solve(chars);
            else chars[i]='+';
        }
        return 1+solve(chars);
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