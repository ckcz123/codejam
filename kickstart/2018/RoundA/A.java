import java.io.PrintStream;
import java.util.*;

/**
 * KickStart 2018 Round A
 * Problem A. Even Digits
 */
public class Main {

    public String solve(Scanner scanner) {
        String s=scanner.next();
        char[] chars=s.toCharArray();
        long v=Long.MAX_VALUE;
        char[] tmp=cal(chars, true);
        if (tmp!=null) v=Math.min(v, Math.abs(Long.parseLong(new String(tmp))-Long.parseLong(s)));
        tmp=cal(chars, false);
        if (tmp!=null) v=Math.min(v, Math.abs(Long.parseLong(new String(tmp))-Long.parseLong(s)));
        return String.valueOf(v);
    }

    private char[] cal(char[] chars, boolean smaller) {
        char[] ans=new char[chars.length];
        int small=0;
        for (int i=0;i<chars.length;i++) {
            if (small>0) ans[i]='0';
            else if (small<0) ans[i]='8';
            else {
                if ((chars[i]-'0')%2==0) ans[i]=chars[i];
                else {
                    if (smaller) {
                        ans[i]=(char)(chars[i]-1);
                        small=-1;
                    }
                    else {
                        if (chars[i]=='9') return null;
                        ans[i]=(char)(chars[i]+1);
                        small=1;
                    }
                }
            }
        }
        return ans;
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
