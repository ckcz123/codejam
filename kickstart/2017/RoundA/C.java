import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * KickStart 2017 Round A Problem C: Space Cubes
 * Check README.md for explanation.
 */
public class Main {

    class Star {
        long x, y, z, r;
        public Star(long _x, long _y, long _z, long _r) {
            x=_x;y=_y;z=_z;r=_r;
        }
    }

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        Star[] stars=new Star[n];
        long[][] corner=new long[3][2];
        corner[0][0]=corner[1][0]=corner[2][0]=Integer.MIN_VALUE;
        corner[0][1]=corner[1][1]=corner[2][1]=Integer.MAX_VALUE;
        for (int i=0;i<n;i++) {
            stars[i]=new Star(scanner.nextLong(), scanner.nextLong(),
                    scanner.nextLong(), scanner.nextLong());
            corner[0][0]=Math.max(corner[0][0], stars[i].x+stars[i].r);
            corner[1][0]=Math.max(corner[1][0], stars[i].y+stars[i].r);
            corner[2][0]=Math.max(corner[2][0], stars[i].z+stars[i].r);
            corner[0][1]=Math.min(corner[0][1], stars[i].x-stars[i].r);
            corner[1][1]=Math.min(corner[1][1], stars[i].y-stars[i].r);
            corner[2][1]=Math.min(corner[2][1], stars[i].z-stars[i].r);
        }
        long start=1, end=Long.MAX_VALUE/10;
        while (start<end) {
            long mid=(start+end)/2;
            if (checkTopLeft(stars, mid, corner) || checkTopRight(stars, mid, corner)
                    || checkBottomLeft(stars, mid, corner) || checkBottomRight(stars, mid, corner))
                end=mid;
            else start=mid+1;
        }
        return String.valueOf(start);
    }

    private boolean checkTopLeft(Star[] stars, long candidate, long[][] corners) {
        for (Star star: stars) {
            if ((star.x-star.r<corners[0][0]-candidate || star.y-star.r<corners[1][0]-candidate
                    || star.z-star.r<corners[2][0]-candidate) && (star.x+star.r>corners[0][1]+candidate
                    || star.y+star.r>corners[1][1]+candidate || star.z+star.r>corners[2][1]+candidate))
                return false;
        }
        return true;
    }

    private boolean checkTopRight(Star[] stars, long candidate, long[][] corners) {
        for (Star star: stars) {
            if ((star.x-star.r<corners[0][0]-candidate || star.y+star.r>corners[1][1]+candidate
                    || star.z-star.r<corners[2][0]-candidate) && (star.x+star.r>corners[0][1]+candidate
                    || star.y-star.r<corners[1][0]-candidate || star.z+star.r>corners[2][1]+candidate))
                return false;
        }
        return true;
    }

    private boolean checkBottomLeft(Star[] stars, long candidate, long[][] corners) {
        for (Star star: stars) {
            if ((star.x+star.r>corners[0][1]+candidate || star.y-star.r<corners[1][0]-candidate
                    || star.z-star.r<corners[2][0]-candidate) && (star.x-star.r<corners[0][0]-candidate
                    || star.y+star.r>corners[1][1]+candidate || star.z+star.r>corners[2][1]+candidate))
                return false;
        }
        return true;
    }

    private boolean checkBottomRight(Star[] stars, long candidate, long[][] corners) {
        for (Star star: stars) {
            if ((star.x+star.r>corners[0][1]+candidate || star.y+star.r>corners[1][1]+candidate
                    || star.z-star.r<corners[2][0]-candidate) && (star.x-star.r<corners[0][0]-candidate
                    || star.y-star.r<corners[1][0]-candidate || star.z+star.r>corners[2][1]+candidate))
                return false;
        }
        return true;
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