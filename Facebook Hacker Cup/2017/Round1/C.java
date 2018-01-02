import java.io.*;
import java.util.*;

/**
 * Facebook Hacker Cup 2017 Round 1
 * Problem C. Manic Moving
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt(), m=scanner.nextInt(), k=scanner.nextInt();
        int[][] distance=new int[n][n];
        for (int i=0;i<n;i++) {
            Arrays.fill(distance[i], 999999999);
            distance[i][i]=0;
        }
        for (int i=0;i<m;i++) {
            int a=scanner.nextInt()-1, b=scanner.nextInt()-1, g=scanner.nextInt();
            distance[a][b]=distance[b][a]=Math.min(distance[a][b], g);
        }
        for (int w=0;w<n;w++) {
            for (int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    distance[i][j]=Math.min(distance[i][j], distance[i][w]+distance[w][j]);
                }
            }
        }
        int[][] families=new int[k][2];
        for (int i=0;i<k;i++) families[i]=new int[]{scanner.nextInt()-1, scanner.nextInt()-1};
        for (int[] family: families)
            if (distance[family[0]][family[1]]==999999999) return "-1";

        // calculate
        int now1=families[0][0], need1=distance[0][now1], now2=-1, need2=-1;
        for (int i=0;i<k-1;i++) {
            int[] fnow = families[i], fnext=families[i+1];
            int nx1, ny1, nx2, ny2;
            // nx1: unload before load
            nx1=fnext[0]; ny1=Math.min(need1+distance[now1][fnow[1]]+distance[fnow[1]][nx1],
                    now2>=0?need2+distance[now2][fnow[1]]+distance[fnow[1]][nx1]:999999999);

            // load before unload
            nx2=fnow[1]; ny2=Math.min(need1+distance[now1][fnext[0]]+distance[fnext[0]][nx2],
                    now2>=0?need2+distance[now2][fnext[0]]+distance[fnext[0]][nx2]:999999999);

            now1=nx1; need1=ny1; now2=nx2; need2=ny2;
        }
        return String.valueOf(Math.min(need1+distance[now1][families[k-1][1]], now2>=0?need2+distance[now2][families[k-1][1]]:999999999));
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
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
