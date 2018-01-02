import java.io.*;
import java.util.*;

/**
 * Facebook Hacker Cup 2017 Round 2
 * Problem D. Rain Over New York
 */
public class Main {

    private static final long MOD = 1000000007L;

    public String solve(Scanner scanner) {
        long n=scanner.nextLong(); int m=scanner.nextInt(), k=scanner.nextInt();
        long[] h=new long[m], lh=new long[m], sh = new long[m+1], ah=new long[m+1];
        int index=0;
        for (int i=0;i<k;i++) {
            int l=scanner.nextInt();
            long x=scanner.nextLong(), a=scanner.nextLong(), b=scanner.nextLong();
            for (int j=0;j<l;j++) {
                h[index]=x;
                lh[index]=n-x;
                sh[index+1]=sh[index]+x;
                ah[index+1]=ah[index]+x*(index+1);
                x=((a*x+b)%(n-1))+1;
                index++;
            }
        }

        long[] left=calLeftBigger(h, m), right=calRightNoLess(h, m);
        long[] length=new long[m], length2=new long[m], length3=new long[m];
        for (int i=0;i<m;i++) {
            long x=left[i], y=right[i], z=x+y;
            length[i]=x*y%MOD;
            if (x%2==0) x/=2;
            else if (y%2==0) y/=2;
            else z/=2;
            length2[i]=x*y%MOD*z%MOD;

            x=left[i]; y=right[i];
            int start=i-(int)x+1;
            // 1*a[start]+2*a[start+1]+...+x*a[i]
            // (start+1)*a[start]+...+(start+x)*a[i] - start*(a[start]+...+a[i])
            // ah[i+1]-ah[start] - start*(sh[i+1]-sh[start])
            long lval = ah[i+1]-ah[start] - start*(sh[i+1]-sh[start]);
            lval = lval%MOD*y%MOD;

            int end=i+(int)y-1;
            // (y-1)*a[i+1]+(y-2)*a[i+2]+...+a[end]
            // (y+i+1)*(a[i+1]+a[i+2]+...+a[end]) - ((i+2)*a[i+1]+...+(end+1)*a[end])
            // (end+2)*(sh[end+1]-sh[i+1]) - (ah[end+1]-ah[i+1])
            long rval = (end+2)*(sh[end+1]-sh[i+1]) - (ah[end+1]-ah[i+1]);
            rval = rval%MOD*x%MOD;

            length3[i] = (lval+rval)%MOD;
        }

        long number=0; // The number of canvas
        for (int i=0;i<m;i++) {
            long x=lh[i], y=x+1;
            if (x%2==0) x/=2; else y/=2;
            number=(number+(x*y%MOD)*length[i]%MOD)%MOD;
        }

        // The number of gray points
        long gray=0;
        for (int i=0;i<m;i++) gray=(gray+h[i])%MOD;
        gray=gray*number%MOD;

        // The number of white point
        long white=0;
        for (int i=0;i<m;i++) {
            long x=lh[i], y=lh[i]+1, z=lh[i]+2;
            if (x%2==0) x/=2; else y/=2;
            if (x%3==0) x/=3;
            else if (y%3==0) y/=3;
            else z/=3;
            long u=x*y%MOD*z%MOD;

            white+=length2[i]*u%MOD;
            white%=MOD;
        }

        // The number of blue point
        long blue=0;
        for (int i=0;i<m;i++) {
            long x=lh[i], y=lh[i]+1;
            if (x%2==0) x/=2; else y/=2;
            long u=x*y%MOD;
            long w=((h[i]*length2[i]%MOD-length3[i])%MOD+MOD)%MOD;
            w=w*u%MOD; // w -- building

            x=lh[i]; y=lh[i]+1; long z=lh[i]-1;
            if (x%2==0) x/=2; else y/=2;
            if (x%3==0) x/=3;
            else if (y%3==0) y/=3;
            else z/=3;
            u=x*y%MOD*z%MOD;
            u=u*length2[i]%MOD;

            w+=u; w%=MOD;
            blue+=w;
            blue%=MOD;
        }

        // final: black
        long black=number*n%MOD*m%MOD;
        black=(black+3*MOD-white-gray-blue)%MOD;

        return String.format("%d %d %d %d", (MOD-black)%MOD, (MOD-white)%MOD, (MOD-gray)%MOD, (MOD-blue)%MOD);
    }

    private long[] calLeftBigger(long[] a, int n) {
        LinkedList<Integer> linkedList=new LinkedList<>();
        long[] ans=new long[n];
        for (int i=0;i<n;i++) {
            while (!linkedList.isEmpty() && a[linkedList.peekLast()]<a[i])
                linkedList.pollLast();

            ans[i]=i-(linkedList.isEmpty()?-1:linkedList.peekLast());
            linkedList.offerLast(i);
        }
        return ans;
    }

    private long[] calRightNoLess(long[] a, int n) {
        LinkedList<Integer> linkedList=new LinkedList<>();
        long[] ans=new long[n];
        for (int i=n-1;i>=0;i--) {
            while (!linkedList.isEmpty() && a[linkedList.peekFirst()]<=a[i])
                linkedList.pollFirst();

            ans[i]=(linkedList.isEmpty()?n:linkedList.peekFirst())-i;
            linkedList.offerFirst(i);
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
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
