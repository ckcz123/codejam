import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        int d=scanner.nextInt(), n=scanner.nextInt();
        char[] allIs=new char[15], allOs=new char[15];
        Arrays.fill(allIs, 'I');
        Arrays.fill(allOs, 'O');
        ArrayList<String> arrayList=new ArrayList<>();
        int three=Math.min(13*7, n/3);
        int one=n-three*3;
        for (int line=0;line<15;line++) {
            if (line%4==0) arrayList.add(new String(allIs));
            else if (line%4==2) arrayList.add(new String(allOs));
            else {
                char[] tmp=Arrays.copyOf(allIs, 15);
                if (one-->0)
                    tmp[0]='/';
                for (int j=1;j<14;j++) {
                    if (three-->0)
                        tmp[j]='/';
                }
                if (one-->0)
                    tmp[14]='/';
                arrayList.add(new String(tmp));
            }
        }
        return "\n"+String.join("\n", arrayList);
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