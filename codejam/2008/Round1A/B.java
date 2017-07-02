import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 Round 1A
 * Problem B. Milkshakes
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt(), m=scanner.nextInt();
        int[] one=new int[m];
        Arrays.fill(one, -1);
        HashSet<Integer>[] zero=new HashSet[m];
        for (int i=0;i<m;i++) {
            zero[i]=new HashSet<>();
            int t=scanner.nextInt();
            while (t-->0) {
                int x=scanner.nextInt()-1, y=scanner.nextInt();
                if (y==1) one[i]=x;
                else zero[i].add(x);
            }
        }

        HashSet<Integer> ans=new HashSet<>();
        while (true) {
            HashSet<Integer> add=new HashSet<>();

            for (int i=0;i<m;i++) {
                if (one[i]>=0 && ans.contains(one[i]))
                    continue;
                if (zero[i].isEmpty()) {
                    if (one[i]>=0)
                        add.add(one[i]);
                    else return "IMPOSSIBLE";
                }
            }

            if (add.isEmpty())
                break;
            for (int i=0;i<m;i++)
                zero[i].removeAll(add);
            ans.addAll(add);
        }
        String[] res=new String[n];
        Arrays.fill(res, "0");
        for (int v: ans) res[v]="1";
        return String.join(" ", res);
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