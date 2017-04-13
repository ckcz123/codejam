import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        String s=scanner.next();
        int l=s.length();
        long cnt=1, mod=1000000007;
        for (int i=0;i<l;i++) {
            HashSet<Character> set=new HashSet<>();
            if (i>0) set.add(s.charAt(i-1));
            set.add(s.charAt(i));
            if (i<l-1) set.add(s.charAt(i+1));
            cnt*=set.size();
            cnt%=mod;
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