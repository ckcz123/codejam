import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    class PanCake {
        long r, h;
        public PanCake(long _r, long _h) {r=_r;h=_h;}
        public long getArea() {return r*h;}
    }

    public String solve(Scanner scanner) {
        int n=scanner.nextInt(), k=scanner.nextInt();
        PanCake[] panCakes=new PanCake[n];
        for (int i=0;i<n;i++) {
            panCakes[i]=new PanCake(scanner.nextLong(), scanner.nextLong());
        }
        long ans=0;
        for (int i=0;i<n;i++) {
            ArrayList<PanCake> list=new ArrayList<>();
            long r=panCakes[i].r, sum=r*r+2*panCakes[i].getArea();
            for (int j=0;j<n;j++) {
                if (i!=j && panCakes[j].r<=r) list.add(new PanCake(panCakes[j].r, panCakes[j].h));
            }
            if (list.size()<k-1) continue;
            list.sort(Comparator.comparingLong(p->-p.getArea()));
            for (int j=0;j<k-1;j++)
                sum+=2*list.get(j).getArea();
            if (ans<sum) ans=sum;
        }
        return String.format("%.9f", Math.PI*ans);
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