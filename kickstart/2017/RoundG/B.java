import java.io.PrintStream;
import java.util.*;

/**
 * KickStart 2017 Round G
 * Problem B. Cards Game
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        long[][] cards=new long[n][2];
        for (int i=0;i<n;i++) cards[i][0]=scanner.nextLong();
        for (int i=0;i<n;i++) cards[i][1]=scanner.nextLong();
        long ans=Long.MAX_VALUE;
        for (int i=0;i<n;i++) {
            boolean[] visited=new boolean[n];
            visited[i]=true;
            int used=1;
            long curr=0;
            while (used<n) {
                int index=-1; long v=Long.MAX_VALUE;
                for (int x=0;x<n;x++) {
                    if (visited[x]) continue;
                    long min=Long.MAX_VALUE;
                    for (int y=0;y<n;y++) {
                        if (!visited[y]) continue;
                        min=Math.min(min, Math.min(cards[x][0]^cards[y][1], cards[x][1]^cards[y][0]));
                    }
                    if (v>min) {
                        index=x; v=min;
                    }
                }
                visited[index]=true;
                curr+=v;
                used++;
            }
            ans=Math.min(ans, curr);
        }
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