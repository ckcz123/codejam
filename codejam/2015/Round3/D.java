import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2015 Round 3
 * Problem D. Log Set
 * Only small solved.
 */
public class Main {

    private String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int p=scanner.nextInt();
        PriorityQueue<Integer> priorityQueue=new PriorityQueue<>();
        int[] e=new int[p];
        for (int i=0;i<p;i++)
            e[i]=scanner.nextInt();
        for (int i=0;i<p;i++) {
            int f=scanner.nextInt();
            for (int j=1;j<=f;j++) priorityQueue.offer(e[i]);
        }
        priorityQueue.poll();

        PriorityQueue<Integer> ans=new PriorityQueue<>();
        while (!priorityQueue.isEmpty()) {
            int v=priorityQueue.poll();
            ans.add(v);
            PriorityQueue<Integer> nq=new PriorityQueue<>();
            while (!priorityQueue.isEmpty()) {
                int u=priorityQueue.poll();
                priorityQueue.remove(u+v);
                nq.offer(u);
            }
            priorityQueue=nq;
        }
        return String.join(" ", ans.stream().map(String::valueOf).collect(Collectors.toList()));
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("E:\\desktop\\output.txt"));
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