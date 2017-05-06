import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2014 Round 1B
 * Problem A. The Repeater
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        ArrayList<Integer>[] lists=new ArrayList[n];
        for (int i=0;i<n;i++) lists[i]=new ArrayList<>();
        String base=null;
        int len=-1;
        boolean able=true;
        for (int i=0;i<n;i++) {
            String word=scanner.next();
            StringBuilder builder=new StringBuilder();
            int cnt=0; char last=0;
            for (char c: word.toCharArray()) {
                if (c!=last) {
                    lists[i].add(cnt);
                    cnt=1;
                    last=c;
                    builder.append(c);
                }
                else cnt++;
            }
            lists[i].add(cnt);
            String s=builder.toString();
            if (base!=null && !base.equals(s)) able=false;
            if (len!=-1 && len!=lists[i].size()) able=false;
            base=s;
            len=lists[i].size();
        }
        if (!able) return "Fegla Won";
        int cnt=0;
        for (int i=0;i<len;i++) {
            ArrayList<Integer> arrayList=new ArrayList<>();
            for (int j=0;j<n;j++)
                arrayList.add(lists[j].get(i));
            Collections.sort(arrayList);
            int b=arrayList.get(arrayList.size()/2);
            for (int u: arrayList) cnt+=Math.abs(u-b);
        }
        return String.valueOf(cnt);
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