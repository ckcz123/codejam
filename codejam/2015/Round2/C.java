import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int n=scanner.nextInt(); scanner.nextLine();
        HashMap<String, ArrayList<Integer>> map=new HashMap<>();
        for (int i=0;i<n;i++) {
            String s=scanner.nextLine();
            String[] words=s.split(" ");
            for (String word: words) {
                ArrayList<Integer> arrayList=map.getOrDefault(word, new ArrayList<>());
                arrayList.add(i);
                map.put(word, arrayList);
            }
        }
        List<ArrayList<Integer>> lists=map.values().stream().filter(i->i.size()>1).collect(Collectors.toList());
        int min=Integer.MAX_VALUE, must=(int)
                lists.stream().filter(i->i.contains(0)&&i.contains(1)).count();
        lists=lists.stream().filter(i->!(i.contains(0)&&i.contains(1))).collect(Collectors.toList());

        for (int i=2;i<(1<<n);i+=4) {
            int cnt=0;
            for (ArrayList<Integer> arrayList: lists) {
                int color=0;
                for (int v: arrayList) {
                    if ((i&(1<<v))!=0) color|=1;
                    else color|=2;
                    if (color==3) break;
                }
                if (color==3) cnt++;
            }
            min=Math.min(min, cnt+must);
        }
        return String.valueOf(min);
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
