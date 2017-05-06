import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2013 Round 2
 * Problem A. Ticket Swapping
 */
public class Main {

    class Card implements Comparable<Card>{
        int index,state,ori;
        long cnt;
        public Card(int _index, long _cnt, int _state,int _ori) {
            index=_index;cnt=_cnt;state=_state;
            ori=_ori;
        }
        public int compareTo(Card another) {
            return index==another.index?state-another.state:index-another.index;
        }
    }

    public String solve(Scanner scanner) {
        PriorityQueue<Card> queue=new PriorityQueue<>();
        int n=scanner.nextInt(), m=scanner.nextInt();
        long mod=1000002013;
        long ans=0;
        while (m-->0) {
            int in=scanner.nextInt(), out=scanner.nextInt();
            long cnt=scanner.nextInt();
            queue.offer(new Card(in, cnt, 0, in));
            queue.offer(new Card(out, cnt, 1, in));
        }
        Stack<Card> stack=new Stack<>();
        while (!queue.isEmpty()) {
            Card card=queue.poll();
            if (card.state==0) {
                if (!stack.isEmpty() && stack.peek().index==card.index) {
                    stack.peek().cnt+=card.cnt;
                }
                else stack.push(card);
            }
            else {
                while (card.cnt>0) {
                    Card top=stack.pop();
                    long mn=Math.min(top.cnt, card.cnt), delta=card.index-top.index,
                            or=card.index-card.ori;
                    card.cnt-=mn;
                    top.cnt-=mn;
                    long val=(((delta-1)*delta/2-(or-1)*or/2)%mod);
                    ans+=val*mn%mod;
                    ans%=mod;
                    if (top.cnt>0) stack.push(top);
                }
            }
        }
        while (ans<0) ans+=mod;
        return String.valueOf(ans);
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
//        Scanner scanner=new Scanner(new File("input.txt"));
        int times=scanner.nextInt();
        long start=System.currentTimeMillis();
        for (int t=1;t<=times;t++) {
            try {
                System.out.println(String.format("Case #%d: %s", t, new Main().solve(scanner)));
            }
            catch (Exception e) {System.err.println("ERROR #"+t);e.printStackTrace();}
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.));
    }

}