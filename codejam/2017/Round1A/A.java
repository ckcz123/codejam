import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private String solve(Scanner scanner) {
        int r=scanner.nextInt(), c=scanner.nextInt();
        char[][] chars=new char[r][c];
        for (int i=0;i<r;i++)
            chars[i]=scanner.next().toCharArray();

        // top to bottom
        for (int i=0;i<r;i++) {
            for (int j=0;j<c;j++) {
                if (chars[i][j]=='?' && i>0 && chars[i-1][j]!='?')
                    chars[i][j]=chars[i-1][j];
            }
        }
        // top to bottom
        for (int i=r-1;i>=0;i--) {
            for (int j=0;j<c;j++) {
                if (chars[i][j]=='?' && i<r-1 && chars[i+1][j]!='?')
                    chars[i][j]=chars[i+1][j];
            }
        }
        for (int i=0;i<r;i++) {
            for (int j=0;j<c;j++) {
                if (chars[i][j]=='?' && j>0 && chars[i][j-1]!='?')
                    chars[i][j]=chars[i][j-1];
            }
        }
        for (int i=0;i<r;i++) {
            for (int j=c-1;j>=0;j--) {
                if (chars[i][j]=='?' && j<c-1 && chars[i][j+1]!='?')
                    chars[i][j]=chars[i][j+1];
            }
        }
        return "\n"+String.join("\n", Arrays.stream(chars).map(String::valueOf).collect(Collectors.toList()));
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