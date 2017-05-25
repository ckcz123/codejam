import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2012 Round 1C
 * Problem A. Diamond Inheritance
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        ArrayList<Integer>[] lists=new ArrayList[n];
        for (int i=0;i<n;i++) lists[i]=new ArrayList<>();
        boolean[][] able=new boolean[n][n];
        for (int i=0;i<n;i++) {
            able[i][i]=true;
            int k=scanner.nextInt();
            while (k-->0) {
                int x=scanner.nextInt()-1;
                lists[i].add(x);
                able[i][x]=true;
            }
        }
        for (int k=0;k<n;k++) {
            for (int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    if (able[i][k] && able[k][j])
                        able[i][j]=true;
                }
            }
        }
        for (int i=0;i<n;i++) {
            ArrayList<Integer> list=lists[i];
            for (int u=0;u<list.size();u++) {
                for (int v=u+1;v<list.size();v++) {
                    for (int w=0;w<n;w++) {
                        if (able[list.get(u)][w] && able[list.get(v)][w])
                            return "Yes";
                    }
                }
            }
        }
        return "No";
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