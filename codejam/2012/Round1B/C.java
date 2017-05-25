import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2012 Round 1B
 * Problem C. Equal Sums
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        long[] val=new long[n];
        for (int i=0;i<n;i++) val[i]=scanner.nextLong();
        Arrays.sort(val);

        HashMap<Long, String> map=new HashMap<>();
        Random random=new Random();
        for (int i=0;i<30000000;i++) {
            long[] next=generate(random, n, val);
            String s=String.join(" ",
                    Arrays.stream(next).boxed().map(String::valueOf).collect(Collectors.toList()));
            long sum=Arrays.stream(next).sum();
            if (!s.equals(map.getOrDefault(sum, s)))
                return "\n"+s+"\n"+map.get(sum);
            map.put(sum, s);
        }
        return "Impossible";
    }

    private long[] generate(Random random, int n, long[] val) {
        while (true) {
            long[] x=new long[6];
            for (int i=0;i<6;i++) x[i]=val[random.nextInt(n)];
            Arrays.sort(x);
            boolean able=true;
            for (int i=0;i<6;i++)
                for (int j=i+1;j<6;j++)
                    if (x[i]==x[j]) {
                        able=false;
                        break;
                    }
            if (able) return x;
        }
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