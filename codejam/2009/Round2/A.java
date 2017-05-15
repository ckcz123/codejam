import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2009 Round 2
 * Problem A. Crazy Rows
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        int[] data=new int[n];
        for (int i=0;i<n;i++) {
            String s=scanner.next();
            for (int j=n-1;j>=0;j--)  {
                if (s.charAt(j)=='1') {
                    data[i]=j+1;
                    break;
                }
            }
        }
        ArrayList<Integer> list=new ArrayList<>(
                Arrays.stream(data).boxed().collect(Collectors.toList())
        );
        int ans=0;
        for (int i=1;i<=n;i++) {
            for (int j=0;j<list.size();j++) {
                if (list.get(j)<=i) {
                    ans+=j;
                    list.remove(j);
                    break;
                }
            }
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        long times=scanner.nextInt();
        long start=System.currentTimeMillis();
        for (long t=1;t<=times;t++) {
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



