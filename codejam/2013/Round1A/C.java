import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2013 Round 1A
 * Problem C. Good Luck
 */
public class Main {

    private String solve(Scanner scanner) throws Throwable {
        int r=scanner.nextInt(),n=scanner.nextInt(),m=scanner.nextInt(),k=scanner.nextInt();
        ArrayList<String> ans=new ArrayList<>();
        ans.add("");
        while (r-->0) {
            int[] a=new int[k];
            for (int i=0;i<k;i++) a[i]=scanner.nextInt();
            ans.add(get(a));
        }
        return String.join("\n", ans);
    }

    private String get(int[] a) {
        for (int x=2;x<=5;x++) {
            for (int y=2;y<=5;y++) {
                for (int z=2;z<=5;z++) {
                    if (check(x,y,z,a)) {
                        return ""+x+y+z;
                    }
                }
            }
        }
        return "235";
    }

    private boolean check(int x, int y, int z, int[] a) {
        HashSet<Integer> set=new HashSet<>();
        set.add(1);
        set.add(x);set.add(y);set.add(z);
        set.add(x*y);set.add(x*z);set.add(y*z);
        set.add(x*y*z);
        for (int v: a) if (!set.contains(v)) return false;
        return true;
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
            }
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}