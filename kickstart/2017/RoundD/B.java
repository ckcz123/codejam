import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * KickStart 2017 Round D
 * Problem B. Sherlock and The Matrix Game
 * Only small solved.
 */
public class Main {

    private String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int n=scanner.nextInt(), k=scanner.nextInt();
        long[][] init=init(n, scanner);
        long[] a=init[0], b=init[1];

        long[][] matrix=new long[n][n];
        for (int i=0;i<n;i++)
            for (int j=0;j<n;j++)
                matrix[i][j]=a[i]*b[j];

        // calculate sums of all submatrices
        long[][] colSum=new long[n][n];
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                colSum[i][j]=(i==0?0:colSum[i-1][j])+matrix[i][j];
            }
        }

        PriorityQueue<Long> queue=new PriorityQueue<>();
        for (int top=0;top<n;top++) {
            for (int bottom=top;bottom<n;bottom++) {
                for (int left=0;left<n;left++) {
                    long last=0;
                    for (int right=left;right<n;right++) {
                        last+=colSum[bottom][right]-(top==0?0:colSum[top-1][right]);
                        if (queue.size()<k) {
                            queue.offer(last);
                        }
                        else if (queue.peek()<last) {
                            queue.poll();
                            queue.offer(last);
                        }
                    }
                }
            }
        }
        return String.valueOf(queue.peek());
    }

    private long[][] init(int n, Scanner scanner) {
        long a0=scanner.nextLong(), b0=scanner.nextLong(), c=scanner.nextLong(),
                d=scanner.nextLong(), e1=scanner.nextLong(), e2=scanner.nextLong(),f=scanner.nextLong();

        long[] a=new long[n], b=new long[n];
        long x=a0, y=b0, r=0, s=0;
        for (int i=0;i<n;i++) {
            a[i]=(r%2==0?1:-1)*x;
            b[i]=(s%2==0?1:-1)*y;
            long _x=(c*x+d*y+e1)%f, _y=(d*x+c*y+e2)%f,
                    _r=(c*r+d*s+e1)%2, _s=(d*r+c*s+e2)%2;
            x=_x; y=_y; r=_r; s=_s;
        }
        return new long[][] {a,b};
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