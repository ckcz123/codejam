import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * APAC 2015 Round C Problem D: Tetris
 * Check README.md for explanation.
 */
public class Main {

    private String solve(Scanner scanner) {
        int w=scanner.nextInt(), h=scanner.nextInt(), n=scanner.nextInt();
        char[][] map=new char[w][h];
        for (int i=0;i<w;i++) Arrays.fill(map[i], '.');
        int[][] mem=new int[n][3];
        for (int i=0;i<n;i++) {
            mem[i]=new int[] {scanner.nextInt(), scanner.nextInt(), scanner.nextInt()};
        }
        int[] height=new int[w];
        for (int[] one: mem) {

            // check height
            for (int i=0;i<w;i++) {
                height[i]=0;
                for (int j=h-1;j>=0;j--) {
                    if (map[i][j]=='x') {
                        height[i]=j+1;
                        break;
                    }
                }
            }

            int x=one[2];
            int[][] block=getBlock(one[0], one[1]);
            int len=block.length, y=0;
            for (int i=0;i<len;i++) {
                y=Math.max(y, height[x+i]-block[i][0]);
            }
            // insert
            for (int i=0;i<len;i++) {
                for (int b: block[i]) {
                    if (y+b>=h) return "\nGame Over!";
                    map[x+i][y+b]='x';
                    height[x+i]=y+b+1;
                }
            }
            // clear
            for (int j=0;j<h;) {
                boolean all=true;
                for (int i=0;i<w;i++) {
                    if (map[i][j]=='.') {
                        all=false;
                        break;
                    }
                }
                if (!all) {j++;continue;}
                // move down
                for (int k=j;k<h-1;k++) {
                    for (int i=0;i<w;i++) {
                        map[i][k]=map[i][k+1];
                    }
                }
                for (int i=0;i<w;i++) map[i][h-1]='.';
            }
        }
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("");
        for (int i=h-1;i>=0;i--) {
            StringBuilder builder=new StringBuilder();
            for (int j=0;j<w;j++)
                builder.append(map[j][i]);
            arrayList.add(builder.toString());
        }
        return String.join("\n", arrayList);
    }

    private int[][] getBlock(int t, int r) {
        if (t==1) {
            if (r==0 || r==2) return new int[][] {{1,2},{0,1}};
            if (r==1 || r==3) return new int[][] {{0},{0,1},{1}};
        }
        if (t==2) {
            if (r==0 || r==2) return new int[][] {{0,1},{1,2}};
            if (r==1 || r==3) return new int[][] {{1},{0,1},{0}};
        }
        if (t==3) {
            if (r==0) return new int[][] {{0,1,2},{0}};
            if (r==1) return new int[][] {{0},{0},{0,1}};
            if (r==2) return new int[][] {{2},{0,1,2}};
            if (r==3) return new int[][] {{0,1},{1},{1}};
        }
        if (t==4) {
            if (r==0) return new int[][] {{0},{0,1,2}};
            if (r==1) return new int[][] {{1},{1},{0,1}};
            if (r==2) return new int[][] {{0,1,2},{2}};
            if (r==3) return new int[][] {{0,1},{0},{0}};
        }
        if (t==5) return new int[][] {{0,1},{0,1}};
        if (t==6) {
            if (r==0 || r==2) return new int[][] {{0,1,2,3}};
            if (r==1 || r==3) return new int[][] {{0},{0},{0},{0}};
        }
        if (t==7) {
            if (r==0) return new int[][] {{0},{0,1},{0}};
            if (r==1) return new int[][] {{1},{0,1,2}};
            if (r==2) return new int[][] {{1},{0,1},{1}};
            if (r==3) return new int[][] {{0,1,2},{1}};
        }
        return new int[0][0];
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=scanner.nextInt();
        long start=System.currentTimeMillis();
        for (int t=1;t<=times;t++) {
            System.out.println(String.format("Case #%d:%s", t, new Main().solve(scanner)));
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}