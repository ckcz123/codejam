import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private String solve(Scanner scanner) {
        int r=scanner.nextInt(), c=scanner.nextInt(), n=scanner.nextInt();

        /*for (int r=3;r<=5;r++) {
            for (int c=3;c<=5;c++) {
                for (int n=(r*c+1)/2+1;n<=r*c;n++) {
                    int small=solveSmall(r, c, n), large=solveLarge(r, c, n);
                    if (small!=large)
                        System.out.println(String.format("r=%d c=%d n=%d small=%d large=%d",
                                r,c,n,small,large));
                }
            }
        }
        return "";*/

        return String.valueOf(solveLarge(r,c,n));
    }

    private int solveLarge(int r, int c, int n) {
        if (r>c) {
            int t=r;r=c;c=t;
        }
        if (n<=(r*c+1)/2) return 0;
        if (r==1) return c-1-2*(c-n);
        if (r*c%2!=0 && n==(r*c+1)/2+1) return 3;


        int res=r*c-n, total=r*(c-1)+c*(r-1);
        if (res<=((r-2)*(c-2)+1)/2) return total-4*res;

        boolean[][] used=new boolean[r][c];
        int x=0;
        for (int i=1;i<r-1;i++) {
            for (int j=1;j<c-1;j++) {
                if (!used[i-1][j] && !used[i][j-1]) {
                    x++;
                    used[i][j]=true;
                }
            }
        }
        res-=x; total-=4*x;

        // minus 3
        for (int i=1;i<r-1;i++) {
            if (res>0 && !used[i][0] && !used[i][1] && !used[i-1][0] && !used[i+1][0]) {
                res--;
                used[i][0]=true;
                total-=3;
            }
            if (res>0 && !used[i][c-1] && !used[i][c-2] && !used[i-1][c-1] && !used[i+1][c-1]) {
                res--;
                used[i][c-1]=true;
                total-=3;
            }
        }
        for (int i=1;i<c-1;i++) {
            if (res>0 && !used[0][i] && !used[1][i] && !used[0][i-1] && !used[0][i+1]) {
                res--;
                used[0][i]=true;
                total-=3;
            }
            if (res>0 && !used[r-1][i] && !used[r-2][i] && !used[r-1][i-1] && !used[r-1][i+1]) {
                res--;
                used[r-1][i]=true;
                total-=3;
            }
        }
        return total-2*res;
    }

    private int solveSmall(int r, int c, int n) {
        int total=r*c, ans=Integer.MAX_VALUE;
        for (int i=0;i<(1<<total);i++) {
            boolean[][] has=new boolean[r][c];
            int cnt=0;
            for (int j=0;j<total;j++) {
                if (((1<<j)&i)!=0) {
                    cnt++;
                    has[j/c][j%c]=true;
                }
            }
            if (cnt!=n) continue;
            cnt=0;
            for (int u=0;u<r;u++) {
                for (int v=0;v<c;v++) {
                    if (has[u][v]) {
                        if (v<c-1 && has[u][v+1]) cnt++;
                        if (u<r-1 && has[u+1][v]) cnt++;
                    }
                }
            }
            ans=Math.min(ans, cnt);
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("E:\\desktop\\output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=scanner.nextInt();
        for (int t=1;t<=times;t++) {
            System.out.println(String.format("Case #%d: %s", t, new Main().solve(scanner)));
        }

    }

}