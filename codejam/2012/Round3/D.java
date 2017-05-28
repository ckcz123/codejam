import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2012 Round 3
 * Problem D. Lost Password
 * Only small solved.
 */
public class Main {

    public String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int k=scanner.nextInt();
        HashMap<Character, Character> map=new HashMap<>();
        map.put('o','0');map.put('i','1');map.put('e','3');
        map.put('a','4');map.put('s','5');map.put('t','7');
        map.put('b','8');map.put('g','9');
        HashSet<String> set=new HashSet<>();
        String s=scanner.next();
        for (int i=0;i<s.length()-1;i++) {
            String s1=s.substring(i,i+2);
            set.add(s1);
            set.add(replace(s1,0,map));
            set.add(replace(s1,1,map));
            set.add(replace(replace(s1,0,map),1,map));
        }
        boolean[][] linked=new boolean[36][36];
        for (String s1: set) {
            char c1=s1.charAt(0), c2=s1.charAt(1);
            int i1=c1>='0'&&c1<='9'?c1-'0':c1-'a'+10;
            int i2=c2>='0'&&c2<='9'?c2-'0':c2-'a'+10;
            linked[i1][i2]=true;
        }
        int[] input=new int[36], output=new int[36];
        for (int i=0;i<36;i++)  {
            for (int j=0;j<36;j++) {
                if (linked[i][j]) {
                    output[i]++;
                    input[j]++;
                }
            }
        }
        int cnt=0;
        while (choose(input, output)) cnt++;
        return String.valueOf(cnt+set.size()+1);
    }

    private boolean choose(int[] inputs, int[] outputs) {

        int cnt=0;
        for (int i=0;i<36;i++) {
            if (inputs[i]>outputs[i]) cnt+=inputs[i]-outputs[i];
        }
        if (cnt<=1) return false;

        int i1=-1, d1=0, i2=-1, d2=0;
        for (int i=0;i<36;i++) {
            if (inputs[i]-outputs[i]>d1) {
                i1=i; d1=inputs[i]-outputs[i];
            }
            if (outputs[i]-inputs[i]>d2) {
                i2=i; d2=outputs[i]-inputs[i];
            }
        }
        outputs[i1]++;
        inputs[i2]++;
        return true;
    }

    private String replace(String s, int index, HashMap<Character, Character> map) {
        char[] chars=s.toCharArray();
        chars[index]=map.getOrDefault(chars[index],chars[index]);
        return new String(chars);
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