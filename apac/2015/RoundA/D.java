import java.io.PrintStream;
import java.util.*;

public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt(), m=scanner.nextInt();
        int[] size=new int[32];
        for (int i=0;i<n;i++)
            size[scanner.nextInt()]++;
        int matched=0, ans=0;
        while (matched<n) {
            ans++;
            matched+=find(size, m, m);
        }
        return String.valueOf(ans);
    }

    private int find(int[] size, int a, int b) {
        if (a<b) {int tmp=a;a=b;b=tmp;}
        int index=31;
        for (;index>=0;index--) {
            if (b<(1<<index) || size[index]==0) continue;
            break;
        }
        if (index==-1) return 0;
        size[index]--;
        int edge=(1<<index);
        return 1+find(size, a-edge, b)+find(size, edge, b-edge);
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
