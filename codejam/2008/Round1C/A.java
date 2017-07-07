import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 Round 1C
 * Problem A. Text Messaging Outrage
 */
public class Main {

    public String solve(Scanner scanner) {
        int p=scanner.nextInt(), k=scanner.nextInt(), l=scanner.nextInt();
        int[] a=new int[l];
        for (int i=0;i<l;i++) a[i]=scanner.nextInt();
        Arrays.sort(a);
        PriorityQueue<Integer> queue=new PriorityQueue<>();
        for (int i=0;i<k;i++) queue.offer(0);
        long ans=0;
        for (int i=l-1;i>=0;i--) {
            int q=queue.poll()+1;
            ans+=q*a[i];
            queue.offer(q);
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