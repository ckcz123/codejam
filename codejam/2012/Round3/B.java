import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2012 Round 3
 * Problem B. Havannah
 */
public class Main {

    class UnionFind {
        int[] father;
        int s;
        UnionFind(int _s) {
            s=_s;
            father=new int[4*s*s];
            for (int i=0;i<4*s*s;i++) father[i]=i;
        }
        private int findfather(int x) {
            return father[x]==x?x:(father[x]=findfather(father[x]));
        }
        void link(int x1, int y1, int x2, int y2) {
            link(encode(x1,y1), encode(x2,y2));
        }
        private void link(int x, int y) {
            father[findfather(x)]=findfather(y);
        }
        boolean linked(int x1, int y1, int x2, int y2) {
            return linked(encode(x1,y1), encode(x2,y2));
        }
        private boolean linked(int x, int y) {
            return findfather(x)==findfather(y);
        }
        private int encode(int x, int y) {
            return x*(2*s)+y;
        }
    }

    public String solve(Scanner scanner) {
        int s=scanner.nextInt(), m=scanner.nextInt();
        boolean[][] map=new boolean[2*s][2*s];
        UnionFind unionFind =new UnionFind(s);
        int[][] corners=new int[][] {{1,1},{1,s},{s,1},{2*s-1,s},{s,2*s-1},{2*s-1,2*s-1}};
        int[][][] edges=new int[6][s-2][2];
        for (int i=2;i<s;i++) {
            edges[0][i-2]=new int[]{1,i};
            edges[1][i-2]=new int[]{i,1};
            edges[2][i-2]=new int[]{i,i+s-1};
            edges[3][i-2]=new int[]{i+s-1,i};
            edges[4][i-2]=new int[]{2*s-1,2*s-i};
            edges[5][i-2]=new int[]{2*s-i,2*s-1};
        }
        int[][] input=new int[m][2];
        for (int i=0;i<m;i++)
            input[i]=new int[] {scanner.nextInt(), scanner.nextInt()};
        for (int i=0;i<m;i++) {
            int x=input[i][0], y=input[i][1];
            boolean isRing=testRing(s, map, unionFind, x, y);
            map[x][y]=true;
            for (int[] adj: adjancent(s, map, x, y)) {
                unionFind.link(x,y,adj[0],adj[1]);
            }
            ArrayList<String> list=new ArrayList<>();
            if (testBridge(corners, unionFind, x, y)) list.add("bridge");
            if (testFork(edges, unionFind, x, y)) list.add("fork");
            if (isRing) list.add("ring");
            if (!list.isEmpty())
                return String.format("%s in move %d", String.join("-", list), i+1);
        }
        return "none";
    }

    private boolean testRing(int s, boolean[][] map, UnionFind unionFind, int x, int y) {
        ArrayList<int[]> adj=adjancent(s,map,x,y);
        int n=adj.size();
        if (n<2) return false;
        boolean[][] linked=new boolean[n][n];
        for (int i=0;i<n;i++) {
            linked[i][i]=true;
            for (int j=0;j<n;j++) {
                int[] u=adj.get(i), v=adj.get(j);
                int dx=u[0]-v[0], dy=u[1]-v[1];
                if (Math.abs(dx)<=1 && Math.abs(dy)<=1 && dx+dy!=0)
                    linked[i][j]=linked[j][i]=true;
            }
        }
        for (int k=0;k<n;k++) {
            for (int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    if (linked[i][k] && linked[k][j]) linked[i][j]=true;
                }
            }
        }
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                int[] u=adj.get(i), v=adj.get(j);
                if (!linked[i][j] && unionFind.linked(u[0],u[1],v[0],v[1]))
                    return true;
            }
        }
        return false;
    }

    private boolean testBridge(int[][] corners, UnionFind unionFind, int x, int y) {
        int cnt=0;
        for (int[] c: corners) {
            if (unionFind.linked(c[0],c[1],x,y))
                cnt++;
        }
        return cnt>=2;
    }

    private boolean testFork(int[][][] edges, UnionFind unionFind, int x, int y) {
        int cnt=0;
        for (int[][] edge: edges) {
            boolean is=false;
            for (int[] e: edge) {
                if (unionFind.linked(e[0],e[1],x,y)) {
                    is=true;break;
                }
            }
            if (is) cnt++;
        }
        return cnt>=3;
    }

    private ArrayList<int[]> adjancent(int s, boolean[][] map, int x, int y) {
        ArrayList<int[]> list=new ArrayList<>();
        for (int dx=-1;dx<=1;dx++) {
            for (int dy=-1;dy<=1;dy++) {
                if (dx+dy==0) continue;
                int nx=x+dx, ny=y+dy;
                if (!valid(nx,ny,s)) continue;
                if (map[nx][ny]) list.add(new int[]{nx,ny});
            }
        }
        return list;
    }

    private boolean valid(int x, int y, int s) {
        return x>=1 && y>=1 && x<2*s && y<2*s && Math.abs(x-y)<=s-1;
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