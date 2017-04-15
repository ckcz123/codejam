import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private String solve(Scanner scanner) {
        int b=scanner.nextInt();
        long m=scanner.nextLong();
        if (m>(1L<<(b-2))) return "IMPOSSIBLE";
        char[][] map=new char[b][b];
        for (int i=0;i<b;i++) Arrays.fill(map[i], '0');
        for (int i=0;i<b-2;i++) {
            for (int j=i+1;j<b-1;j++) {
                map[i][j]='1';
            }
        }
        m--;
        map[0][b-1]='1';
        for (int i=1;i<=b-2;i++) {
            if ((m&(1L<<(i-1)))!=0)
                map[i][b-1]='1';
        }
        return "POSSIBLE\n"+String.join("\n",
                Arrays.stream(map).map(String::valueOf).collect(Collectors.toList()));
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