import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2013 Round 3
 * Problem B. Rural Planning
 * Only small solved.
 */
public class Main {

    class Point {
        int x, y;
        public Point(int _x, int _y) {x=_x;y=_y;}
        public String toString() {return "["+x+","+y+"]";}
    }

    private String solve(Scanner scanner) throws Throwable {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        init();
        int n=scanner.nextInt();
        Point[] points=new Point[n];
        for (int i=0;i<n;i++) points[i]=new Point(scanner.nextInt(), scanner.nextInt());
        ArrayList<ArrayList<Integer>> lists=map.get(n);
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

    private static HashMap<Integer, ArrayList<ArrayList<Integer>>> map;
    private void init() {
        if (map==null) {
            map=new HashMap<>();
            for (int i=3;i<=10;i++) {
                ArrayList<ArrayList<Integer>> lists=new ArrayList<>();
                backtrack(lists, new LinkedList<>(), i);
                map.put(i, lists);
            }
        }
    }

    private boolean onsegment(Point pi,Point pj,Point pk) //判断点pk是否在线段pi pj上
    {
        if(Math.min(pi.x,pj.x)<=pk.x&&pk.x<=Math.max(pi.x,pj.x))
        {
            if(Math.min(pi.y,pj.y)<=pk.y&&pk.y<=Math.max(pi.y,pj.y))
            {
                return true;
            }
        }
        return false;
    }
    private int direction(Point pi,Point pj,Point pk) //计算向量pkpi和向量pjpi的叉积
    {
        return (pi.x-pk.x)*(pi.y-pj.y)-(pi.y-pk.y)*(pi.x-pj.x);
    }
    private boolean cross(Point p1,Point p2,Point p3,Point p4) //判断线段p1p2和p3p4是否相交
    {
        if (p1.x==p4.x && p1.y==p4.y) return cross(p3,p4,p1,p2);
        if (p2.x==p3.x && p2.y==p3.y) {
            return direction(p1,p2,p4)==0 && onsegment(p1,p2,p4);
        }
        double d1 = direction(p3,p4,p1);
        double d2 = direction(p3,p4,p2);
        double d3 = direction(p1,p2,p3);
        double d4 = direction(p1,p2,p4);
        if(d1*d2<0&&d3*d4<0)
            return true;
        if(d1==0&&onsegment(p3,p4,p1))
            return true;
        if(d2==0&&onsegment(p3,p4,p2))
            return true;
        if(d3==0&&onsegment(p1,p2,p3))
            return true;
        if(d4==0&&onsegment(p1,p2,p4))
            return true;
        return false;
    }

    private int calArea(ArrayList<Integer> list, Point[] points, int n) {
        Point[] p=new Point[n];
        for (int i=0;i<n;i++) p[i]=points[list.get(i)];
        for (int i=0;i<n;i++) {
            for (int j=i+1;j<n;j++) {
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
        System.setOut(new PrintStream("output.txt"));
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