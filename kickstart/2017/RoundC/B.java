import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * KickStart 2017 Round C Problem B. X Squared
 * Check README.md for explanation.
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        char[][] map=new char[n][n];
        for (int i=0;i<n;i++) map[i]=scanner.next().toCharArray();
        return check1(n, map)&&check2(n, map)?"POSSIBLE":"IMPOSSIBLE";
    }

    private boolean check1(int n, char[][] map) {
        for (int i=0;i<n;i++) {
            int u=0,v=0;
            for (int j=0;j<n;j++) {
                if (map[i][j]=='X') u++;
                if (map[j][i]=='X') v++;
                if (u>2 || v>2) return false;
            }
        }
        return true;
    }

    private boolean check2(int n, char[][] map) {
        boolean[] visited=new boolean[n];
        ArrayList<Integer>[] lists=new ArrayList[n];

        for (int i=0;i<n;i++) {
            ArrayList<Integer> list=new ArrayList<>();
            for (int j=0;j<n;j++) {
                if (map[i][j]=='X') list.add(j);
            }
            if (list.size()>=3 || list.isEmpty()) return false;
            lists[i]=list;
        }

        for (int i=0;i<n;i++) {
            if (visited[i] || lists[i].size()==1) continue;
            visited[i]=true;
            boolean has=false;
            for (int j=0;j<n;j++) {
                if (!visited[j] && lists[j].equals(lists[i])) {
                    visited[j]=true;
                    has=true;
                    break;
                }
            }
            if (!has) return false;
        }
        return true;
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