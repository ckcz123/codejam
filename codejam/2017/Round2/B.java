import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2017 Round 2
 * Problem B. Roller Coaster Scheduling
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), c=scanner.nextInt(), m=scanner.nextInt();
        ArrayList<Integer>[] lists=new ArrayList[n];
        for (int i=0;i<n;i++) lists[i]=new ArrayList<>();
        for (int i=0;i<m;i++)
            lists[scanner.nextInt()-1].add(scanner.nextInt()-1);
        int start=1, end=m;
        while (start<end) {
            int mid=(start+end)/2;
            if (cal(n, c, lists, mid)!=-1) end=mid;
            else start=mid+1;
        }
        return start+" "+cal(n, c, lists, start);
    }

    private int cal(int n, int c, ArrayList<Integer>[] lists, int candidate) {
        int[] available=new int[c];
        for (int i=0;i<c;i++) available[i]=candidate;
        int promote=0;
        Queue<Integer> users=new LinkedList<>();
        for (int i=n-1;i>=0;i--) {
            int tickets=candidate;
            for (int user: lists[i]) {
                if (available[user]>0 && tickets>0) {
                    available[user]--; tickets--;
                }
                else users.offer(user);
            }
            while (tickets>0 && !users.isEmpty()) {
                int user=users.poll();
                if (available[user]==0) return -1;
                promote++;
                tickets--;
                available[user]--;
            }
        }
        if (!users.isEmpty()) return -1;
        return promote;
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