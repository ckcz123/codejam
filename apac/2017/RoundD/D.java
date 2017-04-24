import java.io.PrintStream;
import java.util.*;

/**
 * APAC 2017 Round D Problem D: Stretch Rope
 * Check README.md for explanation.
 */
public class Main {

    class SegTree {

        class Node {
            int start, end;
            Node left, right;
            long val;
            Node(int s, int e) {start=s;end=e;}
        }

        long[] val;
        int n;
        Node root;
        public SegTree(long[] v) {
            val=v;
            n=v.length;
            root=buildTree(0, n-1);
        }

        private Node buildTree(int start, int end) {
            Node node=new Node(start, end);
            if (start==end) {
                node.val=val[start];
            }
            else {
                int mid=(start+end)/2;
                node.left=buildTree(start, mid);
                node.right=buildTree(mid+1, end);
                node.val=Math.min(node.left.val, node.right.val);
            }
            return node;
        }

        public long query(int start, int end) {
            if (end<start) return Long.MAX_VALUE/100;
            return query(root, start, end);
        }

        private long query(Node node, int start, int end) {
            if (end<start) return Long.MAX_VALUE/100;
            if (node.start==start && node.end==end) return node.val;
            int mid=(node.start+node.end)/2;
            return Math.min(query(node.left, Math.max(node.start, start), Math.min(mid, end)),
                    query(node.right, Math.max(mid+1, start), Math.min(node.end, end)));
        }
    }

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        long m=scanner.nextLong();
        int l=scanner.nextInt();

        long[] ans=new long[l+1];
        Arrays.fill(ans, Long.MAX_VALUE/100);
        ans[0]=0;
        for (int i=0;i<n;i++) {
            int a=scanner.nextInt(), b=scanner.nextInt(), p=scanner.nextInt();
            SegTree segTree=new SegTree(ans);
            long[] nxt=new long[l+1];
            Arrays.fill(nxt, Long.MAX_VALUE/100);
            for (int j=0;j<=l;j++)
                nxt[j]=Math.min(ans[j], segTree.query(Math.max(0, j-b), j-a)+p);
            ans=nxt;
        }
        return ans[l]<=m?String.valueOf(ans[l]):"IMPOSSIBLE";
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
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.));
    }
}
