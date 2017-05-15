import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2012 Qualification Round
 * Problem A. Speaking in Tongues
 */
public class Main {

    private String solve(Scanner scanner) {
        char[] chars=init();
        String s=scanner.nextLine();
        char[] to=new char[s.length()];
        for (int i=0;i<s.length();i++)
            to[i]=chars[s.charAt(i)];
        return new String(to);
    }

    private char[] init() {
        char[] chars=new char[128];
        String[][] map=new String[][] {
                {"ejp mysljylc kd kxveddknmc re jsicpdrysi",
                        "our language is impossible to understand"},
                {"rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd",
                        "there are twenty six factorial possibilities"},
                {"de kr kd eoya kw aej tysr re ujdr lkgc jv",
                        "so it is okay if you want to just give up"}
        };
        chars['y']='a';chars['e']='o';chars['q']='z';
        for (String[] s: map) {
            int l=s[0].length();
            for (int i=0;i<l;i++)
                chars[s[0].charAt(i)]=s[1].charAt(i);
        }
        // 'z' is not mapped..
        boolean[] used=new boolean[128];
        for (int i=0;i<128;i++) {
            used[chars[i]]=true;
        }
        for (int i='a';i<='z';i++) {
            if (!used[i])
                chars['z']=(char)i;
        }
        return chars;
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



