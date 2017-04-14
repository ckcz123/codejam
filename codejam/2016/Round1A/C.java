import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt();
        int[] f=new int[n];
        ArrayList<Integer>[] lists=new ArrayList[n];
        for (int i=0;i<n;i++) {
            lists[i]=new ArrayList<>();
        }
        for (int i=0;i<n;i++) {
            int x=scanner.nextInt()-1;
            f[i]=x;
            lists[x].add(i);
        }
        int ans=0;
        for (int i=0;i<n;i++)
            ans=Math.max(ans, calCycleLength(i, f));
        PriorityQueue<Integer> priorityQueue=new PriorityQueue<>((o1,o2)->o2-o1);
        for (int i=0;i<n;i++)
            if (f[f[i]]==i && f[i]<i)
                priorityQueue.add(calBiLength(i, f[i], lists));
        ans=Math.max(ans, priorityQueue.stream().mapToInt(i->i).sum());
        return String.valueOf(ans);
    }

    private int calCycleLength(int s, int[] f) {
        ArrayList<Integer> arrayList=new ArrayList<>();
        while (true) {
            int index=arrayList.indexOf(s);
            if (index!=-1)
                return arrayList.size()-index;
            arrayList.add(s);
            s=f[s];
        }
    }

    private int calBiLength(int s, int e, ArrayList<Integer>[] lists) {
        return depth(s, e, lists)+depth(e, s, lists);
    }

    private int depth(int s, int e, ArrayList<Integer>[] lists) {
        int x=1;
        for (int y: lists[s]) {
            if (y!=e)
                x=Math.max(x, 1+depth(y, s, lists));
        }
        return x;
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