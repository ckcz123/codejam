import java.io.PrintStream;
import java.util.*;

/**
 * KickStart 2017 Round F
 * Problem D. Eat Cake
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        if (check1(n)) return "1";
        if (check2(n)) return "2";
        if (check3(n)) return "3";
        return "4";
    }

    private boolean check1(int n) {
        for (int i=0;i<=100;i++)
            if (i*i==n) return true;
        return false;
    }

    private boolean check2(int n) {
        for (int i=0;i<=100;i++)
            for (int j=0;j<=100;j++)
                if (i*i+j*j==n)
                    return true;
        return false;
    }

    private boolean check3(int n) {
        for (int i=0;i<=100;i++)
            for (int j=0;j<=100;j++)
                for (int k=0;k<=100;k++)
                    if (i*i+j*j+k*k==n)
                        return true;
        return false;
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