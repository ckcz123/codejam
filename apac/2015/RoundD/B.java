import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        int[][] buses=new int[n][];
        for (int i=0;i<n;i++) buses[i]=new int[] {scanner.nextInt(), scanner.nextInt()};
        int q=scanner.nextInt();
        ArrayList<String> ans=new ArrayList<>();
        while (q-->0) {
            int x=scanner.nextInt(), cnt=0;
            for (int[] bus: buses) {
                if (bus[0]<=x && bus[1]>=x) cnt++;
            }
            ans.add(String.valueOf(cnt));
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