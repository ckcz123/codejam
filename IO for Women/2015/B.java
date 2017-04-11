import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        long k=scanner.nextLong(), v=scanner.nextLong();
        return String.valueOf((3*v*v+3*v+1)*(k-v)+(v+1)*(v+1)*(v+1));
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