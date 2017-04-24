import java.io.PrintStream;
import java.util.*;

/**
 * APAC 2017 Round A Problem A: Country Leader
 * Check README.md for explanation.
 */
public class Main {

    class Node {
        String s;
        int different;
        public Node(String _s) {
            s=_s;
            boolean[] chars=new boolean[26];
            different=0;
            for (char c: s.toCharArray()) {
                if (c!=' ' && !chars[c-'A']) {
                    different++;
                    chars[c-'A']=true;
                }
            }
        }
    }

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(); scanner.nextLine();
        PriorityQueue<Node> queue=new PriorityQueue<>(
                (o1,o2)->o1.different==o2.different?o1.s.compareTo(o2.s):o2.different-o1.different);
        for (int i=0;i<n;i++) {
            queue.offer(new Node(scanner.nextLine()));
        }
        return queue.peek().s;
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("E:\\desktop\\output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=scanner.nextInt();
        for (int t=1;t<=times;t++) {
            System.out.println(String.format("Case #%d: %s", t, new Main().solve(scanner)));
        }

    }

}