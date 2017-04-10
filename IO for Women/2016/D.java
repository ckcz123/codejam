import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        String[] ans=new String[n];
        for (int i=0;i<n;i++) ans[i]=scanner.next();
        for (int i=1;i<=100000;i++) {
            ArrayList<String> arrayList=new ArrayList<>();
            for (char c='A';c<='Z';c++)
                arrayList.add(String.valueOf(c));
            Collections.shuffle(arrayList);
            String candidate=String.join("", arrayList);
            boolean able=true;
            for (String s: ans) {
                if (candidate.contains(s)) {
                    able=false;
                    break;
                }
            }
            if (able) return candidate;
        }
        return "IMPOSSIBLE";
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