import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2009 Round 1B
 * Problem A. Decision Tree
 */
public class Main {

    class MyScanner {
        char[] chars;
        int len, index;

        public MyScanner(String s) {
            chars=s.toCharArray();
            len=chars.length;
            index=0;
        }

        public boolean hasNext() {
            ignoreSpaces();
            return index<len;
        }

        public void ignoreSpaces() {
            while (index<len && Character.isWhitespace(chars[index])) index++;
        }

        public char nextChar() {
            ignoreSpaces(); if (!hasNext()) throw new InputMismatchException();
            return chars[index++];
        }

        public void back() {
            index--;
        }

        public String nextString(String pattern) {
            ignoreSpaces(); if (!hasNext()) throw new InputMismatchException();
            StringBuilder builder=new StringBuilder();
            while (index<len && !Character.isWhitespace(chars[index])) {
                builder.append(chars[index++]);
                if (!builder.toString().matches(pattern)) {
                    index--;
                    return builder.substring(0, builder.length()-1);
                }
            }
            return builder.toString();
        }

        public String next() {
            return nextString("[^\\s]+");
        }

        public int nextInt() {
            return Integer.parseInt(nextString("[0-9]+"));
        }

        public double nextDouble() {
            return Double.parseDouble(nextString("[0-9]+(\\.[0-9]*)?"));
        }

    }

    class Tree {
        String name;
        double p;
        Tree left, right;
        public Tree(double _p, String _name) {
            p=_p;name=_name;
        }
    }

    public String solve(Scanner scanner) {
        int l=Integer.parseInt(scanner.nextLine());
        StringBuilder builder=new StringBuilder();
        for (int i=0;i<l;i++) builder.append(scanner.nextLine()).append(" ");
        MyScanner ss=new MyScanner(builder.toString());
        Tree root=readTree(ss);
        ArrayList<String> ans=new ArrayList<>();
        ans.add("");
        int n=Integer.parseInt(scanner.nextLine());
        while (n-->0) {
            Scanner scanner1=new Scanner(scanner.nextLine());
            scanner1.next();
            HashSet<String> set=new HashSet<>();
            int m=scanner1.nextInt();
            while (m-->0) set.add(scanner1.next());
            Tree tree=root;
            double p=tree.p;
            while (tree.name!=null) {
                if (set.contains(tree.name))
                    tree=tree.left;
                else
                    tree=tree.right;
                p*=tree.p;
            }
            ans.add(String.format("%.9f", p));
        }
        return String.join("\n", ans);

    }

    private Tree readTree(MyScanner scanner) {

        // read '('
        scanner.nextChar();

        // read p
        double p=scanner.nextDouble();

        if (scanner.nextChar()==')') {
            return new Tree(p, null);
        }
        scanner.back();
        Tree tree=new Tree(p, scanner.next());
        tree.left=readTree(scanner);
        tree.right=readTree(scanner);
        scanner.nextChar();
        return tree;
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        Scanner scanner=new Scanner(System.in);
        int times=Integer.parseInt(scanner.nextLine());
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