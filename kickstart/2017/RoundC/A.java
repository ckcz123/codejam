import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * KickStart 2017 Round C
 * Problem A. Ambiguous Cipher
 */
public class Main {

    public String solve(Scanner scanner) {
        String s=scanner.next();
        if (s.length()%2!=0) return "AMBIGUOUS";
        char[] chars=new char[s.length()];
        for (int i=1;i<s.length();i+=2) {
            if (i==1) chars[i]=s.charAt(i-1);
            else {
                // chars[i-2]+chars[i]=s[i-1]
                // chars[i]=s[i]-chars[i-2]
                chars[i]=i2c((c2i(s.charAt(i-1))-c2i(chars[i-2])+26)%26);
            }
        }
        for (int i=s.length()-2;i>=0;i-=2) {
            if (i==s.length()-2) chars[i]=s.charAt(i+1);
            else {
                chars[i]=i2c((c2i(s.charAt(i+1))-c2i(chars[i+2])+26)%26);
            }
        }
        return new String(chars);
    }

    private char i2c(int i) {
        return (char)('A'+i);
    }
    private int c2i(char c) {
        return c-'A';
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=Integer.parseInt(scanner.nextLine());
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