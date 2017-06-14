import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2009 Qualification Round
 * Problem B. Watersheds
 */
public class Main {

    class UnionFind {

        int[] father;
        int r, c;

        public UnionFind(int _r, int _c) {
            r=_r;c=_c;
            father=new int[r*c];
            for (int i=0;i<r*c;i++) father[i]=i;
        }

        private int findfather(int x) {
            return father[x]==x?x:(father[x]=findfather(father[x]));
        }

        public int[] findfather(int x, int y) {
            int t=findfather(x*c+y);
            return new int[] {t/c, t%c};
        }

        public void setfather(int x1, int y1, int x2, int y2) {
            father[x1*c+y1]=x2*c+y2;
        }

    }

    public String solve(Scanner scanner) {
        int r=scanner.nextInt(), c=scanner.nextInt();
        int[][] map=new int[r][c];
        for (int i=0;i<r;i++) for (int j=0;j<c;j++) map[i][j]=scanner.nextInt();
        UnionFind find=new UnionFind(r, c);

        for (int i=0;i<r;i++) {
            for (int j=0;j<c;j++) {

                // find father of (i,j)
                int x=i, y=j, h=map[i][j];
                for (int[] dir: new int[][] {{-1,0},{0,-1},{0,1},{1,0}}) {
                    int nx=i+dir[0], ny=j+dir[1];
                    if (nx<0 || nx>=r || ny<0 || ny>=c || map[nx][ny]>=h)
                        continue;
                    x=nx; y=ny; h=map[nx][ny];
                }

                find.setfather(i,j,x,y);
            }
        }

        char curr='a';
        char[][] ans=new char[r][c];
        for (int i=0;i<r;i++) {
            for (int j=0;j<c;j++) {
                int[] v=find.findfather(i,j);
                if (ans[v[0]][v[1]]==0) {
                    ans[v[0]][v[1]]=curr++;
                }
                ans[i][j]=ans[v[0]][v[1]];
            }
        }
        return "\n"+String.join("\n", Arrays.stream(ans).map(chs ->
                String.join(" ", String.valueOf(chs).split(""))).collect(Collectors.toList()));

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