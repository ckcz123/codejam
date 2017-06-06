import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2010 Round 1A
 * Problem A. Rotate
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt(), k=scanner.nextInt();
        char[][] map=new char[n][n], data=new char[n][n];
        for (int i=0;i<n;i++) map[i]=scanner.next().toCharArray();
        for (int i=0;i<n;i++)
            for (int j=0;j<n;j++)
                data[i][j]=map[n-1-j][i];

        // fall down
        char[][] fall=new char[n][n];
        for (int i=0;i<n;i++) Arrays.fill(fall[i], '.');
        for (int j=0;j<n;j++) {
            for (int i=n-1,curr=n-1;i>=0;i--) {
                if (data[i][j]!='.') {
                    fall[curr][j]=data[i][j];
                    curr--;
                }
            }
        }

        // check
        boolean red=false, blue=false;
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (fall[i][j]=='.') continue;
                // start with (i,j)
                for (int[] dir: new int[][] {{0,1},{1,0},{1,1},{1,-1}}) {
                    boolean is=true;
                    for (int w=0;w<k;w++) {
                        int x=i+w*dir[0], y=j+w*dir[1];
                        if (x<0 || x>=n || y<0 || y>=n || fall[x][y]!=fall[i][j]) {
                            is=false;break;
                        }
                    }
                    if (is) {
                        if (fall[i][j]=='R') red=true;
                        else blue=true;
                        break;
                    }
                }
            }
        }
        return red?(blue?"Both":"Red"):(blue?"Blue":"Neither");
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