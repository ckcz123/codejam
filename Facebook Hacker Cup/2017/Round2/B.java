import java.io.*;
import java.util.*;

///// This is an incorrect solution //////
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        long[][] a=new long[n][2];
        long x=scanner.nextLong(), ax=scanner.nextLong(), bx=scanner.nextLong(), cx=scanner.nextLong();
        for (int i=0;i<n;i++) {
            a[i][0]=x;
            x=((ax*x+bx)%cx)+1;
        }
        x=scanner.nextLong(); ax=scanner.nextLong(); bx=scanner.nextLong(); cx=scanner.nextLong();
        for (int i=0;i<n;i++) {
            a[i][1]=x;
            x=((ax*x+bx)%cx)+1;
        }
        // sort
        Arrays.sort(a,Comparator.comparingLong(u->u[0]));

        LinkedList<long[]> linkedList=new LinkedList<>();
        for (int i=0;i<n;i++) {
            if (linkedList.isEmpty()) linkedList.addLast(a[i]);
            else {
                long[] last=linkedList.peekLast();
                long distance=a[i][0]-last[0];

                if (a[i][1]>last[1]+distance) {
                    while (a[i][1]>last[1]+distance) {
                        linkedList.pollLast();
                        if (linkedList.isEmpty()) break;
                        last=linkedList.peekLast();
                        distance=a[i][0]-last[0];
                    }
                }
                else {
                    if (a[i][1]>=last[1]-distance) {
                        linkedList.addLast(a[i]);
                    }
                }
            }
        }
        linkedList.addLast(new long[]{1000000000,0});

        double ans=0;
        long[] last=new long[] {-1000000000,0};
        while (!linkedList.isEmpty()) {
            long[] now=linkedList.pollFirst();
            if (now[0]-last[0]>=now[1]+last[1]) {
                ans+=(last[1]*last[1]+now[1]*now[1])/2.;
            }
            else {
                // insersect
                long distance=now[0]-last[0], dh=now[1]-last[1];
                double t=(distance-dh)/2.0;
                double nh=now[1]-t;
                ans+=(now[1]+nh)*t/2;
                ans+=(nh+now[2]*(distance-t))/2;
            }
        }
        return String.format("%.2f", ans);
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
