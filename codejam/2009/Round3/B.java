import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2009 Round 3
 * Problem B. Alphabetomials
 * Only small solved.
 */
public class Main {

    public String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    public String solveSmall(Scanner scanner) {
        String s=scanner.next();
        int v=scanner.nextInt(), n=scanner.nextInt();
        String[] dictionary=new String[n];
        for (int i=0;i<n;i++) dictionary[i]=scanner.next();
        ArrayList<String> ans=new ArrayList<>();
        for (int t=1;t<=v;t++) {
            ArrayList<ArrayList<String>> lists=generate(dictionary, n, t, new LinkedList<>(), new ArrayList<>());
            long sum=0;
            for (ArrayList<String> list: lists) {
                int[] cnt=new int[26];
                for (String s1: list) {
                    for (char c: s1.toCharArray()) {
                        cnt[c-'a']++;
                    }
                }
                for (String s1: s.split("\\+")) {
                    long val=1;
                    for (char c: s1.toCharArray()) {
                        val*=cnt[c-'a'];
                        val%=10009;
                    }
                    sum+=val;
                    sum%=10009;
                }
            }
            ans.add(String.valueOf(sum%10009));
        }
        return String.join(" ", ans);
    }

    private ArrayList<ArrayList<String>> generate(String[] dictionary, int n, int s,
                                                  LinkedList<String> list, ArrayList<ArrayList<String>> ans) {
        if (list.size()==s) {
            ans.add(new ArrayList<>(list));
            return ans;
        }
        for (int i=0;i<n;i++) {
            list.offerLast(dictionary[i]);
            generate(dictionary, n, s, list, ans);
            list.pollLast();
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