import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Codejam 2011 Round 1B
 * Problem C. House of Kittens
 * Only small solved.
 */
public class Main {

    public String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int n=scanner.nextInt(), m=scanner.nextInt();
        LinkedList<HashSet<Integer>> linkedList=new LinkedList<>();
        linkedList.add(new HashSet<>(IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList())));
        int[] from=new int[m], to=new int[m];
        for (int i=0;i<m;i++) from[i]=scanner.nextInt();
        for (int i=0;i<m;i++) to[i]=scanner.nextInt();
        for (int i=0;i<m;i++) {
            int u=from[i], v=to[i];
            if (u>v) {int tmp=u;u=v;v=tmp;}
            LinkedList<HashSet<Integer>> next=new LinkedList<>();
            while (!linkedList.isEmpty()) {
                HashSet<Integer> set=linkedList.poll();
                if (set.contains(u) && set.contains(v)) {
                    HashSet<Integer> st1=new HashSet<>(), st2=new HashSet<>();
                    for (Integer x: set) {
                        if (x>=u && x<=v) st1.add(x);
                        if (x<=u || x>=v) st2.add(x);
                    }
                    next.offer(st1); next.offer(st2);
                }
                else next.offer(set);
            }
            linkedList=next;
        }
        if (linkedList.size()==1) {
            return n+"\n"+String.join(" ",
                    linkedList.peek().stream().sorted()
                            .map(String::valueOf).collect(Collectors.toList()));
        }
        int mx=n;
        for (HashSet<Integer> set: linkedList) mx=Math.min(mx, set.size());
        for (int i=mx;i>=1;i--) {
            for (ArrayList<Integer> list: generate(n,i,new ArrayList<>(), new ArrayList<>())) {

                // check
                boolean able=true;
                for (HashSet<Integer> set: linkedList) {
                    boolean[] used=new boolean[i+1];
                    used[0]=true;
                    for (int v: set)
                        used[list.get(v-1)]=true;
                    for (int v=1;v<=i;v++) {
                        if (!used[v]) used[0]=false;
                    }
                    if (!used[0]) {
                        able=false;break;
                    }
                }

                if (able) {
                    return i+"\n"+ String.join(" ", list.stream()
                            .map(String::valueOf).collect(Collectors.toList()));
                }
            }
        }
        return "";
    }

    private ArrayList<ArrayList<Integer>> generate(int n, int m, ArrayList<Integer> list,
                                                   ArrayList<ArrayList<Integer>> ans) {
        if (list.size()==n) {
            ans.add(list);
            return ans;
        }
        for (int i=1;i<=m;i++) {
            ArrayList<Integer> list1=new ArrayList<>(list);
            list1.add(i);
            generate(n,m,list1,ans);
        }
        return ans;
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