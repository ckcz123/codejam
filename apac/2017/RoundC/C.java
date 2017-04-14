import java.io.PrintStream;
import java.util.*;

public class Main {

    public String solve(Scanner scanner) {
        int m=scanner.nextInt(), n=0;
        ArrayList<HashSet<Integer>> arrayLists=new ArrayList<>();
        HashMap<String, Integer> map=new HashMap<>();
        Queue<Integer> set=new LinkedList<>();
        ArrayList<Integer> cnt=new ArrayList<>();
        while (m-->0) {
            String s=scanner.next();
            String name=s.substring(0, s.indexOf('='));
            if (!map.containsKey(name)) {
                map.put(name, n++);
                arrayLists.add(new HashSet<>());
                cnt.add(0);
            }
            int y=map.get(name);
            String parameters=s.substring(s.indexOf('(')+1, s.indexOf(')'));
            if ("".equals(parameters)) set.offer(y);
            else {
                String[] strings=parameters.split(",");
                for (String p: strings) {
                    if (!map.containsKey(p)) {
                        map.put(p, n++);
                        arrayLists.add(new HashSet<>());
                        cnt.add(0);
                    }
                    HashSet<Integer> list=arrayLists.get(map.get(p));
                    if (!list.contains(y)) {
                        list.add(y);
                        cnt.set(y, cnt.get(y)+1);
                    }
                }
            }
        }
        boolean[] visited=new boolean[n];
        while (!set.isEmpty()) {
            int y=set.poll();
            visited[y]=true;
            HashSet<Integer> linked=arrayLists.get(y);
            for (int x: linked) {
                if (!visited[x]) {
                    cnt.set(x, cnt.get(x)-1);
                    if (cnt.get(x)==0) set.offer(x);
                }
            }
        }
        for (boolean b: visited)
            if (!b) return "BAD";
        return "GOOD";
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
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.));
    }
}
