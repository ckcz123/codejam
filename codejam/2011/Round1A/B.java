import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2011 Round 1A
 * Problem B. The Killer Word
 */
public class Main {

    class State {
        int point;
        String curr;
        ArrayList<String> list;
        boolean[] has;
        public State(String _curr, int _point) {
            curr=_curr; point=_point;
            list=new ArrayList<>();
            has=new boolean[128];
        }
        public void add(String word) {
            list.add(word);
            for (char c: word.toCharArray()) has[c]=true;
        }
    }

    public String solve(Scanner scanner) {
        int d=scanner.nextInt(), m=scanner.nextInt();
        ArrayList<String> dict=new ArrayList<>();
        HashMap<String, Integer> indexMap=new HashMap<>();
        for (int i=0;i<d;i++) {
            String s=scanner.next();
            dict.add(s);
            indexMap.put(s, i);
        }
        HashMap<Integer, ArrayList<String>> lengthMap=new HashMap<>();
        for (String s: dict) {
            int l=s.length();
            ArrayList<String> list=lengthMap.getOrDefault(l, new ArrayList<>());
            list.add(s); lengthMap.put(l, list);
        }

        ArrayList<String> ans=new ArrayList<>();
        while (m-->0) {
            String vocab=scanner.next();
            LinkedList<State> list=new LinkedList<>();
            for (Map.Entry<Integer, ArrayList<String>> entry: lengthMap.entrySet()) {
                int l=entry.getKey();
                char[] chars=new char[l]; Arrays.fill(chars, ' ');
                State state=new State(new String(chars), 0);
                for (String s: entry.getValue()) {
                    state.add(s);
                }
                list.add(state);
            }

            for (char c: vocab.toCharArray()) {
                LinkedList<State> nextList=new LinkedList<>();
                for (State state: list) {
                    // guess?
                    if (!state.has[c]) {
                        nextList.add(state);
                        continue;
                    }

                    HashMap<String, State> map=new HashMap<>();
                    for (String s: state.list) {
                        char[] chars=state.curr.toCharArray();
                        for (int i=0;i<s.length();i++) {
                            if (s.charAt(i)==c) {
                                chars[i]=c;
                            }
                        }
                        String next=new String(chars);
                        State nextState=
                                map.getOrDefault(next, new State(next, (next.equals(state.curr)?1:0)+state.point));
                        nextState.add(s);
                        map.put(next, nextState);
                    }
                    for (Map.Entry<String, State> entry: map.entrySet()) {
                        nextList.add(entry.getValue());
                    }
                }
                list=nextList;
            }

            int points=-1;
            String s="";
            for (State state: list) {
                if (state.point>points || (state.point==points &&
                        indexMap.get(state.curr)<indexMap.get(s))) {
                    points=state.point;
                    s=state.curr;
                }
            }
            ans.add(s);
        }
        return String.join(" ", ans);
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