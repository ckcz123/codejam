import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2009 Round 1A
 * Problem A. Multi-base happiness
 */
public class Main {

    private static HashMap<String, Integer> cache=new HashMap<>();
    private static HashMap<Integer, Boolean> baseCache=new HashMap<>();

    public String solve(Scanner scanner) {
        String input=scanner.nextLine();
        if (cache.containsKey(input)) return String.valueOf(cache.get(input));
        int[] bases= Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int x=2;;x++) {
            boolean isBase=true;
            for (int b: bases) {
                // use cache
                if (x<=500000) {
                    if (!baseCache.containsKey(100*x+b))
                        baseCache.put(100*x+b, isBase(b,x,new HashSet<>()));
                    if (!baseCache.get(100*x+b)) {
                        isBase=false;break;
                    }
                }
                // calculate directly
                else {
                    if (!isBase(b,x,new HashSet<>())) {
                        isBase=false;break;
                    }
                }
            }
            if (isBase) {
                cache.put(input, x);
                return String.valueOf(x);
            }
        }
    }

    private boolean isBase(int base, int x, HashSet<Integer> set) {
        while (true) {
            if (x==1) return true;
            if (!set.add(x)) return false;
            int next=0;
            for (char c: Integer.toString(x, base).toCharArray())
                next+=(c-'0')*(c-'0');
            x=next;
        }
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