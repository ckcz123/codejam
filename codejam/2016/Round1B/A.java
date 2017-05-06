import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2016 Round 1B
 * Problem A. Getting the Digits
 */
public class Main {

    private String solve(Scanner scanner) {
        String s=scanner.next().toLowerCase();
        int[] cnt=new int[128];
        for (char c: s.toCharArray()) {
            cnt[c]++;
        }
        int[] num=new int[10];
        num[0]=cnt['z'];
        num[2]=cnt['w'];
        num[4]=cnt['u'];
        num[6]=cnt['x'];
        num[8]=cnt['g'];
        num[7]=cnt['s']-num[6];
        num[5]=cnt['f']-num[4];
        num[3]=cnt['h']-num[8];
        num[1]=cnt['o']-num[0]-num[2]-num[4];
        num[9]=cnt['i']-num[5]-num[6]-num[8];
        StringBuilder builder=new StringBuilder();
        for (int i=0;i<10;i++)
            for (int j=1;j<=num[i];j++)
                builder.append(i);
        return builder.toString();
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