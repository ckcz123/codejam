import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public String solve(Scanner scanner) {
        HashMap<String, Integer> map=new HashMap<>();
        ArrayList<String> cities=new ArrayList<>();
        int m=scanner.nextInt(), n=0;
        int[][] tickets=new int[m][2];
        for (int i=0;i<m;i++) {
            String from=scanner.next(), to=scanner.next();
            if (!map.containsKey(from)) {
                map.put(from, n++);
                cities.add(from);
            }
            if (!map.containsKey(to)) {
                map.put(to, n++);
                cities.add(to);
            }
            tickets[i][0]=map.get(from);
            tickets[i][1]=map.get(to);
        }
        LinkedList<Integer>[] lists=new LinkedList[n];
        for (int i=0;i<n;i++) lists[i]=new LinkedList<>();
        int[] cnt=new int[n];
        for (int i=0;i<m;i++) {
            int x=tickets[i][0], y=tickets[i][1];
            lists[x].add(y);
            cnt[y]++;
        }
        // find start
        int curr=0;
        for (int i=0;i<n;i++) {
            if (lists[i].size()>cnt[i]) {
                curr=i;break;
            }
        }
        ArrayList<String> ans=new ArrayList<>();
        while (ans.size()<m) {
            int to=lists[curr].peekFirst();
            ans.add(cities.get(curr)+"-"+cities.get(to));
            curr=to;
        }
        return String.join(" ", ans);
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