import java.util.*;

/**
 * Codejam 2019 Round 1C
 * Problem B. Power Arrangers
 */
public class Solution {
    private int f = 0;
    public void solve(Scanner scanner, int f) {
        this.f = f;
        ArrayList<Integer> remain = new ArrayList<>();
        for (int i = 0; i < 119; ++i) remain.add(i);
        solve(scanner, new char[5], remain, new HashSet<>());
    }

    private void solve(Scanner scanner, char[] chars, ArrayList<Integer> remain, HashSet<Character> used) {
        int index = used.size();
        if (index == 4) {
            for (char c = 'A'; c <= 'E'; ++c) {
                if (!used.contains(c)) {
                    chars[index] = c;
                }
            }
            while (f>0) query(scanner, 1);
            System.out.println(chars);
            System.out.flush();
            String s = scanner.next();
            if (s.equals("N")) throw new RuntimeException();
            return;
        }
        int need = (new int[]{23, 5, 1, 0})[index];
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for (char c = 'A'; c <= 'E'; ++c) {
            if (!used.contains(c)) map.put(c, new ArrayList<>());
        }
        for (int v : remain) {
            char c = query(scanner, 5 * v + index + 1);
            map.get(c).add(v);
        }
        for (Map.Entry<Character, ArrayList<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() == need) {
                chars[index] = entry.getKey();
                used.add(entry.getKey());
                solve(scanner, chars, entry.getValue(), used);
                return;
            }
        }
    }

    private char query(Scanner scanner, int index) {
        System.out.println(index);
        System.out.flush();
        f--;
        return scanner.next().charAt(0);
    }

    public static void main(String[] args) throws Exception {
        // System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times = scanner.nextInt();
        int f = scanner.nextInt();
        long start=System.currentTimeMillis();
        for (int t=1;t<=times;t++) {
            try {
                // System.out.println(String.format("Case #%d: %s", t, new Main().solve(scanner, f)));
                new Solution().solve(scanner, f);
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