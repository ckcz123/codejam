import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2009 Round 3
 * Problem C. Football Team
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        int[][] positions=new int[n][2];
        for (int i=0;i<n;i++) {
            positions[i]=new int[] {scanner.nextInt(), scanner.nextInt()};
        }
        Arrays.sort(positions, Comparator.comparingInt(p->p[0]));
        if (test1(n, positions)) return "1";
        if (test2(n, positions)) return "2";
        if (test3(n, positions)) return "3";
        return "4";
    }

    private boolean test1(int n, int[][] positions) {
        HashSet<Integer> set=new HashSet<>();
        for (int i=0;i<n;i++) {
            int y=positions[i][1];
            if (!set.add(y) || set.contains(y-1) || set.contains(y+1))
                return false;
        }
        return true;
    }

    private boolean test2(int n, int[][] positions) {
        // make edges
        ArrayList<Integer>[] lists=new ArrayList[n];
        for (int i=0;i<n;i++) lists[i]=new ArrayList<>();
        for (int i=0;i<n;i++) {
            for (int dy=-1;dy<=1;dy++) {
                int index=-1;
                for (int j=i+1;j<n;j++) {
                    if (positions[j][1]==positions[i][1]+dy) {
                        index=j;break;
                    }
                }
                if (index!=-1) {
                    lists[i].add(index);
                    lists[index].add(i);
                }
            }
        }

        // bfs to find cycle
        boolean[] visited=new boolean[n];
        for (int i=0;i<n;i++) {
            if (visited[i]) continue;
            Queue<Integer> queue=new LinkedList<>();
            queue.offer(i);
            queue.offer(-1);
            visited[i]=true;
            while (!queue.isEmpty()) {
                int x=queue.poll(), last=queue.poll();
                for (int k: lists[x]) {
                    if (!visited[k]) {
                        queue.offer(k);
                        queue.offer(x);
                        visited[k]=true;
                    }
                    else {
                        if (k!=last) return false;
                    }
                }
            }
        }

        return true;
    }

    private boolean test3(int n, int[][] positions) {
        for (int i=0;i<n;i++) {
            boolean l=false, r=false,u=false,d=false;
            for (int j=0;j<n;j++) {
                if (positions[j][0]<positions[i][0] && positions[j][1]==positions[i][1])
                    l=true;
                if (positions[j][0]>positions[i][0] && positions[j][1]==positions[i][1])
                    r=true;
                if (positions[j][0]>positions[i][0] && positions[j][1]==positions[i][1]+1)
                    u=true;
                if (positions[j][0]>positions[i][0] && positions[j][1]==positions[i][1]-1)
                    d=true;
            }
            if (l && r && u && d) {
                // last l
                int previous=-1;
                for (int j=0;j<n;j++) {
                    if (positions[j][0]<positions[i][0] && positions[j][1]==positions[i][1])
                        previous=j;
                }
                int cnt=0;
                for (int j=0;j<n;j++) {
                    if (positions[j][0]<positions[i][0] && positions[j][0]>previous &&
                            Math.abs(positions[j][1]-positions[i][1])==1)
                        cnt++;
                }
                if (cnt%2!=0) return false;
            }
        }
        return true;
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