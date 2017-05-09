import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Codejam 2012 Round 2
 * Problem C. Mountain View
 */
public class Main {

    private String solve(Scanner scanner) throws Throwable {
        int n=scanner.nextInt();
        int[] see=new int[n-1];
        for (int i=0;i<n-1;i++) {
            see[i]=scanner.nextInt()-1;
        }
        int[] height=new int[n], max=new int[n];
        Arrays.fill(max, 999999999);
        height[n-1]=max[n-1];
        if (!fill(0, n-1, height, max, see)) return "Impossible";
        return String.join(" ", Arrays.stream(height).boxed().map(String::valueOf)
                .collect(Collectors.toList()));
    }

    private boolean fill(int start, int end, int[] height, int[] max, int[] see) {
        if (start==end) return true;
        for (int i=start;i<end;i++)
            if (see[i]>end) return false;
        int index=-1;
        for (int i=start;i<end;i++) {
            if (see[i]==end) {
                index=i;break;
            }
        }
        height[index]=max[index];
        for (int i=start;i<end;i++) {
            max[i]=Math.min(max[i],
                    height[end]-(height[end]-height[index])*(end-i)/(end-index)-2);
        }
        return fill(start, index, height, max, see) && fill(index+1, end, height, max, see);
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