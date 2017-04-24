import java.io.PrintStream;
import java.util.*;

/**
 * Codejam to I/O for Women 2017 Problem A: Ticket Trouble
 * Check README.md for explanation.
 */
public class Main {

    private String solve(Scanner scanner) {
        int f=scanner.nextInt(), s=scanner.nextInt();
        int[][] seats=new int[f][2];
        for (int i=0;i<f;i++) {
            seats[i][0]=scanner.nextInt();
            seats[i][1]=scanner.nextInt();
        }
        int max=0;
        for (int i=1;i<=s;i++) {
            boolean[] used=new boolean[s+1];
            int cnt=0;
            for (int[] seat: seats) {
                if (seat[0]==i && !used[seat[1]]) {
                    cnt++;
                    used[seat[1]]=true;
                }
                else if (seat[1]==i && !used[seat[0]]) {
                    cnt++;
                    used[seat[0]]=true;
                }
            }
            max=Math.max(max, cnt);
        }
        return String.valueOf(max);
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