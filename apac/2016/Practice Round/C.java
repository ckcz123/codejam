import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), cnt=0;
        scanner.nextLine();
        String max="";
        for (int i=0;i<n;i++) {
            String s=scanner.nextLine();
            if (max.compareTo(s)<=0) max=s;
            else cnt++;
        }
        return String.valueOf(cnt);
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