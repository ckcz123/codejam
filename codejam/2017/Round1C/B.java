import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    class Interval implements Comparable<Interval> {
        int start, end;
        public Interval(int s, int e) {start=s;end=e;}
        public String toString() {return String.format("[%d,%d]", start, end);}
        public int compareTo(Interval o) {return Integer.compare(start, o.start);}
    }

    public String solve(Scanner scanner) {
        int m=scanner.nextInt(), n=scanner.nextInt();
        Interval[] a=new Interval[m], b=new Interval[n];
        for (int i=0;i<m;i++) a[i]=new Interval(scanner.nextInt(), scanner.nextInt());
        for (int j=0;j<n;j++) b[j]=new Interval(scanner.nextInt(), scanner.nextInt());
        Arrays.sort(a); Arrays.sort(b);
        if (m==0 && n==0) return "2";
        if (m==0) return String.valueOf(2*solve(n, b, m, a));
        return String.valueOf(2*solve(m, a, n, b));
    }

    private int solve(int m, Interval[] a, int n, Interval[] b) {
        TreeMap<Integer, Integer> validMap=new TreeMap<>();
        int offset=a[0].start;
        for (int i=0;i<m;i++) {
            if (!(i==m-1 && offset==0 && a[i].end==1440))
                validMap.put(a[i].end-offset, i==m-1?1440:a[i+1].start-offset);
        }
        ArrayList<Interval> must=new ArrayList<>();
        for (int i=0;i<n;i++) {
            int start=b[i].start-offset; if (start<=0) start+=1440;
            int end=b[i].end-offset; if (end<=0) end+=1440;
            must.add(new Interval(start, end));
        }
        Collections.sort(must);
        int remain=720;
        for (Interval interval: must) remain-=interval.end-interval.start;
        // link...
        while (true) {
            int ans=-1, delta=Integer.MAX_VALUE;
            for (int i=0;i<must.size()-1;i++) {
                Interval i1=must.get(i), i2=must.get(i+1);
                if (validMap.floorKey(i1.start).intValue()==validMap.floorKey(i2.start).intValue()
                        && i2.start-i1.end<delta) {
                    ans=i;delta=i2.start-i1.end;
                }
            }
            if (delta==Integer.MAX_VALUE) break;
            if (delta>remain) return must.size();
            ArrayList<Interval> list=new ArrayList<>();
            for (int i=0;i<must.size();i++) {
                if (i!=ans && i!=ans+1) list.add(must.get(i));
            }
            list.add(new Interval(must.get(ans).start, must.get(ans+1).end));
            remain-=delta;
            must=list;
            Collections.sort(must);
        }
        // add...
        while (true) {
            ArrayList<Interval> list=new ArrayList<>();
            boolean used=false;
            for (Interval interval: must) {
                if (!used) {
                    Map.Entry<Integer, Integer> entry=validMap.floorEntry(interval.start);
                    if (entry.getValue()-entry.getKey()!=interval.end-interval.start) {
                        used=true;
                        list.add(new Interval(entry.getKey(), entry.getValue()));
                        remain-=(entry.getValue()-entry.getKey())-(interval.end-interval.start);
                    }
                    else list.add(interval);
                }
                else list.add(interval);
            }
            if (remain<=0) return list.size();
            if (!used) break;
            must=list;
        }
        // add more...
        while (true) {
            int start=-1, end=-1, delta=0;
            for (Map.Entry<Integer, Integer> entry: validMap.entrySet()) {
                boolean has=false;
                for (Interval interval: must) {
                    if (interval.start==entry.getKey()) {
                        has=true;break;
                    }
                }
                if (has) continue;
                if (delta<entry.getValue()-entry.getKey()) {
                    delta=entry.getValue()-entry.getKey();
                    start=entry.getKey(); end=entry.getValue();
                }
            }
            if (start==-1) break;
            must.add(new Interval(start, end));
            Collections.sort(must);
            remain-=delta;
            if (remain<=0) return must.size();
        }
        System.err.println("ERROR!");
        return 0;
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
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.));
    }
}