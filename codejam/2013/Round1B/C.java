import java.io.File;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2013 Round 1B
 * Problem C. Garbled Email
 */
public class Main {

    private static HashSet<String> set;
    private static int maxLength=0;
    // for `codejam`, remember [codejam, *odejam, c*dejam, ..., codeja*, *odej*m, *odeja*, c*deja*]
    private void init() throws Exception {
        if (set==null) {
            set=new HashSet<>();
            maxLength=0;
            Scanner sc=new Scanner(new File("dictionary.txt"));
            while (sc.hasNext()) {
                String s=sc.next();
                set.add(s);
                maxLength=Math.max(maxLength, s.length());
                ArrayList<int[]> list=generate(s.length(), 0);
                for (int[] one: list) {
                    char[] chars=s.toCharArray();
                    for (int v: one) chars[v]='*';
                    set.add(new String(chars));
                }
            }
        }
    }


    public String solve(Scanner scanner) throws Exception {
        init();
        String s=scanner.next();
        int n=s.length();
        int[][] dp=new int[n+1][5];
        for (int i=1;i<=n;i++) {
            for (int j=0;j<5;j++) {
                int ans=Integer.MAX_VALUE;
                for (int l=1;l<=Math.min(i, maxLength);l++) {
                    String sub=s.substring(i-l, i);
                    if (set.contains(sub)) {
                        ans=Math.min(ans, dp[i-l][Math.max(0, j-l)]);
                    }
                    ArrayList<int[]> arrayList=generate(l, j);
                    for (int[] one: arrayList) {
                        char[] chars=sub.toCharArray();
                        for (int v: one) chars[v]='*';
                        if (set.contains(new String(chars))) {
                            ans=one.length+Math.min(ans-one.length, dp[i-l][Math.max(0, 4-one[0])]);
                        }
                    }
                }
                dp[i][j]=ans;
            }
        }
        return String.valueOf(dp[n][0]);
    }

    private ArrayList<int[]> generate(int l, int offset) {
        ArrayList<int[]> arrayList=new ArrayList<>();
        for (int i=0;i<l-offset;i++) arrayList.add(new int[] {i});
        for (int i=0;i<l-offset;i++) {
            for (int j=i+5;j<l-offset;j++) {
                arrayList.add(new int[]{i,j});
            }
        }
        return arrayList;
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
            catch (Exception e) {System.err.println("ERROR #"+t);e.printStackTrace();}
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.));
    }

}