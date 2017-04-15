import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int hd=scanner.nextInt(), ad=scanner.nextInt(), hk=scanner.nextInt(),
                ak=scanner.nextInt(), b=scanner.nextInt(), d=scanner.nextInt();
        int v=dfs(hd, ad, hk, ak, b, d, hd, new HashMap<>());
        return v>1999999998?"IMPOSSIBLE":String.valueOf(v);
    }

    private int dfs(int hd, int ad, int hk, int ak, int b, int d, int origin_hd, HashMap<Integer, Integer> map) {
        int state=build(hd, ad, hk, ak);
        if (map.containsKey(state)) return map.get(state);
        map.put(state, 1999999999);
        if (hd<=0) return 1999999999;
        if (ad>=hk) {
            map.put(state, 1);
            return 1;
        }
        int num=Integer.MAX_VALUE;
        int decrease_ak=ak-d<=0?0:ak-d;
        // attack
        num=Math.min(num, 1+dfs(hd-ak, ad, hk-ad, ak, b, d, origin_hd, map));
        // buff
        num=Math.min(num, 1+dfs(hd-ak, ad+b, hk, ak, b, d, origin_hd, map));
        // cure
        num=Math.min(num, 1+dfs(origin_hd-ak, ad, hk, ak, b, d, origin_hd, map));
        // debuff
        num=Math.min(num, 1+dfs(hd-decrease_ak, ad, hk, decrease_ak, b, d, origin_hd, map));
        map.put(state, num);
        return num;
    }

    private int build(int a, int b, int c, int d) {
        return d+110*c+110*110*b+110*110*110*a;
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