import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2016 Round 3
 * Problem C. Rebel Against The Empire
 */
public class Main {

    private String solve(Scanner scanner) throws Throwable {
        return solveSmall(scanner);
    }

    class Asteroid {
        int x,y,z,vx,vy,vz;
        public Asteroid(int _x, int _y, int _z, int _vx, int _vy, int _vz) {
            x=_x;y=_y;z=_z;vx=_vx;vy=_vy;vz=_vz;
        }
        public double calDistance(Asteroid another) {
            double dx=x-another.x, dy=y-another.y, dz=z-another.z;
            return Math.sqrt(dx*dx+dy*dy+dz*dz);
        }
    }

    private String solveSmall(Scanner scanner) {
        int n=scanner.nextInt(), s=scanner.nextInt();
        Asteroid[] asteroids=new Asteroid[n];
        for (int i=0;i<n;i++) {
            asteroids[i]=new Asteroid(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(),
                    scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        }
        double[][] distance=new double[n][n];
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                distance[i][j]=asteroids[i].calDistance(asteroids[j]);
            }
        }
        for (int k=0;k<n;k++) {
            for (int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    distance[i][j]=Math.min(distance[i][j], Math.max(distance[i][k], distance[k][j]));
                }
            }
        }
        return String.format("%.9f", distance[0][1]);
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