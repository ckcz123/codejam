import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), m=scanner.nextInt();
        int[] zip=new int[n];
        for (int i=0;i<n;i++)
            zip[i]=scanner.nextInt();
        boolean[][] linked=new boolean[n][n];
        while (m-->0) {
            int x=scanner.nextInt()-1, y=scanner.nextInt()-1;
            linked[x][y]=linked[y][x]=true;
        }
        StringBuilder builder=new StringBuilder();
        boolean[] visited=new boolean[n+1];
        LinkedList<Integer> stack=new LinkedList<>();

        int min=Integer.MAX_VALUE, start=-1;
        for (int i=0;i<n;i++) {
            if (zip[i]<min) {
                min=zip[i];start=i;
            }
        }
        stack.push(start);
        builder.append(min);

        for (int k=1;k<n;k++) {
            int v=next(n, zip, linked, visited, stack);
            while (!stack.isEmpty() && !linked[stack.peek()][v])
                visited[stack.pop()]=true;
            stack.push(v);
            builder.append(zip[v]);
        }
        return builder.toString();
    }

    private int next(int n, int[] zip, boolean[][] linked, boolean[] visited, LinkedList<Integer> stack) {
        LinkedList<Integer> tmp=new LinkedList<>(stack);
        boolean[] disable=new boolean[n];
        for (int i=0;i<n;i++) disable[i]=visited[i];
        int candidate=-1, min=Integer.MAX_VALUE;
        while (!tmp.isEmpty()) {
            if (!able(n, linked, disable, tmp)) break;
            int u=tmp.peek();
            for (int v=0;v<n;v++) {
                if (!disable[v] && linked[u][v] && !tmp.contains(v)) {
                    if (zip[v]<min) {
                        min=zip[v];
                        candidate=v;
                    }
                }
            }
            disable[tmp.pop()]=true;
        }
        return candidate;
    }

    private boolean able(int n, boolean[][] linked, boolean[] visited, LinkedList<Integer> stack) {
        boolean[] able=new boolean[n+1];
        for (int k: stack) able[k]=true;
        Queue<Integer> queue=new LinkedList<>();
        for (int k: stack) queue.offer(k);
        while (!queue.isEmpty()) {
            int u=queue.poll();
            for (int v=0;v<n;v++) {
                if (!linked[u][v] || visited[v] || able[v]) continue;
                able[v]=true;
                queue.offer(v);
            }
        }
        for (int i=0;i<n;i++) {
            if (!visited[i] && !able[i])
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