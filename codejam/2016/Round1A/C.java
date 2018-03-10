import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2016 Round 1A
 * Problem C. BFFs
 */
public class Main {

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

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        int[] f=new int[n];
        ArrayList<Integer>[] children=new ArrayList[n];
        for (int i=0;i<n;i++) children[i]=new ArrayList<>();
        for (int i=0;i<n;i++) {
            int x=scanner.nextInt()-1;
            f[i]=x;
            children[x].add(i);
        }

        int ans=0;
        // Step 1: find circle
        for (int i=0;i<n;i++)
            ans=Math.max(ans, calCircle(n, i, f));

        // Step 2: find bi-linked
        int v=0;
        for (int i=0;i<n;i++) {
            if (f[f[i]]==i) {
                v+=calDepth(i, f[i], children);
            }
        }
        return String.valueOf(Math.max(ans, v));
    }

    private int calCircle(int n, int x, int[] f) {
        boolean[] visited=new boolean[n];
        visited[x]=true;
        int len=1, curr=f[x];
        while (curr!=x) {
            if (visited[curr]) return 0;
            visited[curr]=true;
            len++;
            curr=f[curr];
        }
        return len;
    }

    private int calDepth(int x, int not, ArrayList<Integer>[] children) {
        int ans=0;
        for (int w: children[x])
            if (w!=not)
                ans=Math.max(ans, calDepth(w, not, children));
        return ans+1;
    }

}