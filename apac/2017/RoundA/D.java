import java.io.PrintStream;
import java.util.*;

public class Main {

    class Card {
        int k, l;
        long[] a, c;
        public Card(int _k, int _l) {
            k=_k;l=_l;
            a=new long[k+1];
            c=new long[k+1];
        }
    }

    private String solve(Scanner scanner) {
        long m=scanner.nextLong();
        int n=scanner.nextInt();
        Card[] cards=new Card[n];
        long[] maxAtk=new long[n];
        for (int i=0;i<n;i++) {
            cards[i]=new Card(scanner.nextInt(), scanner.nextInt());
            int k=cards[i].k;
            for (int j=1;j<=k;j++) {
                cards[i].a[j]=scanner.nextLong();
            }
            for (int j=1;j<k;j++) {
                cards[i].c[j]=scanner.nextLong();
            }
            maxAtk[i]=(i==0?0:maxAtk[i-1])+cards[i].a[cards[i].k];
        }
        solve(m, n-1, cards, Math.min(n, 8), 0, maxAtk);
        return String.valueOf(result);
    }

    private long result=0;

    private void solve(long m, int n, Card[] cards, int remain, long current, long[] maxAtk) {
        if (n<0 || remain==0) {
            if (current>result) result=current;
            return;
        }
        if (current+maxAtk[n]<=result) return;
        solve(m, n-1, cards, remain, current, maxAtk);
        Card card=cards[n];
        solve(m, n-1, cards, remain-1, current+card.a[card.l], maxAtk);
        long need=0;
        for (int i=card.l+1;i<=card.k;i++) {
            need+=card.c[i-1];
            if (m<need) break;
            solve(m-need, n-1, cards, remain-1, current+card.a[i], maxAtk);
        }
    }

    public static void main(String[] args) throws Exception {
        //System.setOut(new PrintStream("E:\\desktop\\output.txt"));
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