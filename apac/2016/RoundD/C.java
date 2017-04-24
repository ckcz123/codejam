import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * APAC 2016 Round D Problem C: IP Address Summarization
 * Check README.md for explanation.
 */
public class Main {

    class Trie {
        boolean end;
        String s;
        Trie left, right;
        public Trie(String _s) {
            s=_s;left=right=null;end=false;
        }
    }

    public String solve(Scanner scanner) {
        int n=scanner.nextInt();
        Trie trie=new Trie("");
        for (int i=0;i<n;i++) {
            String ip=scanner.next();
            String s=ip2str(ip);
            insert(trie, s, 0);
        }
        clean(trie);
        ArrayList<String> arrayList=new ArrayList<>();
        getAnswer(trie, arrayList);
        return "\n"+String.join("\n", arrayList);
    }

    private String ip2str(String ip) {
        String[] ips=ip.split("[./]");
        StringBuilder builder=new StringBuilder();
        for (int j=0;j<4;j++) {
            StringBuilder builder1=new StringBuilder(Integer.toBinaryString(Integer.parseInt(ips[j])));
            while (builder1.length()<8) builder1.insert(0, '0');
            builder.append(builder1);
        }
        int prefix=Integer.parseInt(ips[4]);
        return builder.toString().substring(0, prefix);
    }

    private void insert(Trie trie, String s, int index) {
        if (index==s.length()) {
            trie.end=true;
            trie.left=null;
            trie.right=null;
            return;
        }
        if (trie.end) return;
        if (s.charAt(index)=='0') {
            if (trie.left==null) trie.left=new Trie(s.substring(0, index+1));
            insert(trie.left, s, index+1);
        }
        else {
            if (trie.right==null) trie.right=new Trie(s.substring(0, index+1));
            insert(trie.right, s, index+1);
        }
    }

    private void clean(Trie trie) {
        if (trie==null) return;
        if (trie.end) {
            trie.left=trie.right=null;
            return;
        }
        clean(trie.left);
        clean(trie.right);
        if (trie.left!=null && trie.right!=null && trie.left.end && trie.right.end) {
            trie.end=true;
            trie.left=trie.right=null;
        }
    }

    private void getAnswer(Trie trie, ArrayList<String> arrayList) {
        if (trie==null) return;
        if (trie.end) {
            arrayList.add(str2ip(trie.s));
            return;
        }
        getAnswer(trie.left, arrayList);
        getAnswer(trie.right, arrayList);
    }

    private String str2ip(String str) {
        int prefix=str.length();
        StringBuilder builder=new StringBuilder(str);
        while (builder.length()<32) builder.append('0');
        return Integer.parseInt(builder.substring(0, 8), 2)+"."
                +Integer.parseInt(builder.substring(8, 16), 2)+"."
                +Integer.parseInt(builder.substring(16, 24), 2)+"."
                +Integer.parseInt(builder.substring(24, 32), 2)+"/"+prefix;
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