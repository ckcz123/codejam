import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2017 Round 1B
 * Problem C. Pony Express
 */
public class Main {

    private String solve(Scanner scanner) throws Throwable {
        int n=scanner.nextInt(), q=scanner.nextInt();
        int[] e=new int[n];
        double[] s=new double[n];
        for (int i=0;i<n;i++) {
            e[i]=scanner.nextInt();
            s[i]=scanner.nextDouble();
        }
        long[][] distance=new long[n][n];
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                distance[i][j]=scanner.nextLong();
                if (i==j) distance[i][j]=0;
                if (distance[i][j]==-1) distance[i][j]=Integer.MAX_VALUE;
            }
        }
        for (int k=0;k<n;k++) {
            for (int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    distance[i][j]=Math.min(distance[i][j], distance[i][k]+distance[k][j]);
                }
            }
        }
        ArrayList<String> arrayList=new ArrayList<>();
        while (q-->0) {
            arrayList.add(String.format("%.9f",
                    query(n, e, s, distance, scanner.nextInt()-1, scanner.nextInt()-1)));
        }
        return String.join(" ", arrayList);
    }

    private double query(int n, int[] e, double[] s, long[][] distance, int start, int end) {
        double[] time=new double[n];
        Arrays.fill(time, 1e18);
        PriorityQueue<City> queue=new PriorityQueue<>(Comparator.comparingDouble(c->c.t));
        queue.offer(new City(start, e[start], s[start], 0));
        while (!queue.isEmpty()) {
            City city=queue.poll();
            if (time[city.id]<=city.t+1e-9) continue;
            time[city.id]=city.t;
            for (int x=0;x<n;x++) {
                if (distance[city.id][x]<=city.e) {
                    queue.offer(new City(x, e[x], s[x], city.t+distance[city.id][x]/city.s));
                }
            }
        }
        return time[end];
    }

    private class City {
        int id, e;
        double s, t;
        public City(int _id, int _e, double _s, double _t) {
            id=_id;e=_e;s=_s;t=_t;
        }
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
            }
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}