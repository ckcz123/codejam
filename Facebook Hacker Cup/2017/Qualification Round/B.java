import java.io.*;
import java.util.*;

/**
 * Facebook Hacker Cup 2017 Qualification Round
 * Problem B. Lazy Loading
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt(), cnt=0;
        LinkedList<Integer> list=new LinkedList<>();
        for (int i=0;i<n;i++) list.add(scanner.nextInt());
        Collections.sort(list);
        while (!list.isEmpty()) {
            if (list.size()*list.peekLast()>=50) {
                int val=list.pollLast(), x=1;
                while (val*x<50) {
                    x++; list.pollFirst();
                }
                cnt++;
            }
            else break;
        }
        return String.valueOf(cnt);

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
