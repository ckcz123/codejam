import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2013 Round 2
 * Problem B. Many Prizes
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt(); long p=scanner.nextLong();
        return String.valueOf(must(n, p)-1)+" "+String.valueOf(may(n, p));
    }

    private long must(int n, long p) {
        if (p==(1L<<n)) return (1L<<n);
        if (p<=(1L<<(n-1))) return 1;
        return 2*must(n-1, p-(1L<<(n-1)))+1;
    }

    private long may(int n, long p) {
        return (1L<<n)-(1L<<(n-Long.toBinaryString(p).length()+1));
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
//        Scanner scanner=new Scanner(new File("input.txt"));
        int times=scanner.nextInt();
        long start=System.currentTimeMillis();
        for (int t=1;t<=times;t++) {
            try {
                System.out.println(String.format("Case #%d: %s", t, new Main().solve(scanner)));
            }
            catch (Exception e) {System.err.println("ERROR #"+t);e.printStackTrace();}
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.));
    }

}