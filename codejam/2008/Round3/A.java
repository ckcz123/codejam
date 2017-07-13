import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2008 Round 3
 * Problem A. How Big Are the Pockets?
 */
public class Main {

    private static final int BOUND = 3500;

    public String solve(Scanner scanner) {
        int l=scanner.nextInt();
        int[][] row=new int[2*BOUND][2], col=new int[2*BOUND][2];
        for (int i=0;i<2*BOUND;i++) {
            row[i][0]=col[i][0]=2*BOUND;
            row[i][1]=col[i][1]=0;
        }

        // current point: (BOUND, BOUND)
        int s=0, x=BOUND, y=BOUND, d=1;
        int[][] dir=new int[][] {{-1,0},{0,1},{1,0},{0,-1}};
        while (l-->0) {
            char[] chars=scanner.next().toCharArray();
            int t=scanner.nextInt();
            while (t-->0) {
                for (char c: chars) {
                    if (c=='L') d=(d+3)%4;
                    else if (c=='R') d=(d+1)%4;
                    else if (c=='F') {
                        int nx=x+dir[d][0], ny=y+dir[d][1];
                        s+=x*ny-y*nx;
                        // row direction -
                        // update col |
                        if (d%2==0) {
                            int _x=Math.min(x, nx);
                            col[_x][0]=Math.min(col[_x][0], y);
                            col[_x][1]=Math.max(col[_x][1], y);
                        }
                        else {
                            int _y=Math.min(y, ny);
                            row[_y][0]=Math.min(row[_y][0], x);
                            row[_y][1]=Math.max(row[_y][1], x);
                        }
                        x=nx;y=ny;
                    }
                }
            }
        }
        int area=0;
        for (int i=0;i<2*BOUND;i++)
            for (int j=0;j<2*BOUND;j++) {
                if ((col[i][0]<=j && col[i][1]>j) || (row[j][0]<=i && row[j][1]>i))
                    area++;
            }
        return String.valueOf(area-Math.abs(s/2));
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