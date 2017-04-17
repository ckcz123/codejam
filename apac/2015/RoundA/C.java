import java.io.PrintStream;
import java.util.*;

public class Main {

    class Varible {
        int father, flag, constant;
        double val;
        public Varible(int _father, int _flag, int _constant) {
            father=_father;
            flag=_flag;
            constant=_constant;
            val=1e9;
        }
    }

    public String solve(Scanner scanner) {
        int m=scanner.nextInt(), n=0;
        String[][] equations=new String[m][3];
        HashMap<String, Integer> map=new HashMap<>();
        for (int i=0;i<m;i++) {
            String s=scanner.next();
            String[] ss=s.split("[+=]");
            String x=ss[0], y=ss[1], v=ss[2];
            if (!map.containsKey(x)) map.put(x, n++);
            if (!map.containsKey(y)) map.put(y, n++);
            equations[i]=new String[] {x, y, v};
        }
        ArrayList<int[]>[] lists=new ArrayList[n];
        for (int i=0;i<n;i++) lists[i]=new ArrayList<>();
        for (String[] equation: equations) {
            int x=map.get(equation[0]), y=map.get(equation[1]),
                    v=Integer.parseInt(equation[2]);
            lists[x].add(new int[] {y, v});
            lists[y].add(new int[] {x, v});
        }

        Varible[] varibles=new Varible[n];
        for (int i=0;i<n;i++) {
            if (varibles[i]!=null) continue;
            varibles[i]=new Varible(i, 1, 0);
            Queue<Integer> queue=new LinkedList<>();
            queue.offer(i);
            while (!queue.isEmpty()) {
                int curr=queue.poll();
                for (int[] val: lists[curr]) {
                    int id=val[0];
                    int flag=-varibles[curr].flag;
                    int constant=val[1]-varibles[curr].constant;
                    if (varibles[id]!=null) {
                        if (varibles[id].father!=varibles[curr].father)
                            System.err.println(String.format("Father Error! id=%d, father=%d, should=%d",
                                    id, varibles[id].father, varibles[curr].father));
                        if (varibles[id].flag==flag) {
                            if (varibles[id].constant!=constant)
                                System.err.println(String.format("Constant Error! id=%d, " +
                                                "father=%d, constant=%d, should=%d",
                                        id, varibles[id].father,
                                        varibles[id].constant, varibles[curr].constant));
                        }
                        else {
                            // calculate
                            varibles[varibles[id].father].val
                                    =(constant-varibles[id].constant+.0)/(varibles[id].flag-flag);
                        }
                    }
                    else {
                        varibles[id]=new Varible(varibles[curr].father, flag, constant);
                        queue.offer(id);
                    }
                }
            }
        }

        int q=scanner.nextInt();
        ArrayList<String> ans=new ArrayList<>();
        ans.add("");
        while (q-->0) {
            String s=scanner.next();
            String[] ss=s.split("\\+");
            String x=ss[0], y=ss[1];
            if (!map.containsKey(x) || !map.containsKey(y)) continue;
            int u=map.get(x), v=map.get(y);
            // check value
            int fu=varibles[u].father, fv=varibles[v].father;
            //if (fu!=fv) continue;
            if (varibles[fu].val!=1e9
                    || varibles[fv].val!=1e9) {

                if (varibles[fu].val==1e9
                        || varibles[fv].val==1e9)
                    continue;
                ans.add(s+"="+(int)((varibles[u].constant+varibles[u].flag*varibles[fu].val
                        +varibles[v].constant+varibles[v].flag*varibles[fv].val)));
            }
            else {
                if (fu!=fv) continue;
                if (varibles[u].flag==varibles[v].flag) continue;
                ans.add(s+"="+(varibles[u].constant+varibles[v].constant));
            }
        }
        return String.join("\n", ans);
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
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.));
    }
}
