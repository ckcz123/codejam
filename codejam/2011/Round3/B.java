import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2011 Round 3
 * Problem B. Dire Straights
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        int[] nums=new int[n];
        for (int i=0;i<n;i++) nums[i]=scanner.nextInt();
        if (n==0) return "0";
        Arrays.sort(nums);
        HashMap<Integer, PriorityQueue<Integer>> map=new HashMap<>();
        for (int x: nums) {
            int u=0;
            if (map.containsKey(x-1)) {
                PriorityQueue<Integer> queue=map.get(x-1);
                if (!queue.isEmpty())
                    u=queue.poll();
                map.put(x-1, queue);
            }
            PriorityQueue<Integer> curr=map.getOrDefault(x, new PriorityQueue<>());
            curr.offer(u+1);
            map.put(x, curr);
        }
        int mn=n;
        for (PriorityQueue<Integer> queue: map.values()) {
            mn=Math.min(mn, queue.isEmpty()?mn:queue.peek());
        }
        return String.valueOf(mn);
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=scanner.nextInt();
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