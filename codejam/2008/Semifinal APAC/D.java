import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 APAC Semifinal
 * Problem D. Modern Art Plagiarism
 */
public class Main {

    class Tree {
        int size;
        List<Tree> children;
        public Tree() {
            size=1;
            children=new ArrayList<>();
        }
    }

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        List<Integer>[] a=init(scanner, n);
        int m=scanner.nextInt();
        List<Integer>[] b=init(scanner, m);

        Tree bTree=makeTree(b, 0, new boolean[m]);
        for (int i=0;i<n;i++)
            if (isSubTree(makeTree(a, i, new boolean[n]), bTree))
                return "YES";
        return "NO";
    }

    private List<Integer>[] init(Scanner scanner, int n) {
        List<Integer>[] lists=new List[n];
        for (int i=0;i<n;i++) lists[i]=new ArrayList<>();
        for (int i=0;i<n-1;i++) {
            int a=scanner.nextInt()-1, b=scanner.nextInt()-1;
            lists[a].add(b);
            lists[b].add(a);
        }
        return lists;
    }

    private Tree makeTree(List<Integer>[] lists, int index, boolean[] used) {
        used[index]=true;
        Tree tree=new Tree();
        for (int v: lists[index])
            if (!used[v]) {
                Tree child=makeTree(lists, v, used);
                tree.children.add(child);
                tree.size+=child.size;
            }
        return tree;
    }

    // is b a sub-tree of a?
    private boolean isSubTree(Tree a, Tree b) {
        if (a.size<b.size || a.children.size()<b.children.size()) return false;
        return isSubTree(a.children, b.children, 0, new boolean[a.children.size()]);
    }

    private boolean isSubTree(List<Tree> a, List<Tree> b, int index, boolean[] used) {
        if (index==b.size()) return true;
        for (int i=0;i<a.size();i++) {
            if (used[i]) continue;
            if (isSubTree(a.get(i), b.get(index))) {
                used[i]=true;
                if (isSubTree(a, b, index+1, used)) {
                    used[i]=false;
                    return true;
                }
                used[i]=false;
            }
        }
        return false;
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