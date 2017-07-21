import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 APAC Semifinal
 * Problem A. What are Birds?
 */
public class Main {

    public String solve(Scanner scanner) throws Exception {
        int n=scanner.nextInt();
        int al=Integer.MAX_VALUE, ah=0, bl=Integer.MAX_VALUE, bh=0;
        ArrayList<int[]> list=new ArrayList<>();
        while (n-->0) {
            int x=scanner.nextInt(), y=scanner.nextInt();
            String s=scanner.next();
            if ("BIRD".equals(s)) {
                al=Math.min(al, x); ah=Math.max(ah, x);
                bl=Math.min(bl ,y); bh=Math.max(bh, y);
            }
            else {
                scanner.next();
                list.add(new int[] {x,y});
            }
        }
        ArrayList<String> ans=new ArrayList<>();
        ans.add("");
        int m=scanner.nextInt();
        while (m-->0)
            ans.add(test(al,ah,bl,bh,list,scanner.nextInt(),scanner.nextInt()));
        return String.join("\n", ans);
    }

    private String test(int al, int ah, int bl, int bh, ArrayList<int[]> list, int x, int y) {
        if (x>=al && x<=ah && y>=bl && y<=bh) return "BIRD";

        // consider it's a bird: test if valid
        al=Math.min(al, x); ah=Math.max(ah, x);
        bl=Math.min(bl ,y); bh=Math.max(bh, y);
        for (int[] v: list)
            if (v[0]>=al && v[0]<=ah && v[1]>=bl && v[1]<=bh)
                return "NOT BIRD";
        return "UNKNOWN";
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