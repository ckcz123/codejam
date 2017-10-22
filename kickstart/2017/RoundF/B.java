import java.io.PrintStream;
import java.util.*;

/**
 * KickStart 2017 Round F
 * Problem B. Dance Battle
 */
public class Main {

    public String solve(Scanner scanner) {
        int e=scanner.nextInt(), n=scanner.nextInt();
        LinkedList<Integer> list=new LinkedList<>();
        for (int i=0;i<n;i++) list.offer(scanner.nextInt());
        Collections.sort(list);
        int ans=0;
        while (!list.isEmpty()) {
            if (e>list.peekFirst()) {
                e-=list.pollFirst(); ans++;
            }
            else if (ans>0 && list.size()>1) {
                e+=list.pollLast()-list.pollFirst();
            }
            else break;
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