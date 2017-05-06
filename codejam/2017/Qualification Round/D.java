import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2017 Qualification Round
 * Problem D: Fashion Show
 */
public class Main {

    private String solve(Scanner scanner) {
        return solveLarge(scanner);
    }

    private String solveLarge(Scanner scanner) {
        int n=scanner.nextInt(), m=scanner.nextInt();
        char[][] origin=new char[n][n];
        int[][] plus=new int[n][n], multi=new int[n][n];

        while (m-->0) {
            String s=scanner.next();
            int r=scanner.nextInt(), c=scanner.nextInt();
            origin[r-1][c-1]=s.charAt(0);
            if (origin[r-1][c-1]=='o' || origin[r-1][c-1]=='+')
                plus[r-1][c-1]=1;
            if (origin[r-1][c-1]=='o' || origin[r-1][c-1]=='x')
                multi[r-1][c-1]=2;
        }
        calPlus(n, plus);
        calMulti(n, multi);
        char[] chars="\0+xo".toCharArray();
        char[][] board=new char[n][n];
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                board[i][j]=chars[plus[i][j]+multi[i][j]];
            }
        }
        return getAnswer(n, board, origin);
    }

    private void calPlus(int n, int[][] plus) {
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (plus[i][j]==1)
                    tag(n, plus, i, j);
            }
        }
        while (true) {
            int[] cnt=update(n, plus);
            int mindex=-1, val=Integer.MAX_VALUE;
            for (int i=0;i<2*n-1;i++) {
                if (cnt[i]>0 && val>cnt[i]) {
                    mindex=i;
                    val=cnt[i];
                }
            }
            if (mindex==-1) break;
            for (int i=0;i<n;i++) {
                int j=mindex-i;
                if (j<0 || j>=n) continue;
                if (plus[i][j]==0) {
                    plus[i][j]=1;
                    tag(n, plus, i, j);
                }
            }
        }
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                plus[i][j]=Math.max(plus[i][j], 0);
            }
        }
    }

    private void tag(int n, int[][] plus, int r, int c) {
        for (int x: new int[] {-1, 1}) {
            for (int i=-n;i<=n;i++) {
                int nr=r+i, nc=c+x*i;
                if (i==0 || nr<0 || nr>=n || nc<0 || nc>=n) continue;
                plus[nr][nc]=-1;
            }
        }
    }

    private int[] update(int n, int[][] plus) {
        int[] cnt=new int[2*n-1];
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (plus[i][j]==1) cnt[i+j]=-1;
                if (plus[i][j]==0 && cnt[i+j]>=0)
                    cnt[i+j]++;
            }
        }
        return cnt;
    }

    private void calMulti(int n, int[][] multi) {
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (ableMulti(n, multi, i, j))
                    multi[i][j]=2;
            }
        }
    }

    private boolean ableMulti(int n, int[][] multi, int r, int c) {
        for (int i=0;i<n;i++) {
            if (i!=r && multi[i][c]!=0) return false;
            if (i!=c && multi[r][i]!=0) return false;
        }
        return true;
    }


    private String getAnswer(int n, char[][] board, char[][] origin) {
        int score=0;
        ArrayList<String> arrayList=new ArrayList<>();
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (board[i][j]=='+' || board[i][j]=='x') score++;
                if (board[i][j]=='o') score+=2;
                if (board[i][j]!=origin[i][j])
                    arrayList.add(board[i][j]+" "+(i+1)+" "+(j+1));
            }
        }
        arrayList.add(0, score+" "+arrayList.size());
        return String.join("\n", arrayList);
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