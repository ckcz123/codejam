import java.io.*;
import java.util.*;

/**
 * Facebook Hacker Cup 2017 Round 1
 * Problem B. Fighting the Zombies
 */
public class Main {

    public String solve(Scanner scanner) {

        int n=scanner.nextInt(); long r=scanner.nextLong();
        long[][] points=new long[n][2];
        for (int i=0;i<n;i++) points[i]=new long[] {scanner.nextLong(), scanner.nextLong()};

        int ans=0;
        for (int i=0;i<n;i++) {
            long l1=points[i][0];
            for (int j=0;j<n;j++) {
                long t1=points[j][1];
                for (int k=0;k<n;k++) {
                    long l2=points[k][0];
                    for (int w=0;w<n;w++) {
                        long t2=points[w][1];
                        int cnt=0;
                        for (int x=0;x<n;x++) {
                            if (points[x][0]>=l1 && points[x][0]<=l1+r && points[x][1]>=t1 && points[x][1]<=t1+r)
                                cnt++;
                            else if (points[x][0]>=l2 && points[x][0]<=l2+r && points[x][1]>=t2 && points[x][1]<=t2+r)
                                cnt++;
                        }
                        ans=Math.max(ans, cnt);
                    }
                }
            }
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
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
