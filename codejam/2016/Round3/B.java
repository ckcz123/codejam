import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        ArrayList<Integer>[] sons=new ArrayList[n+1];
        for (int i=0;i<=n;i++) sons[i]=new ArrayList<>();
        for (int i=1;i<=n;i++) sons[scanner.nextInt()].add(i);
        int[] ans=new int[n+1];
        for (int i=0;i<=n;i++) cal(sons, ans, i);
        char[] chars=scanner.next().toCharArray();
        int m=scanner.nextInt();
        int[] cnt=new int[m];
        String[] strings=new String[m];
        for (int i=0;i<m;i++) strings[i]=scanner.next();
        final int LOOP_COUNT = 100000;
        for (int i=0;i<LOOP_COUNT;i++) {
            List<Integer> list=generate(n, sons, ans);
            String s=String.join("",
                    list.stream().map(x->String.valueOf(chars[x-1])).collect(Collectors.toList()));
            for (int j=0;j<m;j++) {
                if (s.contains(strings[j])) cnt[j]++;
            }
        }
        return String.join(" ", Arrays.stream(cnt)
                .boxed().map(x->String.format("%.9f", x/(LOOP_COUNT+.0))).collect(Collectors.toList()));
    }

    private int cal(ArrayList<Integer>[] sons, int[] ans, int k) {
        if (ans[k]!=0) return ans[k];
        ans[k]=1;
        for (int v: sons[k])
            ans[k]+=cal(sons, ans, v);
        return ans[k];
    }

    private List<Integer> generate(int n, ArrayList<Integer>[] sons, int[] cnt) {
        ArrayList<Integer> total=new ArrayList<>();
        for (int v: sons[0]) {
            for (int k=0;k<cnt[v];k++) total.add(v);
        }
        ArrayList<Integer> ans=new ArrayList<>();
        Random random=new Random();
        while (ans.size()<n) {
            int v=total.get(random.nextInt(total.size()));
            ans.add(v);
            while (total.remove(Integer.valueOf(v)));
            for (int k: sons[v]) {
                for (int x=0;x<cnt[k];x++)
                    total.add(k);
            }
        }
        return ans;
    }


    public static void main(String[] args) throws Exception {
//        System.setOut(new PrintStream("output.txt"));
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