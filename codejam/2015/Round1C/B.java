import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2015 Round 1C
 * Problem B. Typewriter Monkey
 */
public class Main {

    private String solve(Scanner scanner) {
        int k=scanner.nextInt(), l=scanner.nextInt(), s=scanner.nextInt();
        String board=scanner.next(), word=scanner.next();

        int[] cnt=new int[26];
        for (char c: board.toCharArray()) {
            cnt[c-'A']++;
        }
        for (char c: word.toCharArray()) {
            if (cnt[c-'A']==0) return "0.0";
        }
        return String.format("%.9f",calTotal(word, l, s)-calAvg(cnt, k, word, l, s));
    }

    private double calTotal(String word, int l, int s) {
        StringBuilder builder=new StringBuilder(s);
        int cnt=0;
        for (int start=0,end=0;start<=s-l;start++) {
            if (builder.substring(start, end).equals(word.substring(0, end-start))) {
                cnt++;
                builder.replace(start, start+l, word);
                end=start+l;
            }
        }
        return cnt;
    }

    private double calAvg(int[] cnt, int k, String word, int l, int s) {
        double p=1.0;
        for (char c: word.toCharArray()) {
            p*=cnt[c-'A'];
            p/=k;
        }
        return p*(s-l+1);
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("E:\\desktop\\output.txt"));
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