import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2013 Qualification Round
 * Problem D. Treasure
 */
public class Main {

    private String solve(Scanner scanner) throws Throwable {
        return solveSmall(scanner);
    }

    class Chest {
        int need;
        HashMap<Integer, Integer> map;
        public Chest(int _need) {need=_need;map=new HashMap<>();}
    }

    private String solveSmall(Scanner scanner)  {
        int k=scanner.nextInt(), n=scanner.nextInt();
        HashMap<Integer, Integer> map=new HashMap<>();
        while (k-->0)
            add(map, scanner.nextInt(), 1);
        Chest[] chests=new Chest[n];
        for (int i=0;i<n;i++) {
            chests[i]=new Chest(scanner.nextInt());
            int w=scanner.nextInt();
            while (w-->0)
                add(chests[i].map, scanner.nextInt(), 1);
        }
        LinkedList<String> ans=new LinkedList<>();
        if (dfs(n, chests, map, ans, new HashSet<>(), 0))
            return String.join(" ", ans);
        return "IMPOSSIBLE";
    }

    private boolean dfs(int n, Chest[] chests,
                        HashMap<Integer, Integer> map, LinkedList<String> ans,
                        HashSet<Integer> set, int state) {
        if (ans.size()==n) return true;
        if (set.contains(state)) return false;
        for (int i=0;i<n;i++) {
            if ((state&(1<<i))!=0) continue;
            if (map.getOrDefault(chests[i].need, 0)>0) {
                add(map, chests[i].need, -1);
                ans.offerLast(String.valueOf(i+1));
                for (Map.Entry<Integer, Integer> entry: chests[i].map.entrySet()) {
                    add(map, entry.getKey(), entry.getValue());
                }
                if (dfs(n, chests, map, ans, set, state|(1<<i))) return true;
                for (Map.Entry<Integer, Integer> entry: chests[i].map.entrySet()) {
                    add(map, entry.getKey(), -entry.getValue());
                }
                ans.pollLast();
                add(map, chests[i].need, 1);
            }
        }
        set.add(state);
        return false;
    }

    private void add(HashMap<Integer, Integer> map, int key, int value) {
        int v=map.getOrDefault(key, 0)+value;
        if (v<0) throw new RuntimeException();
        map.put(key, v);
    }

    public static void main(String[] args) throws Exception {
//        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=scanner.nextInt();
        long start=System.currentTimeMillis();
        for (int t=1;t<=times;t++) {
            try {
                System.out.println(String.format("Case #%d: %s", t, new Main().solve(scanner)));
            }
            catch (Throwable e) {
                System.err.println("ERROR in case #"+t);
            }
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}