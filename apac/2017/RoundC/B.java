import java.io.PrintStream;
import java.util.*;

public class Main {

    public String solve(Scanner scanner) {
        int r=scanner.nextInt(), c=scanner.nextInt();
        int[][] map=new int[r][c];
        int k=scanner.nextInt();
        while (k-->0)
            map[scanner.nextInt()][scanner.nextInt()]=1;
        int[][] edge=new int[r][c];
        long ans=0;
        for (int i=0;i<r;i++) {
            for (int j=0;j<c;j++) {
                if (map[i][j]==1) continue;
                edge[i][j]=1+Math.min(i==0||j==0?0:edge[i-1][j-1],
                        Math.min(i==0?0:edge[i-1][j], j==0?0:edge[i][j-1]));
                ans+=edge[i][j];
            }
        }
        return String.valueOf(ans);
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
        System.err.println(String.format("Time used: %.3f", (end-start)/1000.));
    }
}
