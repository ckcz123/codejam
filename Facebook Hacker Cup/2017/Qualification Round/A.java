import java.io.*;
import java.util.*;

/**
 * Facebook Hacker Cup 2017 Qualification Round
 * Problem A. Progress Pie
 */
public class Main {

    public static String solve(Scanner scanner) {
        int p=scanner.nextInt(), x=scanner.nextInt(), y=scanner.nextInt();
        if ((x-50)*(x-50)+(y-50)*(y-50)>=50*50) return "white";
        if (p==0) return "white";
        double angle=Math.atan2(x-50., y-50.);
        if (angle<0) angle+=2*Math.PI;
        return angle/2/Math.PI<p/100.?"black":"white";
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
