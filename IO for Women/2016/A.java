import java.io.PrintStream;
import java.util.*;

/**
 * Codejam to I/O for Women 2016 Problem A: Cody's Jams
 * Check README.md for explanation.
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        PriorityQueue<Integer> priorityQueue=new PriorityQueue<>();
        for (int i=0;i<2*n;i++) priorityQueue.offer(scanner.nextInt());
        ArrayList<String> ans=new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            int v=priorityQueue.poll();
            priorityQueue.remove(v/3*4);
            ans.add(String.valueOf(v));
        }
        return String.join(" ", ans);
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