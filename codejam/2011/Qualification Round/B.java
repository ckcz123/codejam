import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2011 Qualification Round
 * Problem B. Magicka
 */
public class Main {

    public String solve(Scanner scanner) {
        char[][] combine=new char[128][128];
        int c=scanner.nextInt();
        while (c-->0) {
            char[] chars=scanner.next().toCharArray();
            combine[chars[0]][chars[1]]=combine[chars[1]][chars[0]]=chars[2];
        }
        boolean[][] clear=new boolean[128][128];
        c=scanner.nextInt();
        while (c-->0) {
            char[] chars=scanner.next().toCharArray();
            clear[chars[0]][chars[1]]=clear[chars[1]][chars[0]]=true;
        }
        int l=scanner.nextInt();
        char[] chars=scanner.next().toCharArray();
        LinkedList<Character> linkedList=solve(l, 0, chars, new LinkedList<>(), combine, clear);
        return "["+String.join(", ",
                linkedList.stream().map(String::valueOf).collect(Collectors.toList()))+"]";

    }

    public LinkedList<Character> solve(int l, int curr, char[] chars, LinkedList<Character> linkedList,
                                       char[][] combine, boolean[][] clear) {
        // check combine
        if (linkedList.size()>=2) {
            char c1=linkedList.pollLast(), c2=linkedList.peekLast();
            if (combine[c1][c2]!=0) {
                linkedList.pollLast(); linkedList.offerLast(combine[c1][c2]);
                return solve(l, curr, chars, linkedList, combine, clear);
            }
            else linkedList.offerLast(c1);
        }
        // check clear
        if (linkedList.size()>1) {
            char c1=linkedList.peekLast();
            for (Character c: linkedList) {
                if (clear[c][c1]) {
                    linkedList.clear();
                    break;
                }
            }
        }
        if (curr==l) return linkedList;
        linkedList.offerLast(chars[curr]);
        return solve(l, curr+1, chars, linkedList, combine, clear);
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
                e.printStackTrace();
            }
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}