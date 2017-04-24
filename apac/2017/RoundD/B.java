import java.io.PrintStream;
import java.util.*;

/**
 * APAC 2017 Round D Problem B: Sitting
 * Check README.md for explanation.
 */
public class Main {

    public String solve(Scanner scanner) {
        int r=scanner.nextInt(), c=scanner.nextInt();
        if (r>c) {int tmp=r;r=c;c=tmp;}
        // ##.##.##.##
        int v1=c/3*2+c%3;
        // #.##.##.##.
        int v2=c/3*2+(c%3==0?0:1);
        // .##.##.##.#
        int v3=c/3*2+(c%3<=1?0:1);
        if (r<=2) return String.valueOf(r*v1);
        return String.valueOf(r/3*(v1+v2+v3)+(r%3>=1?v1:0)+(r%3==2?v2:0));
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
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.));
    }
}
