import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * APAC 2015 Round A Problem B: Super 2048
 * Check README.md for explanation.
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        String dir=scanner.next();
        int[][] nums=new int[n][n];
        for (int i=0;i<n;i++) for (int j=0;j<n;j++) nums[i][j]=scanner.nextInt();
        int[][] ans=new int[n][n];
        if ("left".equals(dir)) {
            for (int i=0;i<n;i++) {
                int curr=0;
                for (int j=0;j<n;j++) {
                    if (nums[i][j]==0) continue;
                    if (ans[i][curr]==0) {
                        ans[i][curr]=nums[i][j];
                    }
                    else if (ans[i][curr]==nums[i][j]) {
                        ans[i][curr]*=2;
                        curr++;
                    }
                    else {
                        curr++;
                        j--;
                    }
                }
            }
        }
        if ("right".equals(dir)) {
            for (int i=0;i<n;i++) {
                int curr=n-1;
                for (int j=n-1;j>=0;j--) {
                    if (nums[i][j]==0) continue;
                    if (ans[i][curr]==0) {
                        ans[i][curr]=nums[i][j];
                    }
                    else if (ans[i][curr]==nums[i][j]) {
                        ans[i][curr]*=2;
                        curr--;
                    }
                    else {
                        curr--;
                        j++;
                    }
                }
            }
        }
        if ("up".equals(dir)) {
            for (int j=0;j<n;j++) {
                int curr=0;
                for (int i=0;i<n;i++) {
                    if (nums[i][j]==0) continue;
                    if (ans[curr][j]==0) {
                        ans[curr][j]=nums[i][j];
                    }
                    else if (ans[curr][j]==nums[i][j]) {
                        ans[curr][j]*=2;
                        curr++;
                    }
                    else {
                        curr++;
                        i--;
                    }
                }
            }
        }
        if ("down".equals(dir)) {
            for (int j=0;j<n;j++) {
                int curr=n-1;
                for (int i=n-1;i>=0;i--) {
                    if (nums[i][j]==0) continue;
                    if (ans[curr][j]==0) {
                        ans[curr][j]=nums[i][j];
                    }
                    else if (ans[curr][j]==nums[i][j]) {
                        ans[curr][j]*=2;
                        curr--;
                    }
                    else {
                        curr--;
                        i++;
                    }
                }
            }
        }
        return "\n"+String.join("\n", Arrays.stream(ans)
                .map(x->String.join(" ",
                        Arrays.stream(x).boxed().map(String::valueOf).collect(Collectors.toList())))
                .collect(Collectors.toList()));
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