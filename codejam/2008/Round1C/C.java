import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2008 Round 1C
 * Problem C. Increasing Speed Limits
 */
public class Main {

    class SegmentTree {
        int start, end;
        long val;
        SegmentTree left, right;
        public SegmentTree(int _start, int _end) {
            start=_start;end=_end;
        }
    }

    private SegmentTree makeTree(int start, int end) {
        SegmentTree tree=new SegmentTree(start, end);
        if (start==end) return tree;
        int mid=(start+end)/2;
        tree.left=makeTree(start, mid);
        tree.right=makeTree(mid+1, end);
        return tree;
    }

    private void add(SegmentTree tree, int index, long val) {
        tree.val+=val;
        tree.val%=1000000007L;
        if (tree.start==tree.end) return;
        int mid=(tree.start+tree.end)/2;
        if (index<=mid) add(tree.left, index, val);
        else add(tree.right, index, val);
    }

    private long query(SegmentTree tree, int start, int end) {
        if (tree.start==start && tree.end==end) return tree.val;
        int mid=(tree.start+tree.end)/2;
        if (mid>=end) return query(tree.left, start, end);
        if (start>mid) return query(tree.right, start, end);
        return (query(tree.left, start, mid)+query(tree.right, mid+1, end))%1000000007L;
    }

    public String solve(Scanner scanner) {
        int n=scanner.nextInt(), m=scanner.nextInt();
        long x=scanner.nextLong(), y=scanner.nextLong(), z=scanner.nextLong();
        int[] num=new int[n], a=new int[m];
        for (int i=0;i<m;i++) a[i]=scanner.nextInt();
        for (int i=0;i<n;i++) {
            num[i]=a[i%m];
            a[i%m]=(int)((x*a[i%m]+y*(i+1))%z);
        }
        a=num.clone();
        Arrays.sort(a);
        for (int i=0;i<n;i++)
            num[i]=Arrays.binarySearch(a, num[i])+1;
        long ans=0;
        SegmentTree tree=makeTree(0, n);
        for (int i=0;i<n;i++) {
            long v=query(tree, 0, num[i]-1)+1;
            ans+=v; ans%=1000000007L;
            add(tree, num[i], v);
        }
        return String.valueOf(ans);
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