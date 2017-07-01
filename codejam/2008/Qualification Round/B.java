import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2008 Qualification Round
 * Problem B. Train Timetable
 */
public class Main {

    public String solve(Scanner scanner) {
        int t=scanner.nextInt(), m=scanner.nextInt(), n=scanner.nextInt(), tot=m+n;
        int[][] a=new int[m][2], b=new int[n][2];
        for (int i=0;i<m;i++) a[i]=new int[] {getTime(scanner.next()), getTime(scanner.next())};
        for (int i=0;i<n;i++) b[i]=new int[] {getTime(scanner.next()), getTime(scanner.next())};
        Arrays.sort(a, Comparator.comparingInt(o->o[0]));
        Arrays.sort(b, Comparator.comparingInt(o->o[0]));
        boolean[][] linked=new boolean[tot][tot];
        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                if (a[i][0]>=b[j][1]+t) linked[j+m][i]=true;
                if (b[j][0]>=a[i][1]+t) linked[i][j+m]=true;
            }
        }
        boolean[] visited=new boolean[tot];
        int va=0, vb=0;
        while (true) {
            int index=-1;
            for (int i=0;i<tot;i++) {
                if (visited[i]) continue;
                boolean has=false;
                for (int j=0;j<tot;j++) {
                    if (!visited[j] && linked[j][i]) {
                        has=true;break;
                    }
                }
                if (!has) {
                    index=i;
                    if (index<m) va++;
                    else vb++;
                    break;
                }
            }
            if (index==-1) break;

            visited[index]=true;
            while (true) {
                int next=-1;
                for (int i=0;i<tot;i++) {
                    if (!visited[i] && linked[index][i]) {
                        next=i;break;
                    }
                }
                if (next==-1) break;
                visited[next]=true;
                index=next;
            }
        }
        return va+" "+vb;
    }

    private int getTime(String time) {
        String[] s=time.split(":");
        return Integer.parseInt(s[0])*60+Integer.parseInt(s[1]);
    }

    public static void main(String[] args) throws Exception {
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