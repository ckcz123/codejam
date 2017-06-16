import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2009 Round 1B
 * Problem C. Square Math
 */
public class Main {

    class State implements Comparable<State> {
        int x, y, val;
        String s;
        public State(int _x, int _y, int _val, String _s) {
            x=_x;y=_y;val=_val;s=_s;
        }
        public int compareTo(State another) {
            if (s==null) return 1;
            if (another.s==null) return -1;
            return s.length()==another.s.length()?
                    s.compareTo(another.s):s.length()-another.s.length();
        }
    }

    // bound of bfs
    private static final int MAX = 1000;

    public String solve(Scanner scanner) {
        int w=scanner.nextInt(), q=scanner.nextInt();
        char[][] map=new char[w][w];
        for (int i=0;i<w;i++) map[i]=scanner.next().toCharArray();
        State[][][] states=new State[w][w][2*MAX];
        for (int i=0;i<w;i++) for (int j=0;j<w;j++) for (int k=0;k<2*MAX;k++)
            states[i][j][k]=new State(i,j,k,null);
        bfs(w, map, states);
        ArrayList<String> list=new ArrayList<>();
        while (q-->0) {
            int v=scanner.nextInt();
            State state=new State(-1,-1,-1,null);
            for (int i=0;i<w;i++) {
                for (int j=0;j<w;j++) {
                    if (states[i][j][v+MAX].compareTo(state)<=0) {
                        state=states[i][j][v+MAX];
                    }
                }
            }
            list.add(state.s);
        }
        return "\n"+String.join("\n", list);
    }

    private void bfs(int w, char[][] map, State[][][] states) {
        PriorityQueue<State> queue=new PriorityQueue<>();
        for (int i=0;i<w;i++) {
            for (int j=0;j<w;j++) {
                if (map[i][j]>='0' && map[i][j]<='9') {
                    queue.offer(new State(i,j,map[i][j]-'0',String.valueOf(map[i][j])));
                }
            }
        }
        while (!queue.isEmpty()) {
            State state=queue.poll();
            int x=state.x, y=state.y, v=state.val, nv=v+MAX;
            String s=state.s;
            if (nv<0 || nv>=2*MAX || states[x][y][nv].compareTo(state)<=0) continue;
            states[x][y][nv]=state;

            int[][] dirs=new int[][] {{-1,0},{0,-1},{1,0},{0,1}};

            for (int[] dir1: dirs) {
                int nx=x+dir1[0], ny=y+dir1[1];
                if (nx<0 || nx>=w || ny<0 || ny>=w) continue;
                char c=map[nx][ny];

                for (int[] dir2: dirs) {
                    int n2x=nx+dir2[0], n2y=ny+dir2[1];
                    if (n2x<0 || n2x>=w || n2y<0 || n2y>=w) continue;

                    int v2=map[n2x][n2y]-'0';
                    if (c=='+') {
                        queue.offer(new State(n2x, n2y, v2+v, ""+v2+c+s));
                    }
                    else {
                        int currval=map[x][y]-'0';
                        queue.offer(new State(n2x, n2y, v2-2*currval+v, ""+v2+c+s));
                    }

                }
            }
        }
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