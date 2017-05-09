import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2012 Round 2
 * Problem B. Spinning Blade
 */
public class Main {

    private String solve(Scanner scanner) {
        int m=scanner.nextInt(), n=scanner.nextInt();
        long d=scanner.nextLong();
        long[][] data=new long[m][n];
        for (int i=0;i<m;i++) {
            char[] chars=scanner.next().toCharArray();
            for (int j=0;j<n;j++) {
                data[i][j]=(chars[j]-'0')+d;
            }
        }
        long ans=Math.max(calOdd(m,n,data), calEven(m,n,data));
        return ans<=2?"IMPOSSIBLE":String.valueOf(ans);
    }

    private long calOdd(int m, int n, long[][] data) {
        long[][] sx=new long[m][n], ssx=new long[m][n];
        long[][] sy=new long[m][n], ssy=new long[m][n];
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                sx[i][j]=getVal(sx, i-1, j)+data[i][j];
                ssx[i][j]=getVal(ssx, i-1, j)+i*data[i][j];
                sy[i][j]=getVal(sy,  i, j-1)+data[i][j];
                ssy[i][j]=getVal(ssy, i, j-1)+j*data[i][j];
            }
        }
        long ans=0;
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                long sumx=0, sumy=0;
                for (int r=1;;r++) {
                    int left=j-r, right=j+r, top=i-r, bottom=i+r;
                    if (left<0 || right>=n || top<0 || bottom>=m) break;
                    sumx+=-r*(getVal(sy, top, right)-getVal(sy, top, left-1));
                    sumx+=r*(getVal(sy, bottom, right)-getVal(sy, bottom, left-1));

                    sumx+=(getVal(ssx, bottom, left)-getVal(ssx, top-1, left))
                            -i*(getVal(sx, bottom, left)-getVal(sx, top-1, left));
                    sumx+=(getVal(ssx, bottom, right)-getVal(ssx, top-1, right))
                            -i*(getVal(sx, bottom, right)-getVal(sx, top-1, right));

                    sumx+=r*(data[top][left]+data[top][right]-data[bottom][left]
                            -data[bottom][right]);

                    sumy+=-r*(getVal(sx, bottom, left)-getVal(sx, top-1, left));
                    sumy+=r*(getVal(sx, bottom, right)-getVal(sx, top-1, right));

                    sumy+=(getVal(ssy, top, right)-getVal(ssy, top, left-1))
                            -j*(getVal(sy, top, right)-getVal(sy, top, left-1));
                    sumy+=(getVal(ssy, bottom, right)-getVal(ssy, bottom, left-1))
                            -j*(getVal(sy, bottom, right)-getVal(sy, bottom, left-1));

                    sumy+=r*(data[top][left]-data[top][right]+data[bottom][left]
                            -data[bottom][right]);
                    long x=sumx+r*(data[top][left]+data[top][right]-data[bottom][left]
                            -data[bottom][right]);
                    long y=sumy+r*(data[top][left]-data[top][right]+data[bottom][left]
                            -data[bottom][right]);
                    if (x==0 && y==0) ans=Math.max(ans, r);
                }
            }
        }
        return 2*ans+1;
    }

    private long calEven(int m, int n, long[][] data) {
        long[][] sx=new long[m][n], ssx=new long[m][n];
        long[][] sy=new long[m][n], ssy=new long[m][n];
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                sx[i][j]=getVal(sx, i-1, j)+data[i][j];
                ssx[i][j]=getVal(ssx, i-1, j)+(2*i+1)*data[i][j];
                sy[i][j]=getVal(sy,  i, j-1)+data[i][j];
                ssy[i][j]=getVal(ssy, i, j-1)+(2*j+1)*data[i][j];
            }
        }
        long ans=0;
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                long sumx=0, sumy=0;
                for (int r=1;;r++) {
                    int left=j-r, right=j+r-1, top=i-r, bottom=i+r-1;
                    if (left<0 || right>=n || top<0 || bottom>=m) break;
                    sumx+=-(2*r-1)*(getVal(sy, top, right)-getVal(sy, top, left-1));
                    sumx+=(2*r-1)*(getVal(sy, bottom, right)-getVal(sy, bottom, left-1));

                    sumx+=(getVal(ssx, bottom, left)-getVal(ssx, top-1, left))
                            -2*i*(getVal(sx, bottom, left)-getVal(sx, top-1, left));
                    sumx+=(getVal(ssx, bottom, right)-getVal(ssx, top-1, right))
                            -2*i*(getVal(sx, bottom, right)-getVal(sx, top-1, right));

                    sumx+=(2*r-1)*(data[top][left]+data[top][right]-data[bottom][left]
                            -data[bottom][right]);

                    sumy+=-(2*r-1)*(getVal(sx, bottom, left)-getVal(sx, top-1, left));
                    sumy+=(2*r-1)*(getVal(sx, bottom, right)-getVal(sx, top-1, right));

                    sumy+=(getVal(ssy, top, right)-getVal(ssy, top, left-1))
                            -2*j*(getVal(sy, top, right)-getVal(sy, top, left-1));
                    sumy+=(getVal(ssy, bottom, right)-getVal(ssy, bottom, left-1))
                            -2*j*(getVal(sy, bottom, right)-getVal(sy, bottom, left-1));

                    sumy+=(2*r-1)*(data[top][left]-data[top][right]+data[bottom][left]
                            -data[bottom][right]);

                    long x=sumx+(2*r-1)*(data[top][left]+data[top][right]-data[bottom][left]
                            -data[bottom][right]);
                    long y=sumy+(2*r-1)*(data[top][left]-data[top][right]+data[bottom][left]
                            -data[bottom][right]);
                    if (x==0 && y==0) ans=Math.max(ans, r);
                }
            }
        }
        return 2*ans;
    }

    private long getVal(long[][] data, int u, int v) {
        if (u<0 || u>=data.length || v<0 || v>=data[0].length) return 0;
        return data[u][v];
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        long times=scanner.nextInt();
        long start=System.currentTimeMillis();
        for (long t=1;t<=times;t++) {
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