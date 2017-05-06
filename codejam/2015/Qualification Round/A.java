import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2015 Qualification Round
 * Problem A. Standing Ovation
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), x=0, cnt=0;
        String s=scanner.next();
        for (char c: s.toCharArray()) {
            x+=c-'1';
            if (x<0) {
                cnt++;
                x=0;
            }
        }
        return String.valueOf(cnt);
    }


    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("E:\\desktop\\output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=scanner.nextInt();
        for (int t=1;t<=times;t++) {
            System.out.println(String.format("Case #%d: %s", t, new Main().solve(scanner)));
        }

    }

}