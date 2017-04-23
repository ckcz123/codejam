import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), x=scanner.nextInt();
        int[] s=new int[n];
        for (int i=0;i<n;i++) s[i]=scanner.nextInt();
        Arrays.sort(s);
        LinkedList<Integer> linkedList=new LinkedList<>(Arrays.stream(s).boxed().collect(Collectors.toList()));
        int cnt=0;
        while (linkedList.size()>0) {
            int mx=linkedList.pollLast();
            cnt++;
            if (linkedList.size()>0 && linkedList.peekFirst()+mx<=x)
                linkedList.pollFirst();
        }
        return String.valueOf(cnt);
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