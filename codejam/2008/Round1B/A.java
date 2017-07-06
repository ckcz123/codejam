import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 Round 1B
 * Problem A. Crop Triangles
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        long[][] cnt=new long[3][3];

        long a=scanner.nextLong(),b=scanner.nextLong(),c=scanner.nextLong()
                ,d=scanner.nextLong(),x=scanner.nextLong(),y=scanner.nextLong(),m=scanner.nextLong();

        for (int i=0;i<n;i++) {
            cnt[(int)x%3][(int)y%3]++;
            x=(a*x+b)%m;
            y=(c*y+d)%m;
        }

        long ans=0;

        /* 0,0,0 */

        // 00-00-00
        ans += cnt[0][0]*(cnt[0][0]-1)/2*(cnt[0][0]-2)/3;

        // 00-01-02
        ans += cnt[0][0]*cnt[0][1]*cnt[0][2];

        // 01-01-01
        ans += cnt[0][1]*(cnt[0][1]-1)/2*(cnt[0][1]-2)/3;

        // 02-02-02
        ans += cnt[0][2]*(cnt[0][2]-1)/2*(cnt[0][2]-2)/3;

        /* 1,1,1 */

        // 10-10-10
        ans += cnt[1][0]*(cnt[1][0]-1)/2*(cnt[1][0]-2)/3;

        // 10-11-12
        ans += cnt[1][0]*cnt[1][1]*cnt[1][2];

        // 11-11-11
        ans += cnt[1][1]*(cnt[1][1]-1)/2*(cnt[1][1]-2)/3;

        // 12-12-12
        ans += cnt[1][2]*(cnt[1][2]-1)/2*(cnt[1][2]-2)/3;

        /* 2,2,2 */

        // 20-20-20
        ans += cnt[2][0]*(cnt[2][0]-1)/2*(cnt[2][0]-2)/3;

        // 20-21-22
        ans += cnt[2][0]*cnt[2][1]*cnt[2][2];

        // 21-21-21
        ans += cnt[2][1]*(cnt[2][1]-1)/2*(cnt[2][1]-2)/3;

        // 22-22-22
        ans += cnt[2][2]*(cnt[2][2]-1)/2*(cnt[2][2]-2)/3;

        /* 0,1,2 */

        // 00-10-20
        ans += cnt[0][0]*cnt[1][0]*cnt[2][0];

        // 01-11-21
        ans += cnt[0][1]*cnt[1][1]*cnt[2][1];

        // 02-12-22
        ans += cnt[0][2]*cnt[1][2]*cnt[2][2];

        // 00-11-22
        ans += cnt[0][0]*cnt[1][1]*cnt[2][2];

        // 00-12-21
        ans += cnt[0][0]*cnt[1][2]*cnt[2][1];

        // 01-10-22
        ans += cnt[0][1]*cnt[1][0]*cnt[2][2];

        // 01-12-20
        ans += cnt[0][1]*cnt[1][2]*cnt[2][0];

        // 02-10-21
        ans += cnt[0][2]*cnt[1][0]*cnt[2][1];

        // 02-11-20
        ans += cnt[0][2]*cnt[1][1]*cnt[2][0];

        return String.valueOf(ans);

    }

    public static void main(String[] args) throws Exception {
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