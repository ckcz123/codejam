import java.io.*;
import java.util.*;

/**
 * Facebook Hacker Cup 2017 Round 3
 * Problem A. Salient Strings
 */
public class Main {

    public String solve(Scanner scanner) throws Exception {
        int n=scanner.nextInt();
        char[] s=new char[n];
        int[] a=new int[n+1], p=new int[n];
        for (int i=0;i<n;i++) {
            int x=scanner.nextInt()-1;
            a[i]=x; p[x]=i;
        }
        a[n]=-1;
        s[p[0]]='A';
        for (int i=1;i<n;i++) {
            if (a[p[i]+1]>a[p[i-1]+1])
                s[p[i]]=s[p[i-1]];
            else {
                if (s[p[i-1]]=='Z') return "-1";
                s[p[i]]=(char)(s[p[i-1]]+1);
            }
        }
        return new String(s);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=Integer.parseInt(scanner.nextLine());
        long start=System.currentTimeMillis();
        for (int t=1;t<=times;t++) {
            try {
                System.out.println(String.format("Case #%d: %s", t, new Main().solve(scanner)));
            }
            catch (Throwable e) {
                System.err.println("ERROR in case #"+t);
                e.printStackTrace();
            }
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }
}
