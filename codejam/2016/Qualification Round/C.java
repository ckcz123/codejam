import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Codejam 2016 Qualification Round
 * Problem C. Coin Jam
 */
public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), j=scanner.nextInt();
        char[] chars=new char[n];
        Arrays.fill(chars, '0');
        chars[0]='1'; chars[n-1]='1';
        long start=Long.parseLong(new String(chars), 2);
        ArrayList<String> ans=new ArrayList<>();
        for (;j!=0;start+=2) {
            String s=Long.toBinaryString(start);
            int[] res=check(s);
            if (res!=null) {
                ans.add(s+" "+String.join(" ", IntStream.of(res).boxed().map(String::valueOf).collect(Collectors.toList())));
                j--;
            }
        }
        return "\n"+String.join("\n", ans);
    }

    private int[] check(String s) {
        int[] primes=new int[]{2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97};
        int[] ans=new int[9];
        for (int base=2;base<=10;base++) {
            BigInteger bigInteger=new BigInteger(s, base);
            for (int p: primes) {
                if (bigInteger.mod(BigInteger.valueOf(p)).equals(BigInteger.ZERO)) {
                    ans[base-2]=p;
                }
            }
            if (ans[base-2]==0) return null;
        }
        return ans;
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