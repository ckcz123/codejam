import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2014 Round 1A
 * Problem B. Full Binary Tree
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        ArrayList<Integer>[] arrayLists=new ArrayList[n+1];
        for (int i=1;i<=n;i++) arrayLists[i]=new ArrayList<>();
        for (int i=1;i<=n-1;i++) {
            int x=scanner.nextInt(), y=scanner.nextInt();
            arrayLists[x].add(y);
            arrayLists[y].add(x);
        }
        int max=0;
        for (int i=1;i<=n;i++)
            max=Math.max(max, dfs(arrayLists, i, 0));
        return String.valueOf(n-max);
    }

    private int dfs(ArrayList<Integer>[] arrayLists, int current, int parent) {
        ArrayList<Integer> arrayList=arrayLists[current];
        if (arrayList.size()<2 || (parent!=0 && arrayList.size()==2))
            return 1;
        ArrayList<Integer> next=new ArrayList<>();
        for (int x: arrayList) {
            if (x!=parent)
                next.add(dfs(arrayLists, x, current));
        }
        next.sort((o1,o2)->o2-o1);
        return next.get(0)+next.get(1)+1;
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