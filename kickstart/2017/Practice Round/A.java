import java.io.PrintStream;
import java.util.*;

public class Main {

    class Node {
        String s;
        int num;
        public Node(String _s) {
            s=_s;
            boolean[] cnt=new boolean[26];
            for (char c: s.toCharArray())
                if (c!=' ')
                    cnt[c-'A']=true;
            num=0;
            for (boolean b: cnt)
                if (b)
                    num++;
        }
    }

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        scanner.nextLine();
        Node[] nodes=new Node[n];
        for (int i=0;i<n;i++) {
            nodes[i]=new Node(scanner.nextLine());
        }
        Arrays.sort(nodes, (o1,o2)->o1.num==o2.num?o1.s.compareTo(o2.s):o2.num-o1.num);
        return nodes[0].s;
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        long start=System.currentTimeMillis();
        Scanner scanner=new Scanner(System.in);
        int times=scanner.nextInt();
        for (int t=1;t<=times;t++) {
            System.out.println(String.format("Case #%d: %s", t, new Main().solve(scanner)));
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}