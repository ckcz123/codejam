import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private String solve(Scanner scanner) throws Throwable {
        int n=scanner.nextInt();
        int r=scanner.nextInt(), o=scanner.nextInt(), y=scanner.nextInt(),
                g=scanner.nextInt(), b=scanner.nextInt(), v=scanner.nextInt();

        // o-b
        if (o==b && o+b==n) return fill('O','B', n);
        if (g==r && g+r==n) return fill('G','R', n);
        if (v==y && v+y==n) return fill('V','Y', n);

        if (o>0) {
            b-=o; if (b<=0) return "IMPOSSIBLE";
        }
        if (g>0) {
            r-=g; if (r<=0) return "IMPOSSIBLE";
        }
        if (v>0) {
            y-=v; if (y<=0) return "IMPOSSIBLE";
        }

        String string=solve(r, y, b);
        if ("IMPOSSIBLE".equals(string)) return "IMPOSSIBLE";
        string=string.replaceFirst("B", fillCross('B', 'O', o));
        string=string.replaceFirst("R", fillCross('R', 'G', g));
        string=string.replaceFirst("Y", fillCross('Y', 'V', v));
        return string;
    }

    private String fill(char x, char y, int n) {
        char[] chars=new char[n];
        for (int i=0;i<n;i+=2) {
            chars[i]=x;
            chars[i+1]=y;
        }
        return new String(chars);
    }

    private String fillCross(char x, char y, int n) {
        char[] chars=new char[2*n+1];
        Arrays.fill(chars, x);
        for (int i=1;i<2*n;i+=2) chars[i]=y;
        return new String(chars);
    }

    private String solve(int r, int y, int b) {
        Color[] colors=new Color[] {new Color(r, 'R'), new Color(y, 'Y'),
                new Color(b, 'B')};
        int n=r+y+b;
        Arrays.sort(colors, (c1,c2)->c2.n-c1.n);
        if (2*colors[0].n>n) return "IMPOSSIBLE";
        int curr=0;
        char[] chars=new char[n];
        while (colors[0].n-->0) {
            chars[curr]=colors[0].c;
            curr+=2;
        }
        while (colors[1].n-->0) {
            if (curr>=n) curr=1;
            chars[curr]=colors[1].c;
            curr+=2;
        }
        while (colors[2].n-->0) {
            if (curr>=n) curr=0;
            while (chars[curr]!=0) curr++;
            chars[curr]=colors[2].c;
        }
        return new String(chars);
    }

    class Color {
        int n;
        char c;
        public Color(int _n, char _c) {n=_n;c=_c;}
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
            }
        }
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}