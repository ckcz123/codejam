import java.io.PrintStream;
import java.util.*;

public class Main {

    class Point {
        int x, y;
        double k;
        public Point(int _x, int _y) {
            x=_x;y=_y;
            if (x==0) {
                x=y/Math.abs(y);
                y=x*999999999;
            }
            k=y/(x+0.0);
        }
    }

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        int[] x=new int[n], y=new int[n];
        for (int i=0;i<n;i++) {
            x[i]=scanner.nextInt();
            y[i]=scanner.nextInt();
        }
        StringBuilder builder=new StringBuilder();
        for (int i=0;i<n;i++) {
            ArrayList<Point> arrayList=new ArrayList<>();
            for (int j=0;j<n;j++) {
                if (i!=j)
                    arrayList.add(new Point(x[j]-x[i], y[j]-y[i]));
            }
            builder.append("\n").append(solve(arrayList));

        }
        return builder.toString();
    }

    private int solve(ArrayList<Point> points) {
        int cnt1=0, cnt2=0, pos=0, neg=0;
        PriorityQueue<Point> queue=new PriorityQueue<>((p1,p2)->{
            if (p1.k>0 && p2.k<0) return -1;
            if (p1.k<0 && p2.k>0) return 1;
            return Double.compare(p1.k, p2.k);
        });
        for (Point point: points) {
            if (point.y==0) {
                if (point.x>0) pos++;
                else neg++;
            }
            else {
                if (point.y>0) cnt1++;
                else cnt2++;
                queue.offer(point);
            }
        }

        int ans=Math.min(cnt1, cnt2);
        while (!queue.isEmpty()) {
            int pos1=0, neg1=0;
            Point p=queue.poll();
            if (p.y>0) pos1++;
            else neg1++;
            while (!queue.isEmpty() && queue.peek().k==p.k) {
                p=queue.poll();
                if (p.y>0) pos1++;
                else neg1++;
            }
            cnt1+=neg-pos1;
            cnt2+=pos-neg1;
            pos=pos1;
            neg=neg1;
            ans=Math.min(ans, Math.min(cnt1, cnt2));
        }
        return ans;
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