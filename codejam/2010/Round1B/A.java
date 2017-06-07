import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2010 Round 1B
 * Problem A. File Fix-it
 */
public class Main {

    private String solve(Scanner scanner) {
        HashSet<String> set=new HashSet<>();
        int n=scanner.nextInt(), m=scanner.nextInt(), ans=0;
        while (n-->0) set.addAll(translate(scanner.next()));
        while (m-->0)
            for (String s: translate(scanner.next()))
                if (set.add(s)) ans++;
        return String.valueOf(ans);
    }

    private List<String> translate(String path) {
        ArrayList<Integer> list = new ArrayList<>();
        int index=0;
        while (true) {
            index=path.indexOf('/', index+1);
            if (index<0) index=path.length();
            list.add(index);
            if (index>=path.length()) break;
        }
        return list.stream().map(i->path.substring(0, i)).collect(Collectors.toList());
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