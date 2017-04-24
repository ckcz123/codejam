import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * APAC 2016 Round B Problem C: gNumbers
 * Check README.md for explanation.
 */
public class Main {

    private String solve(Scanner scanner) {
        long n=scanner.nextLong(), val=n;
        ArrayList<Long> arrayList=new ArrayList<>();
        for (long p=2;p<=Math.sqrt(n);p+=(p==2?1:2)) {
            if (n%p==0) {
                long v=1;
                while (n%p==0) {
                    n/=p;
                    v*=p;
                }
                arrayList.add(v);
            }
        }
        if (n!=1) arrayList.add(n);
        return win(val, arrayList, new HashMap<>())?"Laurence":"Seymour";
    }

    private boolean win(long n, ArrayList<Long> list, HashMap<Long, Boolean> map) {
        if (map.containsKey(n)) return map.get(n);
        if (isGNumber(n)) {
            map.put(n, false);
            return false;
        }
        for (long v: list) {
            if (n%v==0 && !win(n/v, list, map)) {
                map.put(n, true);
                return true;
            }
        }
        map.put(n, false);
        return false;
    }

    private boolean isGNumber(long x) {
        char[] chars=String.valueOf(x).toCharArray();
        int sum=0;
        for (char c: chars) sum+=c-'0';
        for (int i=2;i<=Math.sqrt(sum);i++)
            if (sum%i==0) return false;
        return true;
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