import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        int r=scanner.nextInt(), c=scanner.nextInt(), x=scanner.nextInt(),
                y=scanner.nextInt(), s=scanner.nextInt();
        double p=scanner.nextDouble(), q=scanner.nextDouble();
        scanner.nextLine();
        String[] map=new String[r];
        for (int i=0;i<r;i++) map[i]=scanner.nextLine().replace(" ","");
        return String.format("%.7f", solve(r,c,x,y,s,map,new int[r][c],p,q));
    }

    private double solve(int r, int c, int x, int y, int s, String[] map,
                         int[][] times, double p, double q) {
        if (s==0) return 0;
        double max=0;
        for (int[] dir: new int[][] {{-1,0},{0,1},{0,-1},{1,0}}) {
            int xx=x+dir[0], yy=y+dir[1];
            if (xx<0 || xx>=r || yy<0 || yy>=c) continue;
            double ans=cal(map[xx].charAt(yy)=='A'?p:q, times[xx][yy]);
            times[xx][yy]++;
            max=Math.max(max, ans+solve(r,c,xx,yy,s-1,map,times,p,q));
            times[xx][yy]--;
        }
        return max;
    }

    private double cal(double p, int times) {
        return Math.pow(1-p, times)*p;
    }

    public static void main(String[] args) throws Exception {
        //System.setOut(new PrintStream("E:\\desktop\\output.txt"));
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