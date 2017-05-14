import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2017 Round 2
 * Problem C. Beaming With Joy
 * Only small solved.
 */
public class Main {

    private String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int r=scanner.nextInt(), c=scanner.nextInt();
        char[][] map=new char[r][c];
        for (int i=0;i<r;i++) {
            map[i]=scanner.next().toCharArray();
            for (int j=0;j<c;j++) {
                if (map[i][j]=='-' || map[i][j]=='|')
                    map[i][j]='?';
            }
        }
        for (int i=0;i<r;i++) {
            for (int j=0;j<c;j++) {
                if (map[i][j]=='.' || map[i][j]=='#') continue;

                // can horizontal?
                boolean horizontal=true;
                for (int x=j-1;x>=0;x--) {
                    if (map[i][x]!='.' && map[i][x]!='#') {
                        horizontal=false;break;
                    }
                    if (map[i][x]=='#') break;
                }
                for (int x=j+1;x<c;x++) {
                    if (map[i][x]!='.' && map[i][x]!='#') {
                        horizontal=false;break;
                    }
                    if (map[i][x]=='#') break;
                }

                // can vertical?
                boolean vertical=true;
                for (int x=i-1;x>=0;x--) {
                    if (map[x][j]!='.' && map[x][j]!='#') {
                        vertical=false;break;
                    }
                    if (map[x][j]=='#') break;
                }
                for (int x=i+1;x<r;x++) {
                    if (map[x][j]!='.' && map[x][j]!='#') {
                        vertical=false;break;
                    }
                    if (map[x][j]=='#') break;
                }

                if (!horizontal && !vertical) return "IMPOSSIBLE";
                if (!horizontal) map[i][j]='|';
                if (!vertical) map[i][j]='-';
            }
        }

        HashMap<Integer, ArrayList<Integer>> hashMap=new HashMap<>();

        // can be one?
        for (int i=0;i<r;i++) {
            for (int j=0;j<c;j++) {
                if (map[i][j]=='.') {

                    boolean has=false;

                    int hx=-1, hy=-1, vx=-1, vy=-1;
                    // can horizontal?
                    for (int x=j-1;x>=0;x--) {
                        if (map[i][x]=='#' || map[i][x]=='|') break;
                        if (map[i][x]=='-') {has=true;break;}
                        if (map[i][x]=='?') {hx=i;hy=x;break;}
                    }
                    if (has) continue;
                    for (int x=j+1;x<c;x++) {
                        if (map[i][x]=='#' || map[i][x]=='|') break;
                        if (map[i][x]=='-') {has=true;break;}
                        if (map[i][x]=='?') {hx=i;hy=x;break;}
                    }
                    if (has) continue;

                    // can vertical?
                    for (int x=i-1;x>=0;x--) {
                        if (map[x][j]=='#' || map[x][j]=='-') break;
                        if (map[x][j]=='|') {has=true;break;}
                        if (map[x][j]=='?') {vx=x;vy=j;break;}
                    }
                    if (has) continue;
                    for (int x=i+1;x<r;x++) {
                        if (map[x][j]=='#' || map[x][j]=='-') break;
                        if (map[x][j]=='|') {has=true;break;}
                        if (map[x][j]=='?') {vx=x;vy=j;break;}
                    }
                    if (has) continue;

                    if (hx==-1 && vx==-1) return "IMPOSSIBLE";
                    if (hx==-1) {
                        map[vx][vy]='|';
                        continue;
                    }
                    if (vx==-1) {
                        map[hx][hy]='-';
                        continue;
                    }

                    // horizontal: 0
                    // vertical: 1
                    int buildh=hx*1000+hy*10+1, buildv=vx*1000+vy*10;
                    ArrayList<Integer> hl=hashMap.getOrDefault(buildh, new ArrayList<>());
                    hl.add(buildv); hashMap.put(buildh, hl);
                    ArrayList<Integer> vl=hashMap.getOrDefault(buildv, new ArrayList<>());
                    vl.add(buildh); hashMap.put(buildv, vl);

                }
            }
        }

        if (dfs(r, c, map, hashMap, 0, 0)) {
            ArrayList<String> list=new ArrayList<>();
            list.add("POSSIBLE");
            for (int i=0;i<r;i++)
                list.add(new String(map[i]));
            return String.join("\n", list);
        }
        else return "IMPOSSIBLE";
    }

    private boolean dfs(int r, int c, char[][] map, HashMap<Integer, ArrayList<Integer>> hashMap,
                        int x, int y) {
        if (x>=r) return true;
        if (y==c) return dfs(r, c, map, hashMap, x+1, 0);
        if (map[x][y]=='#' || map[x][y]=='.') return dfs(r, c, map, hashMap, x, y+1);
        boolean notsure=map[x][y]=='?';
        if (notsure || map[x][y]=='-') {
            map[x][y]='-';
            boolean able=true;
            int state=1000*x+10*y;
            ArrayList<Integer> list=hashMap.getOrDefault(state, new ArrayList<>());
            for (int v: list) {
                int nx=v/1000, ny=(v-1000*nx)/10, dir=v%10;
                if (dir==1 && map[nx][ny]=='|') able=false;
                if (dir==0 && map[nx][ny]=='-') able=false;
            }
            if (able && dfs(r, c, map, hashMap, x, y+1))
                return true;
        }
        if (notsure || map[x][y]=='|') {
            map[x][y]='|';
            boolean able=true;
            int state=1000*x+10*y+1;
            ArrayList<Integer> list=hashMap.getOrDefault(state, new ArrayList<>());
            for (int v: list) {
                int nx=v/1000, ny=(v-1000*nx)/10, dir=v%10;
                if (dir==1 && map[nx][ny]=='|') able=false;
                if (dir==0 && map[nx][ny]=='-') able=false;
            }
            if (able && dfs(r, c, map, hashMap, x, y+1))
                return true;
        }
        return false;
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