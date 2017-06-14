import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2009 Qualification Round
 * Problem A. Alien Language
 */
public class Main {

    private int solve(HashSet<String> words, String s, int l) {
        String[] lists = new String[l];

        for (int i=0,pos=0;i<l;i++) {
            if (s.charAt(pos)!='(') {
                lists[i]=s.substring(pos, pos+1);
                pos++;
                continue;
            }
            int index=s.indexOf(')', pos);
            lists[i]=s.substring(pos+1, index);
            pos=index+1;
        }

        int cnt=0;
        for (String word: words) {
            boolean is=true;
            for (int i=0;i<l;i++) {
                if (lists[i].indexOf(word.charAt(i))<0) {
                    is=false;break;
                }
            }
            if (is) cnt++;
        }
        return cnt;
    }

    public String solve(Scanner scanner) {
        int l=scanner.nextInt(), d=scanner.nextInt(), n=scanner.nextInt();
        HashSet<String> words=new HashSet<>();
        while (d-->0) words.add(scanner.next());
        for (int i=1;i<=n;i++) {
            System.out.println(String.format("Case #%d: %d", i, solve(words, scanner.next(), l)));
        }
        return "";
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        new Main().solve(scanner);
        /*int times=scanner.nextInt();
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
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));*/

    }

}