import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2017 Round 3
 * Problem B. Good News and Bad News
 * Only small solved.
 */
public class Main {

    class Equation {
        ArrayList<Integer> left, right;
        int maxindex;
        public Equation() {left=new ArrayList<>();right=new ArrayList<>();maxindex=-1;}
        public void addLeft(int x) {left.add(x); maxindex=Math.max(maxindex, x);}
        public void addRight(int x) {right.add(x); maxindex=Math.max(maxindex, x);}
        public boolean satisfy(int index, int[] b) {
            if (index<maxindex) return true;
            int sl=0, sr=0;
            for (int x: left) sl+=b[x];
            for (int x: right) sr+=b[x];
            return sl==sr;
        }
    }

    public String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int f=scanner.nextInt(), q=scanner.nextInt();
        int[][] link=new int[q][2];
        for (int i=0;i<q;i++) link[i]=new int[] {scanner.nextInt()-1, scanner.nextInt()-1};
        Equation[] equations=new Equation[f];
        for (int i=0;i<f;i++) equations[i]=new Equation();
        for (int i=0;i<q;i++) {
            equations[link[i][0]].addLeft(i);
            equations[link[i][1]].addRight(i);
        }
        int[] val=new int[q];
        return dfs(q, equations, val, 0)?String.join(" ",
                        Arrays.stream(val).boxed().map(String::valueOf).collect(Collectors.toList())):
                "IMPOSSIBLE";
    }

    private boolean dfs(int q, Equation[] equations, int[] val, int curr) {
        if (curr>=q) return true;
        for (int x: new int[] {1,-1,2,-2,3}) {
            val[curr]=x;
            boolean able=true;
            for (Equation equation: equations) {
                if (!equation.satisfy(curr, val)) {
                    able=false;break;
                }
            }
            if (able && dfs(q, equations, val, curr+1))
                return true;
        }
        return false;
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

