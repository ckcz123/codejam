import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        int a=scanner.nextInt()-1;
        boolean[] able1=new boolean[16], able2=new boolean[16];
        for (int i=0;i<16;i++) {
            int x=scanner.nextInt();
            if (a==i/4) able1[x-1]=true;
        }
        int b=scanner.nextInt()-1;
        for (int i=0;i<16;i++) {
            int x=scanner.nextInt();
            if (b==i/4) able2[x-1]=true;
        }
        int ans=0;
        for (int i=0;i<16;i++) {
            if (able1[i] && able2[i]) {
                if (ans>0) ans=-1;
                if (ans==0) ans=i+1;
            }
        }
        if (ans==0) return "Volunteer cheated!";
        if (ans==-1) return "Bad magician!";
        return String.valueOf(ans);
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