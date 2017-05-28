import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2011 Round 1B
 * Problem A. RPI
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        char[][] map=new char[n][n];
        for (int i=0;i<n;i++) {
            map[i]=scanner.next().toCharArray();
        }
        double[] wp=new double[n];
        for (int i=0;i<n;i++) {
            double s=0; int cnt=0;
            for (int j=0;j<n;j++) {
                if (map[i][j]=='.') continue;
                cnt++;
                if (map[i][j]=='1') s++;
            }
            wp[i]=s/cnt;
        }
        double[] owp=new double[n];
        for (int i=0;i<n;i++) {
            double s=0; int cnt=0;
            for (int j=0;j<n;j++) {
                if (map[i][j]=='.') continue;
                cnt++;
                double ss=0; int cntt=0;
                for (int k=0;k<n;k++) {
                    if (map[j][k]=='.' || k==i) continue;
                    cntt++;
                    if (map[j][k]=='1') ss++;
                }
                s+=ss/cntt;
            }
            owp[i]=s/cnt;
        }
        double[] oowp=new double[n];
        for (int i=0;i<n;i++) {
            double s=0; int cnt=0;
            for (int j=0;j<n;j++) {
                if (map[i][j]=='.') continue;
                cnt++;
                s+=owp[j];
            }
            oowp[i]=s/cnt;
        }
        ArrayList<String> ans=new ArrayList<>();
        ans.add("");
        for (int i=0;i<n;i++)
            ans.add(String.format("%.9f", 0.25*wp[i]+0.5*owp[i]+0.25*oowp[i]));
        return String.join("\n", ans);
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