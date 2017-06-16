import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2009 Round 1C
 * Problem A. All Your Base
 */
public class Main {

    public String solve(Scanner scanner) {
        String s=scanner.next();
        HashMap<Character, Integer> map=new HashMap<>();
        int last=0;
        char[] chars=s.toCharArray();
        for (int i=0;i<chars.length;i++) {
            char c=chars[i];
            if (map.containsKey(c)) continue;
            map.put(c, last<=1?1-last:last);
            last++;
        }
        long base=Math.max(last, 2), ans=0;
        for (int i=0;i<chars.length;i++){
            ans*=base;
            ans+=map.get(chars[i]);
        }
        return String.valueOf(ans);
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