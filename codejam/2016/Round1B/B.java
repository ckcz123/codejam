import java.io.PrintStream;
import java.util.*;

public class Main {

    long delta=Long.MAX_VALUE;
    String ss="", tt="";

    private String solve(Scanner scanner) {
        char[] s=scanner.next().toCharArray(), t=scanner.next().toCharArray();
        dfs(0, s, t, 0);
        return ss+" "+tt;
    }

    private int dfs(int curr, char[] s, char[] t, int smaller) {
        if (curr==s.length) {
            String s1=new String(s), t1=new String(t);
            long sval=Long.parseLong(s1), tval=Long.parseLong(t1);
            if (Math.abs(sval-tval)<delta || (Math.abs(sval-tval)==delta &&
                    (s1.compareTo(ss)<0 || (s1.compareTo(ss)==0 && t1.compareTo(tt)<0)))) {
                delta=Math.abs(sval-tval);
                ss=s1;
                tt=t1;
            }
            return 0;
        }
        if (s[curr]!='?' && t[curr]!='?') {
            return dfs(curr+1, s, t, smaller==0?Character.compare(s[curr], t[curr]):smaller);
        }
        if (s[curr]=='?' && t[curr]=='?') {
            if (smaller!=0)
                return check(curr, s, t, smaller, smaller<0?'9':'0', smaller<0?'0':'9');
            check(curr, s, t, 0, '0', '0');
            check(curr, s, t, -1, '0','1');
            check(curr, s, t, 1, '1','0');
            return 0;
        }
        if (s[curr]=='?') {
            if (smaller!=0)
                return check(curr, s, t, smaller, smaller<0?'9':'0', t[curr]);
            if (t[curr]!='0')
                check(curr, s, t, -1, (char)(t[curr]-1), t[curr]);
            check(curr, s, t, 0, t[curr], t[curr]);
            if (t[curr]!='9')
                check(curr, s, t, 1, (char)(t[curr]+1), t[curr]);
            return 0;
        }
        if (smaller!=0)
            return check(curr, s, t, smaller, s[curr], smaller<0?'0':'9');
        if (s[curr]!='0')
            check(curr, s, t, 1, s[curr], (char)(s[curr]-1));
        check(curr, s, t, 0, s[curr], s[curr]);
        if (s[curr]!='9')
            check(curr, s, t, -1, s[curr], (char)(s[curr]+1));
        return 0;
    }

    private int check(int curr, char[] s, char[] t, int smaller, char c1, char c2) {
        char tmp1=s[curr], tmp2=t[curr];
        s[curr]=c1;
        t[curr]=c2;
        dfs(curr+1, s, t, smaller);
        s[curr]=tmp1;
        t[curr]=tmp2;
        return 0;
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