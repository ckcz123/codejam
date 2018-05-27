import java.io.PrintStream;
import java.util.*;

/**
 * KickStart 2018 Round C
 * Problem A. Planet Distance
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        ArrayList<Integer>[] lists = new ArrayList[n];
        for (int i=0;i<n;i++) lists[i]=new ArrayList<>();

        for (int i=0;i<n;i++) {
            int x=scanner.nextInt()-1, y=scanner.nextInt()-1;
            lists[x].add(y);
            lists[y].add(x);
        }

        int[] visited=new int[n]; visited[0]=1;
        boolean[] circle = findCircle(n, lists, 0, -1, new int[n], visited);

        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE/10);

        for (int i=0;i<n;i++) {
            if (!circle[i]) continue;
            distance[i]=0;
            Queue<Integer> queue=new LinkedList<>();
            queue.offer(i);

            while (!queue.isEmpty()) {
                int curr = queue.poll();
                for (int v: lists[curr]) {
                    if (distance[v]>distance[curr]+1) {
                        distance[v]=distance[curr]+1;
                        queue.offer(v);
                    }
                }
            }
        }
        return String.join(" ", Arrays.stream(distance).boxed().map(String::valueOf).collect(Collectors.toList()));

    }

    private boolean[] findCircle(int n, ArrayList<Integer>[] lists, int curr, int last, int[] father, int[] visited) {
        for (int v: lists[curr]) {
            if (v==last) continue;
            if (visited[v]==1) {
                // found!
                boolean[] circle = new boolean[n];
                circle[v]=true;
                while (curr!=v) {
                    circle[curr]=true;
                    curr=father[curr];
                }
                return circle;
            }
            father[v]=curr;
            if (visited[v]==0) {
                visited[v]=1;
                boolean[] find = findCircle(n, lists, v, curr, father, visited);
                if (find!=null) return find;
                visited[v]=2;
            }
        }
        return null;
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