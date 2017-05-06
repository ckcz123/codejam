import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2013 Round 1C
 * Problem A. Consonants
 */
public class Main {

    public String solve(Scanner scanner) throws Exception {
        String s=scanner.next(); int l=s.length(), n=scanner.nextInt();
        TreeSet<Integer> set=new TreeSet<>();
        int[] cnt=new int[l+1];
        cnt[l]=0;
        for (int i=l-1;i>=0;i--) {
            if (isConsonant(s.charAt(i))) cnt[i]=cnt[i+1]+1;
        }
        for (int i=0;i<l+1-n;i++) {
            if (cnt[i]>=n) set.add(i);
        }
        long ans=0;
        for (int i=l-1;i>=n-1;i--) {
            Integer v=set.floor(i-n+1);
            if (v!=null)
                ans+=v+1;
        }
        return String.valueOf(ans);
    }

    private boolean isConsonant(char c) {
        return c!='a' && c!='e' && c!='i' && c!='o' && c!='u';
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
//        Scanner scanner=new Scanner(System.in);
        Scanner scanner=new Scanner(new File("input.txt"));
        int times=scanner.nextInt();
        long start=System.currentTimeMillis();
        for (int t=1;t<=times;t++) {
            try {
                System.out.println(String.format("Case #%d: %s", t, new Main().solve(scanner)));
            }
            catch (Exception e) {System.err.println("ERROR #"+t);e.printStackTrace();}
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.));
    }

}