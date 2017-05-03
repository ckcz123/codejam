import java.io.PrintStream;
import java.util.*;

public class Main {

    class Interval implements Comparable<Interval> {
        int start, end;
        long cnt;
        public Interval(int s, int e, long c) {start=s;end=e;cnt=c;}
        public int compareTo(Interval interval) {
            if (start==interval.start) return Integer.compare(end, interval.end);
            return Integer.compare(start, interval.start);
        }
    }

    public String solve(Scanner sc) {
        long n=sc.nextInt(); int m=sc.nextInt();
        ArrayList<Interval> list=new ArrayList<>();
        while (m-->0) {
            list.add(new Interval(sc.nextInt(), sc.nextInt(), sc.nextLong()));
        }
        Collections.sort(list);
        long ans=0;
        while (true) {
            boolean has=false;
            int size=list.size();
            for (int i=0;i<size;i++) {
                Interval i1=list.get(i);
                for (int j=size-1;j>=i;j--) {
                    Interval i2=list.get(j);
                    if (i1.cnt>0 && i2.cnt>0 && i2.start>i1.start && i2.start<=i1.end && i2.end>i1.end) {
                        has=true;
                        long mn=Math.min(i1.cnt, i2.cnt);
                        ans+=mn*((i2.start-i1.start)*(i2.end-i1.end));
                        i1.cnt-=mn;
                        i2.cnt-=mn;
                        if (i2.cnt==0) list.remove(j);
                        if (i1.cnt==0) list.remove(i);
                        list.add(new Interval(i1.start, i2.end, mn));
                        list.add(new Interval(i2.start, i1.end, mn));
                        Collections.sort(list);
                        break;
                    }
                }
                if (has) break;
            }
            if (!has) break;
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) throws  Exception{
        System.setOut(new PrintStream("output.txt"));
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();
        for(int i=1; i<=times; i++){
            try {
                System.out.println(String.format("Case #%d: %s", i, new Main().solve(sc)));
            }catch (Exception e){
                System.err.println(String.format("error at case#%d", i));
            }
        }
    }

}
