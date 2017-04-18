import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), x=scanner.nextInt(), k=scanner.nextInt(),
                a=scanner.nextInt(), b=scanner.nextInt(), c=scanner.nextInt();
        HashMap<Integer, Double> map=new HashMap<>();
        map.put(x, 1d);
        for (int i=0;i<n;i++) {
            HashMap<Integer, Double> map1=new HashMap<>();
            for (Map.Entry<Integer, Double> entry: map.entrySet()) {
                add(map1, entry.getKey()&k, entry.getValue()*a/100.);
                add(map1, entry.getKey()|k, entry.getValue()*b/100.);
                add(map1, entry.getKey()^k, entry.getValue()*c/100.);
            }
            map=map1;
        }
        return String.format("%.9f", map.entrySet().stream().mapToDouble(en->en.getKey()*en.getValue()).sum());
    }

    private void add(HashMap<Integer, Double> map, int key, double value) {
        map.put(key, map.getOrDefault(key, 0d)+value);
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