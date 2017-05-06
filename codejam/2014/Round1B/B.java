import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2014 Round 1B
 * Problem B. New Lottery Game
 */
public class Main {

    private String solve(Scanner scanner) {
        long a=scanner.nextLong(), b=scanner.nextLong(), k=scanner.nextLong();
        return String.valueOf(solve(a,b,k,new HashMap<>()));
    }

    private long solve(long a, long b, long k, HashMap<String, Long> map)  {
        if (map.containsKey(a+","+b+","+k)) return map.get(a+","+b+","+k);
        if (a==0 || b==0 || k==0) return 0;
        if (a==1 && b==1) return 1;
        long ans=solve(a/2, b/2, k/2, map)
                +solve((a+1)/2, b/2, (k+1)/2, map)
                +solve(a/2, (b+1)/2, (k+1)/2, map)
                +solve((a+1)/2, (b+1)/2, (k+1)/2, map);
        map.put(a+","+b+","+k, ans);
        return ans;
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