import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2017 Round 3
 * Problem A. Googlements
 */
public class Main {

    public String solve(Scanner scanner) {
        String g=scanner.next();
        init();
        LinkedList<String> linkedList=new LinkedList<>();
        linkedList.offer(g);
        int ans=0, l=g.length();

        while (!linkedList.isEmpty()) {
            ans++;
            String s=linkedList.poll();
            int sum=0, nsum=0;
            int[] num=new int[l+1];
            for (int i=1;i<=l;i++) {
                int x=s.charAt(i-1)-'0';
                sum+=x;
                nsum+=i*x;
                num[i]=x;
            }

            if (sum>l) continue;
            if (nsum>l) {
                int n=1, left=l;
                for (int v: num) {
                    n*=c[left][v];
                    left-=v;
                }
                ans+=n;
                continue;
            }

            char[] chars=new char[l];
            Arrays.fill(chars, '0');
            linkedList.addAll(generate(s, num, l, 1, chars, 0, new ArrayList<>()));
        }
        return String.valueOf(ans);
    }

    private List<String> generate(String s, int[] num, int l, int curr, char[] chars, int start, ArrayList<String> ans) {
        if (curr>l) {
            String ns=new String(chars);
            if (!ns.equals(s)) ans.add(ns);
            return ans;
        }
        if (num[curr]==0) return generate(s, num, l, curr+1, chars, 0, ans);
        for (int i=start;i<l;i++) {
            if (chars[i]!='0') continue;
            chars[i]=(char)(curr+'0');
            num[curr]--;
            generate(s, num, l, curr, chars, i+1, ans);
            num[curr]++;
            chars[i]='0';
        }
        return ans;
    }

    private static long[][] c;
    private void init() {
        if (c!=null) return;
        c=new long[11][11];
        for (int i=0;i<11;i++) {
            for (int j=0;j<=i;j++) {
                if (j==0 || j==i) c[i][j]=1;
                else c[i][j]=c[i-1][j]+c[i-1][j-1];
            }
        }
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