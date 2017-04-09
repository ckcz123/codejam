import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        int[] a=new int[n];
        for (int i=0;i<n;i++) a[i]=scanner.nextInt();
        int maxgap=0;
        int x=0, y=0;
        for (int i=0;i<n-1;i++) {
            if (a[i]>a[i+1]) {
                x+=a[i]-a[i+1];
                maxgap=Math.max(maxgap, a[i]-a[i+1]);
            }
        }
        for (int i=0;i<n-1;i++) {
            y+=Math.min(a[i], maxgap);
        }
        return x+" "+y;
    }


    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("E:\\desktop\\output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=scanner.nextInt();
        for (int t=1;t<=times;t++) {
            System.out.println(String.format("Case #%d: %s", t, new Main().solve(scanner)));
        }

    }

}