import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), l=scanner.nextInt();
        ArrayList<String> origin=new ArrayList<>(), target=new ArrayList<>();
        for (int i=0;i<n;i++) origin.add(scanner.next());
        for (int i=0;i<n;i++) target.add(scanner.next());
        Collections.sort(target);
        int val=dfs(origin, target, l, 0);
        if (val>=1000) return "NOT POSSIBLE";
        return String.valueOf(val);
    }

    private int dfs(ArrayList<String> origin, ArrayList<String> target, int l, int k) {
        if (k==l) return 0;

        ArrayList<String> reorigin=reverse(origin, k);
        Collections.sort(origin);
        Collections.sort(reorigin);

        int v=10000;
        if (valid(origin, target, k))
            v=Math.min(v, dfs(origin, target, l, k+1));
        if (valid(reorigin, target, k))
            v=Math.min(v, 1+dfs(reorigin, target, l, k+1));

        return v;
    }

    private boolean valid(ArrayList<String> origin, ArrayList<String> target, int k) {
        ArrayList<String> subOrigin=new ArrayList<>(origin.stream()
                .map(s->s.substring(0, k+1)).collect(Collectors.toList())),
                subTarget=new ArrayList<>(target.stream()
                        .map(s->s.substring(0, k+1)).collect(Collectors.toList()));
        Collections.sort(subOrigin);
        Collections.sort(subTarget);
        return subOrigin.equals(subTarget);
    }

    private ArrayList<String> reverse(ArrayList<String> origin, int k) {
        return new ArrayList<>(origin.stream().map(s->{
            StringBuilder builder=new StringBuilder(s);
            builder.setCharAt(k, (char)('1'+'0'-builder.charAt(k)));
            return builder.toString();
        }).collect(Collectors.toList()));
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