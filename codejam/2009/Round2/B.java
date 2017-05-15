import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2009 Round 2
 * Problem B. A Digging Problem
 */
public class Main {

    private String solve(Scanner scanner) {
        int m=scanner.nextInt(), n=scanner.nextInt(), f=scanner.nextInt();
        long[] data=new long[m];
        for (int i=0;i<m;i++) {
            data[i]=Long.parseLong(
                    new StringBuilder(scanner.next().replace('.','0').replace('#','1'))
                            .reverse().toString()
                    , 2);
        }
        int ans=dfs(data, m, n, f, 0, 0, data[0], 0, new HashMap<>());
        return ans>=m*n?"No":"Yes "+ans;
    }

    private int dfs(long[] data, int m, int n, int f,
                    int r, int pos, long currline, int fall, HashMap<String, Integer> map) {
        if (fall>f || !isEmpty(currline, pos)) return Integer.MAX_VALUE/10;
        if (r==m-1) return 0;
        long nextline=data[r+1];
        // falling...
        if (isEmpty(nextline, pos)) return dfs(data, m, n, f, r+1, pos,
                nextline, fall+1, map);

        // find leftmost & rightmost
        int leftmost=pos, rightmost=pos;
        while (leftmost>0 && isEmpty(currline, leftmost-1)
                && !isEmpty(nextline, leftmost-1))
            leftmost--;
        while (rightmost<n-1 && isEmpty(currline, rightmost+1)
                && !isEmpty(nextline, rightmost+1))
            rightmost++;
        String state=r+"~"+currline+"~"+leftmost;
        if (map.containsKey(state)) return map.get(state);
        int ans=Integer.MAX_VALUE/10;
        // can jump leftmost?
        if (leftmost>0 && isEmpty(currline, leftmost-1))
            ans=Math.min(ans, dfs(data, m, n, f, r+1, leftmost-1, nextline, 1, map));
        // can jump rightmost?
        if (rightmost<n-1 && isEmpty(currline, rightmost+1))
            ans=Math.min(ans, dfs(data, m, n, f, r+1, rightmost+1, nextline, 1, map));

        // choose [start, end] to dig
        for (int start=leftmost;start<=rightmost;start++) {
            for (int end=start;end<=rightmost;end++) {
                if (start==leftmost && end==rightmost) continue;
                long nl=nextline;
                for (int i=start;i<=end;i++) {
                    nl^=(1L<<i);
                }
                if (start!=leftmost)
                    ans=Math.min(ans, end-start+1+dfs(data, m, n, f, r+1, start, nl, 1, map));
                if (end!=rightmost)
                    ans=Math.min(ans, end-start+1+dfs(data, m, n, f, r+1, end, nl, 1, map));
            }
        }
        map.put(state, ans);
        return ans;
    }

    private boolean isEmpty(long v, int pos) {
        return (v&(1L<<pos))==0;
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        long times=scanner.nextInt();
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



