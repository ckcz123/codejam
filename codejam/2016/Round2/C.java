import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2016 Round 2
 * Problem C. The Gardener of Seville
 * Only small solved.
 */
public class Main {

    private String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int r=scanner.nextInt(), c=scanner.nextInt();
        int[] map=new int[2*(r+c)+1];
        for (int i=0;i<r+c;i++) {
            int x=scanner.nextInt(), y=scanner.nextInt();
            map[x]=y; map[y]=x;
        }
        for (int x=0;x<(1<<(r*c));x++) {
            HashMap<String, String> hashMap=new HashMap<>();
            for (int i=0;i<r;i++) {
                for (int j=0;j<c;j++) {
                    int block=i*c+j;
                    if ((x&(1<<block))==0) {
                        // '/'
                        link("-,"+i+","+j, "|,"+i+","+j, hashMap);
                        link("-,"+(i+1)+","+j, "|,"+i+","+(j+1), hashMap);
                    }
                    else {
                        // '\'
                        link("-,"+i+","+j, "|,"+i+","+(j+1), hashMap);
                        link("-,"+(i+1)+","+j, "|,"+i+","+j, hashMap);
                    }
                }
            }
            // check
            boolean able=true;
            for (int i=1;i<=2*(r+c);i++) {
                int v=map[i];
                String s=build(i, r, c), y=build(v, r, c);
                if (!findfather(s, hashMap).equals(findfather(y, hashMap))) {
                    able=false;break;
                }
            }
            if (able) {
                ArrayList<String> ans=new ArrayList<>();
                ans.add("");
                for (int i=0;i<r;i++) {
                    char[] chars=new char[c];
                    for (int j=0;j<c;j++) {
                        int block=i*c+j;
                        if ((x&(1<<block))==0) {
                            chars[j]='/';
                        }
                        else chars[j]='\\';
                    }
                    ans.add(new String(chars));
                }
                return String.join("\n", ans);
            }
        }
        return "\nIMPOSSIBLE";
    }

    private String build(int x, int r, int c) {
        if (x>=1 && x<=c)
            return "-,0,"+(x-1);
        if (x>=c+1 && x<=c+r)
            return "|,"+(x-c-1)+","+c;
        if (x>=c+r+1 && x<=c+r+c)
            return "-,"+r+","+(c+c+r-x);
        return "|,"+(2*r+2*c-x)+",0";
    }

    private String findfather(String s, HashMap<String, String> map) {
        if (s.equals(map.getOrDefault(s, s))) return s;
        String father=findfather(map.get(s), map);
        map.put(s, father);
        return father;
    }

    private void link(String x, String y, HashMap<String, String> map) {
        map.put(findfather(x, map), findfather(y, map));
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