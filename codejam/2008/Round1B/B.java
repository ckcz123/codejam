import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 Round 1B
 * Problem B. Number Sets
 */
public class Main {

    class UnionFind {
        long a, b;
        int delta;
        int[] father;
        public UnionFind(long _a, long _b) {
            a=_a;b=_b;
            delta=(int)(b-a+1);
            father=new int[delta];
            for (int i=0;i<delta;i++)
                father[i]=i;
        }
        private int findFather(int x) {
            return father[x]==x?x:(father[x]=findFather(father[x]));
        }
        public boolean isFather(long x) {
            return findFather((int)(x-a))+a==x;
        }
        public void link(long x, long y) {
            father[findFather((int)(x-a))]=findFather((int)(y-a));
        }
    }

    public String solve(Scanner scanner) {
        init();
        long a=scanner.nextLong(), b=scanner.nextLong();
        int p=scanner.nextInt(), cnt=0;
        UnionFind unionFind=new UnionFind(a,b);
        for (int x: primes) {
            if (x<p) continue;
            ArrayList<Long> list=new ArrayList<>();
            for (long start=a/x*x;start<=b;start+=x)
                if (start>=a)
                    list.add(start);
            for (Long v : list)
                unionFind.link(v, list.get(0));
        }
        for (long i=a;i<=b;i++)
            if (unionFind.isFather(i))
                cnt++;
        return String.valueOf(cnt);
    }

    private static ArrayList<Integer> primes;
    private void init() {
        if (primes!=null) return;
        primes=new ArrayList<>();
        primes.add(2); primes.add(3);
        for (int i=5;i<1000000;i+=2) {
            boolean isPrime=true;
            for (int v: primes) {
                if ((long)v*(long)v>(long)i) break;
                if (i%v==0) {
                    isPrime=false;break;
                }
            }
            if (isPrime) primes.add(i);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=Integer.parseInt(scanner.nextLine());
        long start=System.currentTimeMillis();
        for (int t=1;t<=times;t++) {
            try {
                System.out.println(String.format("Case #%d: %s", t, new Main().solve(scanner)));
            }
            catch (Throwable e) {
                System.err.println("ERROR in case #"+t);
                e.printStackTrace();
            }
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}