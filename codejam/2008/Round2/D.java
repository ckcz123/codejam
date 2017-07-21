import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 Round 2
 * Problem D. PermRLE
 * Only small solved.
 */
public class Main {

    public String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int n=scanner.nextInt();
        ArrayList<ArrayList<Integer>> lists=getPermutation(n, new boolean[n], new ArrayList<>(), new ArrayList<>());
        char[] chars=scanner.next().toCharArray();

        int min=Integer.MAX_VALUE;
        for (ArrayList<Integer> list: lists) {
            char[] tmp=new char[chars.length];
            for (int i=0;i<tmp.length;i+=n) {
                for (int j=0;j<n;j++) {
                    tmp[i+j]=chars[i+list.get(j)];
                }
            }
            int val=0;
            char last=0;
            for (char c: tmp) {
                if (c!=last) {
                    val++;
                    last=c;
                }
            }
            min=Math.min(min, val);
        }
        return String.valueOf(min);
    }

    private ArrayList<ArrayList<Integer>> getPermutation(int n, boolean[] used,
                                                         ArrayList<Integer> current, ArrayList<ArrayList<Integer>> ans) {
        if (current.size()==n) {
            ans.add(current);
            return ans;
        }
        for (int i=0;i<n;i++) {
            if (used[i]) continue;
            used[i]=true;
            ArrayList<Integer> next=new ArrayList<>(current);
            next.add(i);
            getPermutation(n, used, next, ans);
            used[i]=false;
        }
        return ans;
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