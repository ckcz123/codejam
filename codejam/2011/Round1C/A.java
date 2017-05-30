import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2011 Round 1C
 * Problem A. Square Tiles
 */
public class Main {

    public String solve(Scanner scanner) {
        int m=scanner.nextInt(), n=scanner.nextInt();
        char[][] map=new char[m][n];
        for (int i=0;i<m;i++) map[i]=scanner.next().toCharArray();
        return "\n"+(able(m,n,map)?String.join("\n",
                Arrays.stream(map).map(String::valueOf).collect(Collectors.toList()))
                :"Impossible");
    }

    private boolean able(int m, int n, char[][] map) {
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if (map[i][j]=='#') {
                    if (i==m-1 || j==n-1 || map[i+1][j]!='#' || map[i][j+1]!='#' || map[i+1][j+1]!='#')
                        return false;
                    map[i][j]='/';map[i][j+1]='\\';map[i+1][j]='\\';map[i+1][j+1]='/';
                    return able(m,n,map);
                }
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