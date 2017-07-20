import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 AMER Semifinal
 * Problem A. Mixing Bowls
 */
public class Main {

    public String solve(Scanner scanner) {
        HashMap<String, ArrayList<String>> map=new HashMap<>();
        int n=scanner.nextInt();
        String root=null;
        while (n-->0) {
            String name=scanner.next();
            if (root==null) root=name;
            ArrayList<String> list=new ArrayList<>();
            int m=scanner.nextInt();
            while (m-->0) list.add(scanner.next());
            map.put(name, list);
        }
        return String.valueOf(solve(map, root));
    }

    private int solve(HashMap<String, ArrayList<String>> map, String name) {
        if (!map.containsKey(name)) return 0;
        ArrayList<String> list=map.get(name);
        ArrayList<Integer> need=new ArrayList<>();
        for (String s: list)
            need.add(solve(map, s));
        need.sort((o1, o2)->o2-o1);
        int ans=0, left=0;
        for (int v: need) {
            if (v==0) continue;
            if (left<v) {
                ans+=(v-left);
                left=v;
            }
            left--;
        }
        if (left==0) ans++;
        return ans;
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