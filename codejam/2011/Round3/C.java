import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2011 Round 3
 * Problem C. Perpetual Motion
 * Only small solved.
 */
public class Main {

    public String solve(Scanner scanner) {
        int r= scanner.nextInt(), c= scanner.nextInt();
        char[][] map = new char[r][c];
        for(int i=0; i<r; i++)
            map[i]=scanner.next().toCharArray();
        int n=1<<(r*c);
        long ans=0;
        for(int k=0; k<n; k++){
            if (able(r,c,map,k)) ans++;
        }
        return String.valueOf(ans);
    }

    private boolean able(int r, int c, char[][] map, int k) {
        HashSet<Integer> set=new HashSet<>();
        for (int i=0;i<r;i++) {
            for (int j=0;j<c;j++) {
                int index=i*c+j;
                int x=i,y=j;
                if ((k&(1<<index))==0) {
                    switch (map[i][j]) {
                        case '|': x=i-1;break;
                        case '-': y=j-1;break;
                        case '/': x=i-1;y=j+1;break;
                        case '\\': x=i-1;y=j-1;break;
                    }
                }
                else {
                    switch (map[i][j]) {
                        case '|': x=i+1;break;
                        case '-': y=j+1;break;
                        case '/': x=i+1;y=j-1;break;
                        case '\\': x=i+1;y=j+1;break;
                    }
                }
                if (x<0) x+=r; if (y<0) y+=c;
                if (x>=r) x-=r; if (y>=c) y-=c;
                if (!set.add(x*c+y)) return false;
            }
        }
        return true;
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