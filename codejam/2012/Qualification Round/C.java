import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2012 Qualification Round
 * Problem C. Recycled Numbers
 */
public class Main {

    private String solve(Scanner scanner) {
        int a=scanner.nextInt(), b=scanner.nextInt(), cnt=0;
        for (int x=a;x<=b;x++) {
            String s=Integer.toString(x);
            HashSet<Integer> set=new HashSet<>();
            for (int i=0;i<s.length();i++) {
                String s1=s.substring(i)+s.substring(0, i);
                int y=Integer.parseInt(s1);
                if (y>x && y<=b) set.add(y);
            }
            cnt+=set.size();
        }
        return String.valueOf(cnt);
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        long times=Integer.parseInt(scanner.nextLine());
        long start=System.currentTimeMillis();
        for (long t=1;t<=times;t++) {
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



