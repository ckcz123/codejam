import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private String solve(Scanner scanner) {
        HashSet<String> set=new HashSet<>();
        int np=scanner.nextInt(), ne=scanner.nextInt(), nt=scanner.nextInt();
        int[] p=new int[np]; for (int i=0;i<np;i++) p[i]=scanner.nextInt();
        int[] e=new int[ne]; for (int i=0;i<ne;i++) e[i]=scanner.nextInt();
        int[] t=new int[nt]; for (int i=0;i<nt;i++) t[i]=scanner.nextInt();
        for (int i=0;i<ne;i++) {
            for (int j=0;j<ne;j++) {
                if (i!=j) set.add(build(e[i], e[j]));
            }
        }
        int m=scanner.nextInt();
        ArrayList<String> ans=new ArrayList<>();
        ans.add("");
        while (m-->0) {
            long u=scanner.nextLong(), v=scanner.nextLong();
            boolean able=false;
            for (int x: p) {
                for (int y: t) {
                    if (set.contains(build(u*y, v*x)))
                        able=true;
                    if (able) break;
                }
                if (able) break;
            }
            ans.add(able?"Yes":"No");
        }
        return String.join("\n", ans);
    }

    private String build(long x, long y) {
        long g=gcd(x, y);
        x/=g;y/=g;
        return x+","+y;
    }

    private long gcd(long x, long y) {
        return y==0?x:gcd(y, x%y);
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