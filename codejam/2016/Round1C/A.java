import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    class Letter {
        char c;
        int num;
        public Letter(char _c, int n) {c=_c;num=n;}
    }

    private String solve(Scanner scanner) {
        int n=scanner.nextInt(), total=0;
        PriorityQueue<Letter> priorityQueue=new PriorityQueue<>(Comparator.comparingInt(l->-l.num));
        for (int i=0;i<n;i++) {
            int x=scanner.nextInt();
            total+=x;
            priorityQueue.offer(new Letter((char)('A'+i), x));
        }
        ArrayList<String> ans=new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            StringBuilder builder=new StringBuilder();
            Letter letter=priorityQueue.poll();
            builder.append(letter.c);
            letter.num--;
            if (letter.num>0) priorityQueue.offer(letter);
            total--;
            if (total%2!=0) {
                letter=priorityQueue.poll();
                builder.append(letter.c);
                letter.num--;
                if (letter.num>0) priorityQueue.offer(letter);
                total--;
            }
            ans.add(builder.toString());
        }
        return String.join(" ", ans);
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