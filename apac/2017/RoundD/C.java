import java.io.PrintStream;
import java.util.*;

public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt(), v=scanner.nextInt(), ml=0;
        HashMap<String, Integer> map=new HashMap<>();
        for (int i=0;i<n;i++) {
            String s=sort(scanner.next());
            ml=Math.max(ml, s.length());
            map.put(s, map.getOrDefault(s, 0)+1);
        }
        ArrayList<String> ans=new ArrayList<>();
        while (v-->0) {
            String s=scanner.next();
            int l=s.length();
            long[] dp=new long[l+1];
            dp[0]=1;
            for (int i=1;i<=l;i++) {
                for (int j=Math.max(0, i-ml);j<i;j++) {
                    dp[i]+=dp[j]*map.getOrDefault(sort(s.substring(j, i)), 0);
                    dp[i]%=1000000007;
                }
            }
            ans.add(String.valueOf(dp[l]));
        }
        return String.join(" ", ans);
    }

    private String sort(String s) {
        char[] c=s.toCharArray();
        Arrays.sort(c);
        return new String(c);
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
