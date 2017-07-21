import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 Round 2
 * Problem A. Cheating a Boolean Tree
 */
public class Main {

    class Tree {
        int val, operator;
        boolean leaf, change;
        public Tree(int _val) {
            val=_val; operator=0; leaf=true; change=false;
        }
        public Tree(int _op, int _change) {
            operator=_op; change=_change>0; val=0; leaf=false;
        }
    }

    public String solve(Scanner scanner) {
        int m=scanner.nextInt(), v=scanner.nextInt();
        Tree[] trees=new Tree[m];
        for (int i=0;i<(m-1)/2;i++)
            trees[i]=new Tree(scanner.nextInt(), scanner.nextInt());
        for (int i=(m-1)/2;i<m;i++)
            trees[i]=new Tree(scanner.nextInt());
        int ans=dfs(trees, 0, v);
        return ans>=Integer.MAX_VALUE/10?"IMPOSSIBLE":String.valueOf(ans);
    }

    private int dfs(Tree[] trees, int index, int val) {
        if (trees[index].leaf)
            return trees[index].val==val?0:Integer.MAX_VALUE/10;
        int min=Integer.MAX_VALUE;
        int leftZero=dfs(trees, 2*index+1, 0), leftOne=dfs(trees, 2*index+1, 1),
                rightZero=dfs(trees, 2*index+2, 0), rightOne=dfs(trees, 2*index+2, 1);

        int ans=Integer.MAX_VALUE/10;
        if (val==0) {
            // OR
            if (trees[index].operator==0) {
                ans=Math.min(ans, leftZero+rightZero);
                // change to AND
                if (trees[index].change) {
                    ans=Math.min(ans, 1+Math.min(leftZero, rightZero));
                }
            }
            // AND
            else {
                ans=Math.min(ans, Math.min(leftZero, rightZero));
                // no need for change to OR
            }
        }
        else {
            // OR
            if (trees[index].operator==0) {
                ans=Math.min(ans, Math.min(leftOne, rightOne));
                // no need for change to AND
            }
            // AND
            else {
                ans=Math.min(ans, leftOne+rightOne);
                // change to OR
                if (trees[index].change) {
                    ans=Math.min(ans, 1+Math.min(leftOne, rightOne));
                }
            }
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