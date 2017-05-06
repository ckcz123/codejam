import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2016 Round 2
 * Problem D. Freeform Factory
 * Only small solved.
 */
public class Main {

    private String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int n=scanner.nextInt(), origin=0;
        char[][] map=new char[n][];
        for (int i=0;i<n;i++) {
            map[i]=scanner.next().toCharArray();
            for (int j=0;j<n;j++) if (map[i][j]=='1') origin++;
        }
        int min=Integer.MAX_VALUE;
        for (int i=0;i<(1<<(n*n));i++) {
            char[][] data=new char[n][n];
            int cnt=0;
            for (int j=0;j<n*n;j++) {
                if ((i&(1<<j))!=0) {
                    cnt++;
                    data[j/n][j%n]='1';
                }
                else {
                    if (map[j/n][j%n]=='1') {
                        data=null;break;
                    }
                    data[j/n][j%n]='0';
                }
            }
            if (data==null) continue;
            boolean able=true;
            for (int k=0;k<n;k++) {
                if (!check(k, n, data)) able=false;
            }
            if (able) min=Math.min(min, cnt-origin);
        }
        return String.valueOf(min);
    }

    private boolean check(int x, int n, char[][] data) {
        HashSet<Integer> s1=new HashSet<>(), s2=new HashSet<>();
        s1.add(x);
        for (int w=0;w<2*n;w++) {
            for (int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    if (data[i][j]=='1' && (s1.contains(i) || s2.contains(j))) {
                        s1.add(i); s2.add(j);
                    }
                }
            }
        }
        if (s1.size()!=s2.size()) return false;
        for (int i: s1) {
            for (int j: s2) {
                if (data[i][j]!='1')
                    return false;
            }
        }
        return true;
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