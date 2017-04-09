import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        int l=scanner.nextInt();
        long x=scanner.nextLong();
        if (x>=12) x=x%4+8;
        StringBuilder builder=new StringBuilder();
        String s=scanner.next().replace('i','2').replace('j','3').replace('k','4');
        for (int i=0;i<x;i++) builder.append(s);
        s=builder.toString();
        int state=2, curr=1;
        for (char c: s.toCharArray()) {
            curr=cal(curr, c-'0');
            if (state==curr && state!=4) {
                state++;
                curr=1;
            }
        }
        return state==4&&curr==4?"YES":"NO";
    }

    private int cal(int x, int y) {
        int sig=(int)Math.signum(x*y);
        x=Math.abs(x);y=Math.abs(y);
        if (x==1 || y==1) return sig*x*y;
        if (x==y) return -sig;
        if (x==2 && y==3) return sig*4;
        if (x==2 && y==4) return -sig*3;
        if (x==3 && y==2) return -sig*4;
        if (x==3 && y==4) return sig*2;
        if (x==4 && y==2) return sig*3;
        return -sig*2;
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