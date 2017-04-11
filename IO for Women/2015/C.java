import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        if (n<=4) return "...";
        int start=1, end=8999;
        while (start<end) {
            int mid=(start+end)/2;
            double v=0;
            int x=9000;
            while (x>1) {
                v+=Math.log10(x);
                x-=mid;
            }
            if ((int)Math.ceil(v)<n)
                end=mid;
            else start=mid+1;
        }
        char[] chars=new char[start];
        Arrays.fill(chars, '!');
        return "IT'S OVER 9000"+new String(chars);
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