import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private String solve(Scanner scanner) {
        String[] clock=new String[] {"1111011", "1111111", "1110000", "1011111",
                "1011011", "0110011", "1111001", "1101101", "0110000", "1111110"};
        int[] nums=Arrays.stream(clock).mapToInt(s->Integer.parseInt(s, 2)).toArray();
        int n=scanner.nextInt();
        int[] data=new int[n];
        for (int i=0;i<n;i++) data[i]=Integer.parseInt(scanner.next(), 2);
        HashSet<String> set=new HashSet<>();
        for (int i=0;i<=nums[1];i++) {
            int[] curr=new int[10];
            for (int j=0;j<10;j++) curr[j]=nums[j]&i;
            set.addAll(check(curr, data));
        }
        return set.size()==1?set.iterator().next():"ERROR!";
    }

    private HashSet<String> check(int[] nums, int[] data) {
        HashSet<String> set=new HashSet<>();
        for (int i=0;i<=9;i++) {
            boolean able=true;
            for (int j=0;j<data.length;j++) {
                if (data[j]!=nums[(i+j)%10]) {
                    able=false;
                    break;
                }
            }
            if (able) {
                set.add(toString(nums[(i+data.length)%10]));
            }
        }
        return set;
    }

    private String toString(int v) {
        StringBuilder builder=new StringBuilder(Integer.toBinaryString(v));
        while (builder.length()<7) builder.insert(0, '0');
        return builder.toString();
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