import java.io.PrintStream;
import java.util.*;

/**
 * KickStart 2017 Round F
 * Problem C. Catch Them All
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt(), m=scanner.nextInt(), p=scanner.nextInt();
        int[][] distance=new int[n][n];
        for (int i=0;i<n;i++)
            for (int j=0;j<n;j++)
                if (i!=j)
                    distance[i][j]=Integer.MAX_VALUE/10;
        for (int i=0;i<m;i++) {
            int x=scanner.nextInt()-1, y=scanner.nextInt()-1;
            distance[x][y]=distance[y][x]=scanner.nextInt();
        }

        // prim
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                for (int k=0;k<n;k++) {
                    distance[i][j]=Math.min(distance[i][j], distance[i][k]+distance[k][j]);
                }
            }
        }

        double[] ans=new double[n], tmp=new double[n];
        for (int i=0;i<p;i++) {
            for (int j=0;j<n;j++) {
                tmp[j]=0;
                for (int k=0;k<n;k++) {
                    if (j==k) continue;
                    tmp[j]+=(distance[j][k]+ans[k]);
                }
                tmp[j]/=(n-1);
            }
            for (int j=0;j<n;j++)
                ans[j]=tmp[j];
        }
        return String.format("%.9f", ans[0]);
    }

    public static void main(String[] args) throws Exception {
//        System.setOut(new PrintStream("output.txt"));
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