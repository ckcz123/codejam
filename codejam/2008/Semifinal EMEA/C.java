import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 EMEA Semifinal
 * Problem C. Rainbow Trees
 */
public class Main {

    class Tree {
        Tree father;
        ArrayList<Tree> children;
        public Tree(Tree _father) {
            father=_father;
            children=new ArrayList<>();
        }
        int getDegree() {
            return children.size()+(father==null?0:1);
        }
    }

    private static final long MOD = 1000000009L;

    public String solve(Scanner scanner) throws Exception {
        int n=scanner.nextInt();
        long k=scanner.nextInt();
        ArrayList<Integer>[] lists=new ArrayList[n];
        for (int i=0;i<n;i++) lists[i]=new ArrayList<>();
        for (int i=1;i<=n-1;i++) {
            int x=scanner.nextInt()-1, y=scanner.nextInt()-1;
            lists[x].add(y); lists[y].add(x);
        }
        Tree root=makeTree(lists, 0, new boolean[n], null);
        return String.valueOf(calculate(root, k));
    }

    private Tree makeTree(ArrayList<Integer>[] lists, int index, boolean[] used, Tree father) {
        Tree tree=new Tree(father);
        used[index]=true;
        for (int v: lists[index]) {
            if (used[v]) continue;
            tree.children.add(makeTree(lists, v, used, tree));
        }
        return tree;
    }

    private long calculate(Tree root, long k) {
        int d=(root.father==null?0:root.father.getDegree());
        long ans=1, valid=k-d;
        for (Tree son: root.children) {
            if (valid==0) return 0;
            ans*=valid--;
            ans%=MOD;
            ans*=calculate(son, k);
            ans%=MOD;
        }
        return ans;
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