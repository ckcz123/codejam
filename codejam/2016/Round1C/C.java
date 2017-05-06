import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Codejam 2016 Round 1C
 * Problem C. Fashion Police
 */
public class Main {

    private String solve(Scanner scanner) {
        int a=scanner.nextInt(), b=scanner.nextInt(), c=scanner.nextInt(), k=scanner.nextInt();
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add(String.valueOf(a*b*Math.min(c, k)));
        for (int i=1;i<=a;i++) {
            for (int j=1;j<=b;j++) {
                for (int p=0;p<Math.min(c, k);p++)
                    arrayList.add(String.format("%d %d %d", i, j, (i+j+p-1)%c+1));
            }
        }
        return String.join("\n", arrayList);
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