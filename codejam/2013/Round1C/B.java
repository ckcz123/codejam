import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2013 Round 1C
 * Problem B. Pogo
 */
public class Main {

    public String solve(Scanner sc) {
        int x=sc.nextInt(), y=sc.nextInt();
        int sum=0, n=0;
        while (sum<Math.abs(x)+Math.abs(y) || (sum+x+y)%2!=0) {
            sum+=++n;
        }
        StringBuilder builder=new StringBuilder();
        while (n>0) {
            if (Math.abs(x)>=Math.abs(y)) {
                if (x<0) {
                    builder.append('W');x+=n;
                }
                else {
                    builder.append('E');x-=n;
                }
            }
            else {
                if (y<0) {
                    builder.append('S'); y+=n;
                }
                else {
                    builder.append('N');y-=n;
                }
            }
            n--;
        }
        return builder.reverse().toString();
    }

    public static void main(String[] args) throws  Exception{
        System.setOut(new PrintStream("output.txt"));
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();
        for(int i=1; i<=times; i++){
//            System.out.println(String.format("Case #%d: %s", i, new Main().solve(sc)));
            try {
                System.out.println(String.format("Case #%d: %s", i, new Main().solve(sc)));
            }catch (Exception e){
                System.err.println(String.format("error at case#%d", i));
            }
        }
    }

}
