import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {


    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        char[][] chars=new char[n][n];
        for (int i=0;i<n;i++) chars[i]=scanner.next().toCharArray();
        int[][] nums=new int[n][n];
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (chars[i][j]=='*') nums[i][j]=-1;
                else {
                    for (int x=i-1;x<=i+1;x++) {
                        for (int y=j-1;y<=j+1;y++) {
                            if (x>=0 && x<n && y>=0 && y<n && chars[x][y]=='*')
                                nums[i][j]++;
                        }
                    }
                }
            }
        }
        int cnt=0;
        while (true) {
            // check 0
            int zx=-1, zy=-1;
            for (int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    if (nums[i][j]==0) {
                        zx=i;zy=j;break;
                    }
                }
                if (zx!=-1) break;
            }
            if (zx==-1) break;
            cnt++;
            Queue<Integer> queue=new LinkedList<>();
            queue.offer(zx); queue.offer(zy);
            nums[zx][zy]=-1;
            while (!queue.isEmpty()) {
                int x=queue.poll(), y=queue.poll();
                for (int dx=x-1;dx<=x+1;dx++) {
                    for (int dy=y-1;dy<=y+1;dy++) {
                        if (dx>=0 && dx<n && dy>=0 && dy<n && nums[dx][dy]!=-1) {
                            if (nums[dx][dy]==0) {queue.offer(dx); queue.offer(dy);}
                            nums[dx][dy]=-1;
                        }
                    }
                }
            }
        }
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (nums[i][j]!=-1)
                    cnt++;
            }
        }
        return String.valueOf(cnt);
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