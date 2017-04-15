import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), p=scanner.nextInt();
        long[] origin=new long[n];
        for (int i=0;i<n;i++) origin[i]=scanner.nextLong();
        long maxorigin= LongStream.of(origin).max().getAsLong();

        ArrayList<Long>[] ingredients=new ArrayList[n];
        long maxindredient=0;
        for (int i=0;i<n;i++) {
            ingredients[i]=new ArrayList<>();
            for (int j=0;j<p;j++)
                ingredients[i].add(scanner.nextLong());
            Collections.sort(ingredients[i]);
            maxindredient=Math.max(maxindredient, ingredients[i].get(p-1));
        }

        int res=0;
        for (int size=1;maxorigin*size<=maxindredient/.9;) {
            long[] need=new long[n];
            for (int i=0;i<n;i++) need[i]=origin[i]*size;
            long[] use=new long[n];
            boolean able=true;
            for (int i=0;i<n;i++) {
                for (long x: ingredients[i]) {
                    if (x>=need[i]*.9 && x<=need[i]*1.1) {
                        use[i]=x;
                        break;
                    }
                }
                if (use[i]==0) {
                    able=false;
                    break;
                }
            }
            if (!able) {
                size++;continue;
            }
            res++;
            for (int i=0;i<n;i++) {
                ingredients[i].remove(use[i]);
            }
        }
        return String.valueOf(res);
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