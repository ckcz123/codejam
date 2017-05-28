import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2011 Round 1B
 * Problem B. Revenge of the Hot Dogs
 */
public class Main {

    public String solve(Scanner scanner) {
        int c=scanner.nextInt();
        long d=scanner.nextLong()*2;
        ArrayList<Long> list=new ArrayList<>();
        while (c-->0) {
            long p=scanner.nextLong(); int v=scanner.nextInt();
            while (v-->0) list.add(2*p);
        }
        long start=0, end=1L<<60;
        while (end-start>1e-9) {
            long mid=(start+end)/2;
            long can=-(1L<<60);
            boolean able=true;
            for (long v: list) {
                if (can>v+mid) {
                    able=false;break;
                }
                can=Math.max(can, v-mid)+d;
            }
            if (able) end=mid;
            else start=mid+1;
        }
        return start/2+(start%2==0?"":".5");
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=scanner.nextInt();
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