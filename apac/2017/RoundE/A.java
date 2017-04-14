import java.io.PrintStream;
import java.util.*;

public class Main {

    public String solve(Scanner scanner) {
        String s=scanner.next();
        return String.valueOf(-cal(s, scanner.nextLong()-1)+cal(s, scanner.nextLong()));
    }

    private long cal(String s, long k) {
        int l=s.length();
        long cnt=0;
        char[] chars=s.toCharArray();
        for (char c: chars)
            if (c=='B') cnt++;
        cnt*=k/l;
        k%=l;
        for (int i=1;i<=k;i++) {
            if (chars[i-1]=='B') cnt++;
        }
        return cnt;
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
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.));
    }
}
