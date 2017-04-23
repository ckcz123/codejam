import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int m=scanner.nextInt(), n=scanner.nextInt();
        String[] strings=new String[m];
        for (int i=0;i<m;i++) strings[i]=scanner.next();
        HashSet<String>[] sets=new HashSet[n];
        for (int i=0;i<n;i++) sets[i]=new HashSet<>();
        HashMap<Integer, Long> map=new HashMap<>();
        dfs(strings, m, sets, n, 0, map);
        ArrayList<Map.Entry<Integer, Long>> arrayList=new ArrayList<>(map.entrySet());
        arrayList.sort(Comparator.comparingInt(Map.Entry::getKey));
        Map.Entry<Integer, Long> entry=arrayList.get(arrayList.size()-1);
        return entry.getKey()+" "+entry.getValue()%1000000007;
    }

    private void dfs(String[] strings, int m, HashSet<String>[] sets,
                     int n, int curr, HashMap<Integer, Long> map) {
        if (curr==m) {
            int cnt=0;
            for (HashSet<String> set: sets) {
                if (set.isEmpty()) return;
                cnt+=cal(set);
            }
            map.put(cnt, map.getOrDefault(cnt, 0L)+1L);
            return;
        }
        for (int i=0;i<n;i++) {
            sets[i].add(strings[curr]);
            dfs(strings, m, sets, n, curr+1, map);
            sets[i].remove(strings[curr]);
        }
    }

    private int cal(HashSet<String> strings) {
        HashSet<String> set=new HashSet<>();
        set.add("");
        for (String s: strings) {
            for (int i=1;i<=s.length();i++)
                set.add(s.substring(0, i));
        }
        return set.size();
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