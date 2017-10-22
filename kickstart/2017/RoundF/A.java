import java.io.PrintStream;
import java.util.*;

/**
 * KickStart 2017 Round F
 * Problem A. Kicksort
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        ArrayList<Integer> list=new ArrayList<>();
        for (int i=0;i<n;i++) list.add(scanner.nextInt());
        int start=1, end=n;
        while (!list.isEmpty()) {
            int index=(list.size()-1)/2, v=list.get(index);
            if (v!=start && v!=end) return "NO";
            if (v==start) start++;
            if (v==end) end--;
            list.remove(index);
        }
        return "YES";

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