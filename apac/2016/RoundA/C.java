import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), m=scanner.nextInt();
        int[][] distance=new int[n][n];
        for (int i=0;i<n;i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE/10);
            distance[i][i]=0;
        }
        int[][] roads=new int[m][3];
        for (int i=0;i<m;i++) {
            roads[i]=new int[] {scanner.nextInt(), scanner.nextInt(), scanner.nextInt()};
            int x=roads[i][0], y=roads[i][1], r=roads[i][2];
            distance[x][y]=distance[y][x]=Math.min(distance[x][y], r);
        }
        for (int k=0;k<n;k++) {
            for (int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    distance[i][j]=Math.min(distance[i][j], distance[i][k]+distance[k][j]);
                }
            }
        }
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("");
        for (int i=0;i<m;i++) {
            if (distance[roads[i][0]][roads[i][1]]!=roads[i][2])
                arrayList.add(String.valueOf(i));
        }
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