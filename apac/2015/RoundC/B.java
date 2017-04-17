import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private String solve(Scanner scanner) {
        HashMap<String, Integer> map=new HashMap<>();
        int lines=scanner.nextInt(), n=0;
        int[] waitTime=new int[lines+1];
        int[][] times=new int[lines+1][];
        for (int i=1;i<=lines;i++) {
            int stations=scanner.nextInt();
            waitTime[i]=scanner.nextInt();
            times[i]=new int[stations];
            for (int j=1;j<=stations;j++) {
                map.put(i+","+j, n++);
                map.put(i+"~"+j, n++);
            }
            for (int j=1;j<stations;j++) times[i][j]=scanner.nextInt();
        }
        int[][] data=new int[n][n];
        for (int i=0;i<n;i++) {
            Arrays.fill(data[i], Integer.MAX_VALUE/10);
            data[i][i]=0;
        }
        for (int i=1;i<=lines;i++) {
            int stations=times[i].length;
            for (int j=1;j<stations;j++) {
                int x=map.get(i+","+j), y=map.get(i+","+(j+1));
                data[x][y]=data[y][x]=times[i][j];
            }
            for (int j=1;j<=stations;j++) {
                int x=map.get(i+","+j), y=map.get(i+"~"+j);
                data[y][x]=waitTime[i];
                data[x][y]=0;
            }
        }
        int m=scanner.nextInt();
        int[][] tunnels=new int[m][2];
        for (int i=0;i<m;i++) {
            int l1=scanner.nextInt(), s1=scanner.nextInt(),
                    l2=scanner.nextInt(), s2=scanner.nextInt();
            int x=map.get(l1+"~"+s1), y=map.get(l2+"~"+s2);
            tunnels[i][0]=x;tunnels[i][1]=y;
            int t=scanner.nextInt();
            data[x][y]=Math.min(data[x][y], t);
            data[y][x]=Math.min(data[y][x], t);
        }
        int query=scanner.nextInt();
        ArrayList<String> ans=new ArrayList<>();
        ans.add("");
        while (query-->0) {
            int l1=scanner.nextInt(), s1=scanner.nextInt(),
                    l2=scanner.nextInt(), s2=scanner.nextInt();
            int x=map.get(l1+"~"+s1), y=map.get(l2+","+s2), z=map.get(l2+"~"+s2);
            int time=solve(n, data, x, y, z);
            if (time>=Integer.MAX_VALUE/20) ans.add("-1");
            else ans.add(String.valueOf(time));
        }
        return String.join("\n", ans);
    }

    private int solve(int n, int[][] times, int start, int end1, int end2) {
        int[] dist=new int[n];
        boolean[] visited=new boolean[n];
        for (int i=0;i<n;i++)
            dist[i]=times[start][i];
        dist[start]=0;
        visited[start]=true;
        for (int i=2;i<=n;i++) {
            int mindist=Integer.MAX_VALUE/20, u=-1;
            for (int j=0;j<n;j++) {
                if (!visited[j] && dist[j]<mindist) {
                    u=j; mindist=dist[j];
                }
            }
            if (u==-1) break;
            visited[u]=true;
            for (int j=0;j<n;j++) {
                if (!visited[j] && dist[u]+times[u][j]<dist[j]) {
                    dist[j]=dist[u]+times[u][j];
                }
            }
        }
        return Math.min(dist[end1], dist[end2]);
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