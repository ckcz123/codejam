import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2010 Round 2
 * Problem C. Bacteria
 * Only small solved.
 */
public class Main {

    private String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int[][] map=new int[100][100];
        int r=scanner.nextInt();
        while (r-->0) {
            int x1=scanner.nextInt()-1, y1=scanner.nextInt()-1,
                    x2=scanner.nextInt()-1, y2=scanner.nextInt()-1;
            for (int i=x1;i<=x2;i++)
                for (int j=y1;j<=y2;j++)
                    map[i][j]=1;
        }
        int cnt=0;
        while (true) {
            int[][] nmap=new int[100][100];
            boolean has=false;
            for (int i=0;i<100;i++) {
                for (int j=0;j<100;j++) {
                    if (map[i][j]==1) {
                        has=true;
                        if ((i>0 && map[i-1][j]==1) || (j>0 && map[i][j-1]==1))
                            nmap[i][j]=1;
                    }
                    else {
                        if (i>0 && j>0 && map[i][j-1]==1 && map[i-1][j]==1)
                            nmap[i][j]=1;
                    }
                }
            }
            if (!has) break;
            map=nmap;
            cnt++;
        }
        return String.valueOf(cnt);
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