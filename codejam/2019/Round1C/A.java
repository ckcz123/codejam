import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2019 Round 1C
 * Problem A. Robot Programming Strategy
 */
public class Solution {
    public String solve(Scanner scanner) {
        int n = scanner.nextInt();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < n; ++i) list.add(scanner.next());
        StringBuilder builder = new StringBuilder();
        int index = 0;
        while (builder.length()<500) {
            char c = next(list, index++);
            if (c == 0) return "IMPOSSIBLE";
            builder.append(c);
            if (list.isEmpty()) return builder.toString();
        }
        return "IMPOSSIBLE";
    }

    private char next(ArrayList<String> list, int index) {
        boolean hasR = false, hasS = false, hasP = false;
        for (String s: list) {
            char curr = s.charAt(index%s.length());
            if (curr == 'R') hasR = true;
            if (curr == 'S') hasS = true;
            if (curr == 'P') hasP = true;
        }
        char next=0;
        if (hasR && hasS && hasP) return 0;
        else if (hasR && hasS) next = 'R';
        else if (hasS && hasP) next = 'S';
        else if (hasP && hasR) next = 'P';
        else if (hasR) next = 'P';
        else if (hasS) next = 'R';
        else if (hasP) next = 'S';
        final char n = next;
        list.removeIf(s->{
            char curr = s.charAt(index%s.length());
            if (n == 'R' && curr == 'S') return true;
            if (n == 'S' && curr == 'P') return true;
            if (n == 'P' && curr == 'R') return true;
            return false;
        });
        return next;
    }

    public static void main(String[] args) throws Exception {
        // System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=Integer.parseInt(scanner.nextLine());
        long start=System.currentTimeMillis();
        for (int t=1;t<=times;t++) {
            try {
                System.out.println(String.format("Case #%d: %s", t, new Solution().solve(scanner)));
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