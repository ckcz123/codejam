import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2011 Round 1C
 * Problem C. Pseudominion
 * Only small solved.
 */
public class Main {

    class Card {
        int c, s, t;
        public Card(int _c, int _s, int _t) {c=_c;s=_s;t=_t;}
    }

    public String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int n=scanner.nextInt();
        ArrayList<Card> hand=new ArrayList<>();
        for (int i=0;i<n;i++)
            hand.add(new Card(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
        int m=scanner.nextInt();
        LinkedList<Card> board=new LinkedList<>();
        while (m-->0) {
            board.add(new Card(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
        }
        return String.valueOf(dfs(hand, board, 1));
    }

    private int dfs(ArrayList<Card> hand, LinkedList<Card> board, int turns) {
        if (hand.size()==0 || turns==0) return 0;

        // find one with turn>0
        for (int i=0;i<hand.size();i++) {
            if (hand.get(i).t>0) {
                Card card=hand.remove(i);
                for (int j=0;j<card.c;j++) {
                    if (!board.isEmpty())
                        hand.add(board.poll());
                }
                return card.s+dfs(hand, board, turns+card.t-1);
            }
        }

        int ans=0, lastscore=0;
        for (int v=2;v>=0;v--) {
            int index=-1, score=-1;
            ArrayList<Card> hd=new ArrayList<>(hand);
            LinkedList<Card> bd=new LinkedList<>(board);
            for (int i=0;i<hand.size();i++) {
                if (hand.get(i).c==v && hand.get(i).s>score) {
                    index=i; score=hand.get(i).s;
                }
            }
            if (index==-1 || score<lastscore) continue;
            Card card=hd.remove(index);
            for (int j=0;j<card.c;j++) {
                if (!bd.isEmpty())
                    hd.add(bd.poll());
            }
            ans=Math.max(ans, card.s+dfs(hd,bd, turns+card.t-1));
            lastscore=score;
        }
        return ans;
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