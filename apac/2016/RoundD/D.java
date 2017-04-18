import java.io.PrintStream;
import java.util.*;

public class Main {

    private String solve(Scanner scanner) {
        int g=translate(scanner.next()), w=translate(scanner.next()),
                h=translate(scanner.next()), b=translate(scanner.next()),
                x=translate(scanner.next());
        long d=scanner.nextLong();
        //return solveSmall(g,w,h,b,x,d);
        ArrayList<Integer> days=new ArrayList<>();
        ArrayList<Integer> times=new ArrayList<>();
        HashSet<Integer> set=new HashSet<>();
        long total=d*86400;
        long curr=0, loop=-1;
        while (curr+x<total) {
            long next=findNext(curr, g, w, h, b, x);
            if (next==-1) return "-1";
            curr=next;
            long day=curr/86400, time=curr%86400;
            if (set.contains((int)time)) {
                loop=times.indexOf((int)time);
                break;
            }
            days.add((int)day);
            set.add((int)time);
            times.add((int)time);
        }
        if (loop==-1) return String.valueOf(times.size());
        int startDay=days.get((int)loop), loopSize=(int)curr/86400-startDay;
        long loopCount=(d-1-startDay)/loopSize;
        curr=(startDay+(loopCount-1)*loopSize+(days.get(days.size()-1)-startDay))*86400+times.get(times.size()-1);
        long cnt=loop+(days.size()-loop)*loopCount;
        while (curr+x<total) {
            long next=findNext(curr, g, w, h, b, x);
            cnt++;
            curr=next;
        }
        return String.valueOf(cnt);
    }

    private int translate(String time) {
        String[] strings=time.split(":");
        return Integer.parseInt(strings[0])*3600+Integer.parseInt(strings[1])*60
                +Integer.parseInt(strings[2]);
    }

    private long findNext(long curr, int g, int w, int h, int b, int x) {
        long day=curr/86400, start=day*86400;
        long next=-1;
        for (long[] intevals: new long[][] {{start+g, start+w-1}, {start+h, start+b-1},
                {start+86400+g, start+86400+w-1}, {start+86400+h, start+86400+b-1}}) {
            if (intevals[0]<=curr+x && intevals[1]>curr) {
                next=Math.max(next, Math.min(curr+x, intevals[1]));
            }
        }
        return next;
    }

    /*private String solveSmall(int g, int w, int h, int b, int x, long d) {
        long total=d*86400;
        long curr=0, cnt=0;
        while (curr+x<total) {
            long next=findNext(curr, g, w, h, b, x);
            if (next==-1) return "-1";
            cnt++;
            curr=next;
        }
        return String.valueOf(cnt);
    }*/

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
