import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        int k=scanner.nextInt(), c=scanner.nextInt(), s=scanner.nextInt();
        if (c*s<k) return "IMPOSSIBLE";
        ArrayList<String> ans=new ArrayList<>();
        for (int curr=1;curr<=k;curr+=c) {
            long p=1;
            for (int j=0;j<c;j++) {
                p=(p-1)*k+Math.min(curr+j, k);
            }
            ans.add(String.valueOf(p));
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
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}