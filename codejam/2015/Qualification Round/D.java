import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2015 Qualification Round
 * Problem D. Ominous Omino
 */
public class Main {

    private String solve(Scanner scanner) {
        return solve(scanner.nextInt(), scanner.nextInt(), scanner.nextInt())?"GABRIEL":"RICHARD";
    }

    private boolean solve(int x, int r, int c) {
        if (r*c%x!=0 || x>=7) return false;
        if (r<c) {int t=r;r=c;c=t;}
        if (x<=2) return true;
        if (x==3) return c>=2;
        if (x==4) return c>=3;
        if (x==5) return c>=3 && !(c==3 && r==5);
        return c>=4;
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