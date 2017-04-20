import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), r=scanner.nextInt(), p=scanner.nextInt(), s=scanner.nextInt();
        String string=solve(n, r, p, s);
        if ("IMPOSSIBLE".equals(string)) return "IMPOSSIBLE";
        StringBuilder builder=new StringBuilder(string);
        for (int x=1;x<1<<(n);x*=2) {
            for (int i=0;i<(1<<n);i+=2*x) {
                if (builder.substring(i, i+x).compareTo(builder.substring(i+x, i+2*x))>0) {
                    String s1=builder.substring(i, i+x);
                    builder.replace(i, i+x, builder.substring(i+x, i+2*x));
                    builder.replace(i+x, i+2*x, s1);
                }
            }
        }
        return builder.toString();
    }

    private String solve(int n, int r, int p, int s) {
        if (n==0) return r>0?"R":p>0?"P":"S";
        if (n==1) {
            if (r>=2 || p>=2 || s>=2) return "IMPOSSIBLE";
            return r==0?"PS":p==0?"RS":"PR";
        }
        int sum=1<<(n-2), pprs=p-sum, prrs=r-sum, prss=s-sum;
        if (pprs<0 || prrs<0 || prss<0) return "IMPOSSIBLE";
        int np=prrs, nr=prss, ns=pprs;
        String string=solve(n-2, nr, np, ns);
        if ("IMPOSSIBLE".equals(string)) return "IMPOSSIBLE";
        string=string.replace('P','0').replace('R','1')
                .replace('S','2');
        string=string.replace("0","PRRS").replace("1", "PSRS")
                .replace("2", "PRPS");
        return string;
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