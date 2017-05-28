import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2012 Round 3
 * Problem A. Perfect Game
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        int[] l=new int[n], p=new int[n];
        for (int i=0;i<n;i++) l[i]=scanner.nextInt();
        for (int i=0;i<n;i++) p[i]=scanner.nextInt();
        boolean[] used=new boolean[n];
        ArrayList<String> list=new ArrayList<>();
        while (list.size()<n) {
            int index=-1;
            double v=-1;
            for (int i=0;i<n;i++) {
                if (used[i]) continue;
                double v1=p[i]/(l[i]+.0);
                if (v1>v+1e-9) {
                    v=v1; index=i;
                }
            }
            used[index]=true;
            list.add(String.valueOf(index));
        }
        return String.join(" ", list);
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