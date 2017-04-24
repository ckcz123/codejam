import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * APAC 2016 Round A Problem D: gSnake
 * Check README.md for explanation.
 */
public class Main {


    class Snake {

        class Point {
            int x, y;
            public Point(int _x, int _y) {x=_x;y=_y;}
            public String toString() {return String.format("[%d,%d]",x,y);}
        }

        int[][] dirs=new int[][] {{-1,0}, {0,-1}, {1,0}, {0,1}};
        int r, c, dir, size;
        LinkedList<Point> linkedList;
        HashSet<Long> bodySet;
        HashSet<Long> foodSet;

        public Snake(int _r, int _c) {
            r=_r; c=_c;
            linkedList=new LinkedList<>();
            bodySet=new HashSet<>();
            foodSet=new HashSet<>();
            linkedList.add(new Point(1, 1));
            bodySet.add(build(1, 1));
            size=1;
            dir=3;
        }

        public int getSize() {
            return size;
        }

        public boolean goOn() {
            Point point=next();
            if (!hasFood(point)) {
                Point tail=linkedList.pollLast();
                bodySet.remove(build(tail));
            } else {
                foodSet.add(build(point));
                size++;
            }
            if (collide(point)) return false;
            linkedList.addFirst(point);
            bodySet.add(build(point));
            return true;
        }

        public void turn(char ch) {
            int v=ch=='L'?1:-1;
            dir=(dir+v+4)%4;
        }

        private Point next() {
            Point front=linkedList.getFirst();
            int x=front.x+dirs[dir][0], y=front.y+dirs[dir][1];
            if (x<=0) x+=r; if (x>r) x-=r;
            if (y<=0) y+=c; if (y>c) y-=c;
            return new Point(x, y);
        }

        private boolean hasFood(Point point) {
            int x=point.x, y=point.y;
            return (x+y)%2!=0 && !foodSet.contains(build(x, y));
        }

        private boolean collide(Point point) {
            return bodySet.contains(build(point));
        }

        private long build(int x, int y) {
            return 10000000000L*x+y;
        }

        private long build(Point point) {
            return build(point.x, point.y);
        }

    }

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        Snake snake=new Snake(scanner.nextInt(), scanner.nextInt());
        int[] a=new int[n];
        char[] c=new char[n];
        for (int i=0;i<n;i++) {
            a[i]=scanner.nextInt();
            c[i]=scanner.next().charAt(0);
        }
        int curr=0;
        for (int t=0;t<=1200000;t++) {
            if (curr<n && a[curr]==t)
                snake.turn(c[curr++]);
            if (!snake.goOn())
                break;
        }
        return String.valueOf(snake.getSize());
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