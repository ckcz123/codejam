import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    class Point {
        int x, y;
        public Point(int _x, int _y) {x=_x;y=_y;}
        public double k() {
            return x==0?(y/Math.abs(y))*1e10:y/(x+.0);
        }
        public boolean same(Point another) {
            return Math.abs(k()-another.k())<1e-9;
        }
    }

    private String solve(Scanner scanner) throws Throwable {
        int n=scanner.nextInt();
        Point[] points=new Point[n];
        for (int i=0;i<n;i++) points[i]=new Point(scanner.nextInt(), scanner.nextInt());
        ArrayList<ArrayList<Integer>> lists=new ArrayList<>();
        backtrack(lists, new LinkedList<>(), n);
        ArrayList<Integer> ans=new ArrayList<>();
        int sum=0;
        for (ArrayList<Integer> list: lists) {
            int s=calArea(list, points, n);
            if (sum<s) {
                sum=s;
                ans=list;
            }
        }
        return String.join(" ", ans.stream().map(String::valueOf).collect(Collectors.toList()));
    }

    private boolean cross(Point a, Point b, Point c, Point d) {
        if (a.x==d.x && a.y==d.y) return cross(c,d,a,b);
        if (b.x==c.x && b.y==c.y)
            return new Point(a.x-b.x, a.y-b.y).same(new Point(d.x-c.x, d.y-c.y));
        double delta = determinant(b.x-a.x, c.x-d.x, b.y-a.y, c.y-d.y);
        if ( delta<=(1e-6) && delta>=-(1e-6) )  // delta=0，表示两线段重合或平行
        {
            return false;
        }
        double namenda = determinant(c.x-a.x, c.x-d.x, c.y-a.y, c.y-d.y) / delta;
        if ( namenda>1 || namenda<0 )
        {
            return false;
        }
        double miu = determinant(b.x-a.x, c.x-a.x, b.y-a.y, c.y-a.y) / delta;
        if ( miu>1 || miu<0 )
        {
            return false;
        }
        return true;
    }

    private double determinant(double v1, double v2, double v3, double v4)
    {
        return (v1*v3-v2*v4);
    }

    private int calArea(ArrayList<Integer> list, Point[] points, int n) {
        Point[] p=new Point[n];
        for (int i=0;i<n;i++) p[i]=points[list.get(i)];
        for (int i=0;i<n;i++) {
            for (int j=i+1;j<n;j++) {
                if (i==j) continue;
                if (cross(p[i], p[(i+1)%n], p[j], p[(j+1)%n])) return 0;
            }
        }
        int sum=0;
        for (int i=0;i<n;i++) {
            int j=(i+1)%n;
            sum+=p[i].x*p[j].y-p[i].y*p[j].x;
        }
        return Math.abs(sum);
    }

    private void backtrack(ArrayList<ArrayList<Integer>> list, LinkedList<Integer> tempList, int n){
        if(tempList.size()==n){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i=0; i<n; i++){
                if(tempList.contains(i)) continue;
                tempList.offerLast(i);
                backtrack(list, tempList, n);
                tempList.pollLast();
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=scanner.nextInt();
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