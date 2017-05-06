import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2014 Round 2
 * Problem B. Up and Down
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        ArrayList<Integer> arrayList=new ArrayList<>();
        for (int i=0;i<n;i++) arrayList.add(scanner.nextInt());
        int cnt=0;
        while (!arrayList.isEmpty()) {
            cnt+=cal(arrayList);
        }
        return String.valueOf(cnt);
    }

    private int cal(ArrayList<Integer> arrayList) {
        int min=Integer.MAX_VALUE, index=-1;
        for (int i=0;i<arrayList.size();i++) {
            if (min>arrayList.get(i)) {
                min=arrayList.get(i);index=i;
            }
        }
        arrayList.remove(index);
        return Math.min(index, arrayList.size()-index);
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