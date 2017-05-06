import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2016 Round 3
 * Problem D. Go++
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), l=scanner.nextInt();
        HashSet<String> set=new HashSet<>();
        for (int i=0;i<n;i++) set.add(scanner.next());
        String s=scanner.next();
        if (set.contains(s)) return "IMPOSSIBLE";
        if (l==1) {
            if ("0".equals(s)) return "1 1?";
            return "0 0?";
        }
        String x=s.replace('0','A').replace('1','B')
                .replace("A","1?").replace("B","0?");
        String y=s.substring(0, l-1).replace('0','A').replace('1','B')
                .replace("A","10").replace("B","01");
        return x+" "+y;
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