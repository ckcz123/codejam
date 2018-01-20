import java.io.*;
import java.util.*;

/**
 * Facebook Hacker Cup 2017 Round 3
 * Problem B. Sluggish Security
 */
public class Main {

    private static final long MOD = 1000000007L;

    private static long[] x, y;

    public String solve(Scanner scanner) throws Exception {
        calMod();
        int n=scanner.nextInt();
        long[] a=input(scanner, n), b=input(scanner, n);

        int ai=0, bi=0;
        long ans=1;

        while (ai<n || bi<n) {

            ////// CASE 1: X,X
            if (ai<n && bi<n && a[ai]==b[bi]) {
                ai++; bi++;
            }

            ////// CASE 2: XY,YX
            else if (ai+1<n && bi+1<n && a[ai]==b[bi+1] && a[ai+1]==b[bi]) {
                ans*=2; ans%=MOD;
                ai+=2; bi+=2;
            }

            ////// CASE 3: XYX,Y
            else if (ai+2<n && bi<n && a[ai]==a[ai+2] && a[ai+1]==b[bi]) {
                ans*=2; ans%=MOD;
                ai+=3; bi++;
            }

            ////// CASE 4: X,YXY
            else if (bi+2<n && ai<n && b[bi]==b[bi+2] && b[bi+1]==a[ai]) {
                ans*=2; ans%=MOD;
                ai++; bi+=3;
            }

            ////// CASE 5: XX|XYXY|...
            else {
                int al = 0, bl = 0;
                while (true) {
                    if (ai+1<n && a[ai]==a[ai+1]) {
                        ai+=2;
                        al++;
                    }
                    else if (ai+3<n && a[ai]==a[ai+2] && a[ai+1]==a[ai+3]) {
                        ai+=4;
                        al++;
                    }
                    else break;
                }
                while (true) {
                    if (bi+1<n && b[bi]==b[bi+1]) {
                        bi+=2;
                        bl++;
                    }
                    else if (bi+3<n && b[bi]==b[bi+2] && b[bi+1]==b[bi+3]) {
                        bi+=4;
                        bl++;
                    }
                    else break;
                }

                // Not IMPOSSIBLE
                if (al==0 && bl==0) return "0";

                ans=ans*x[al+bl]%MOD*y[al]%MOD*y[bl]%MOD;
            }
        }
        return String.valueOf(ans);
    }

    private long[] input(Scanner scanner, int n) {
        long[] a=new long[n], da=new long[n];
        a[0]=scanner.nextLong();
        int k=scanner.nextInt(), index=0;
        for (int i=0;i<k;i++) {
            int r=scanner.nextInt(), l=scanner.nextInt();
            ArrayList<Long> list=new ArrayList<>();
            for (int j=0;j<l;j++) list.add(scanner.nextLong());
            for (int j=0;j<r;j++) {
                for (long u: list) {
                    a[index+1]=a[index]+u;
                    index++;
                }
            }
        }
        return a;
    }

    private static void calMod() {
        if (x!=null) return;
        x=new long[5000000]; y=new long[5000000];

        // x
        x[0]=1;
        y[0]=1;
        for (int i=1;i<5000000;i++) {
            x[i]=x[i-1]*i%MOD;
            y[i]=quickPow(x[i], MOD-2);
        }
    }

    // x^n%p
    private static long quickPow(long x, long n) {
        if (n==0) return 1;
        if (n==1) return x;
        long v=quickPow(x, n/2);
        return v*v%MOD*(n%2!=0?x:1)%MOD;
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
