import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2019 Round 1C
 * Problem C. Bacterial Tactics
 */
public class Solution {
    public String solve(Scanner scanner) {
        int r = scanner.nextInt(), c = scanner.nextInt();
        char[][] chars = new char[r][c];
        for (int i = 0; i < r; ++i)
            chars[i] = scanner.next().toCharArray();
        return String.valueOf(solveSmall(r, c, chars));
    }

    private int solveSmall(int r, int c, char[][] chars) {
        char[] map = new char[r*c];
        for (int i = 0; i < r; ++i) {
            for (int j = 0; j < c; ++j)
                map[i*c+j] = chars[i][j];
        }
        return dfs(r, c, map, 0, new HashMap<>());
    }

    private int dfs(int r, int c, char[] chars, long curr, HashMap<Long, Integer> map) {
        if (map.containsKey(curr)) return map.get(curr);
        int cnt = 0;
        for (int i = 0; i < r*c; ++i) {
            if ((curr & (1<<i)) != 0 || chars[i] == '#') continue;
            if (test1(r, c, chars, curr, i, map))
                cnt++;
            if (test2(r, c, chars, curr, i, map))
                cnt++;
        }
        map.put(curr, cnt);
        return cnt;
    }

    private boolean test1(int r, int c, char[] chars, long curr, int i, HashMap<Long, Integer> map) {
        curr |= (1<<i);
        int m = i/c, n = i%c;
        while (true) {
            m--; if (m<0) break;
            int u = m*c+n;
            if (chars[u]=='#') return false;
            if ((curr & (1<<u)) != 0) break;
            curr |= (1<<u);
        }
        m = i/c; n = i%c;
        while (true) {
            m++; if (m>=r) break;
            int u = m*c+n;
            if (chars[u]=='#') return false;
            if ((curr & (1<<u)) != 0) break;
            curr |= (1<<u);
        }
        return dfs(r, c, chars, curr, map) == 0;
    }

    private boolean test2(int r, int c, char[] chars, long curr, int i, HashMap<Long, Integer> map) {
        curr |= (1<<i);
        int m = i/c, n = i%c;
        while (true) {
            n--; if (n<0) break;
            int u = m*c+n;
            if (chars[u]=='#') return false;
            if ((curr & (1<<u)) != 0) break;
            curr |= (1<<u);
        }
        m = i/c; n = i%c;
        while (true) {
            n++; if (n>=c) break;
            int u = m*c+n;
            if (chars[u]=='#') return false;
            if ((curr & (1<<u)) != 0) break;
            curr |= (1<<u);
        }
        return dfs(r, c, chars, curr, map) == 0;
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