import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private String solve(Scanner scanner) throws Throwable {
        char[][] chars=new char[4][];
        for (int i=0;i<4;i++) chars[i]=scanner.next().toCharArray();
        for (int i=0;i<4;i++) {
            char c=check(chars[i][0], chars[i][1], chars[i][2], chars[i][3]);
            if (c!=0) return String.format("%c won", c);
            c=check(chars[0][i], chars[1][i], chars[2][i], chars[3][i]);
            if (c!=0) return String.format("%c won", c);
        }
        char c=check(chars[0][0], chars[1][1], chars[2][2], chars[3][3]);
        if (c!=0) return String.format("%c won", c);
        c=check(chars[0][3], chars[1][2], chars[2][1], chars[3][0]);
        if (c!=0) return String.format("%c won", c);
        for (int i=0;i<16;i++) if (chars[i/4][i%4]=='.') return "Game has not completed";
        return "Draw";
    }

    private char check(char c1, char c2, char c3, char c4) {
        String s= String.valueOf(c1) + c2 + c3 + c4;
        if ("XXXX".equals(s.replace('T','X'))) return 'X';
        if ("OOOO".equals(s.replace('T','O'))) return 'O';
        return 0;
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