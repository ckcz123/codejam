import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2013 Qualification Round
 * Problem C. Fair and Square
 */
public class Main {

    private String solve(Scanner scanner) throws Throwable {
        if (list==null) {
            list=new ArrayList<>();
            for (int l=1;l<=51;l++) {
                list.addAll(build(l,9,false));
            }
            System.err.println("Size: "+list.size());
        }
        String a=scanner.next(), b=scanner.next();
        return String.valueOf(getSize(new BigInteger(b))
                -getSize(new BigInteger(a).subtract(BigInteger.ONE)));
    }

    private static ArrayList<String> list=null;

    private long getSize(BigInteger k) {
        long cnt=0;
        int klen=k.toString().length();
        for (String s: list) {
            int l=2*s.length()-1;
            if (l>klen) return cnt;
            else if (l<klen) cnt++;
            else {
                BigInteger bigInteger=new BigInteger(s);
                BigInteger square=bigInteger.multiply(bigInteger);
                if (square.compareTo(k)<=0) cnt++;
            }
        }
        return cnt;
    }

    private ArrayList<String> build(int len, int left, boolean zero) {
        if (len==0) return new ArrayList<>(Arrays.asList(""));
        ArrayList<String> list=new ArrayList<>();
        if (len==1) {
            for (int i=zero?0:1;i*i<=left;i++)
                list.add(String.valueOf(i));
            return list;
        }
        for (int i=zero?0:1;2*i*i<=left;i++) {
            ArrayList<String> list1=build(len-2, left-2*i*i, true);
            for (String s: list1)
                list.add(i+s+i);
        }
        return list;
    }

    private boolean isPalindrome(BigInteger x) {
        String s=x.toString();
        return s.equals(new StringBuilder(s).reverse().toString());
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
            }
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}