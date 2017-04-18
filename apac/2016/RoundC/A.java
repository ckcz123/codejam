import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    class Athlete {
        String name;
        ArrayList<Integer> scores;
        int score;
        public Athlete(String name) {
            this.name=name;
            scores=new ArrayList<>();
        }
        public void addScore(int score) {
            scores.add(score);
        }
        Athlete calScore(int m) {
            scores.sort((o1, o2)->o2-o1);
            score=0;
            for (int i=0;i<scores.size() && i<m; i++) {
                score+=scores.get(i);
            }
            return this;
        }
    }

    public String solve(Scanner scanner) {
        int p=scanner.nextInt();
        HashMap<String, Athlete> map=new HashMap<>();
        int[] s=new int[p];
        for (int i=0;i<p;i++) s[i]=scanner.nextInt();
        int n=scanner.nextInt();
        for (int i=0;i<n;i++) {
            int w=scanner.nextInt();
            for (int j=0;j<p;j++) {
                String name=scanner.next();
                Athlete athlete=map.getOrDefault(name, new Athlete(name));
                athlete.addScore(w*s[j]);
                map.put(name, athlete);
            }
        }
        int m=scanner.nextInt();
        ArrayList<Athlete> arrayList=new ArrayList<>();
        for (Athlete athlete: map.values())
            arrayList.add(athlete.calScore(m));
        arrayList.sort((o1,o2)->o1.score==o2.score?o1.name.compareTo(o2.name):o2.score-o1.score);

        int last=0;
        ArrayList<String> ans=new ArrayList<>();
        ans.add("");
        for (int i=0;i<arrayList.size();i++) {
            if (i==0 || arrayList.get(i).score!=arrayList.get(i-1).score)
                last=i;
            ans.add(String.format("%d: %s", last+1, arrayList.get(i).name));
        }
        return String.join("\n", ans);
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