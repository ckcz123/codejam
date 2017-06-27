import java.io.PrintStream;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * KickStart 2017 Round C Problem C. Magical Thinking
 * Check README.md for explanation.
 */
public class Main {

    public String solve(Scanner scanner) {
        int n=scanner.nextInt(), q=scanner.nextInt();
        char[][] chars=new char[n+1][q];
        int[] score=new int[n];
        for (int i=0;i<=n;i++) chars[i]=scanner.next().toCharArray();
        for (int i=0;i<n;i++) score[i]=scanner.nextInt();
        return String.valueOf(n==1?solve1(q, chars[0], chars[1], score[0]):
                solve2(q, chars[0], chars[1], chars[2], score[0], score[1]));
    }

    private int solve1(int q, char[] c, char[] you, int s) {
        int ans=0;
        for (int i=0;i<q;i++) {
            if (c[i]==you[i] && s-->0)
                ans++;
            if (c[i]!=you[i] && i+s<q)
                ans++;
        }
        return ans;
    }

    private int solve2(int q, char[] c1, char[] c2, char[] you, int s1, int s2) {
        boolean[][][] able=new boolean[q+1][s1+1][s2+1];
        able[0][0][0]=true;
        for (int i=1;i<=q;i++) {
            for (int u=0;u<=s1;u++) {
                for (int v=0;v<=s2;v++) {
                    for (char c: "TF".toCharArray()) {
                        int nu=u, nv=v;
                        if (c1[i-1]==c) nu--;
                        if (c2[i-1]==c) nv--;
                        if (nu>=0 && nv>=0 && able[i-1][nu][nv])
                            able[i][u][v]=true;
                    }
                }
            }
        }
        int ans=0;
        for (int i=q;i>=1;i--) {
            char c=you[i-1];
            // can score
            int ns1=s1, ns2=s2;
            if (c1[i-1]==c) ns1--;
            if (c2[i-1]==c) ns2--;
            if (ns1>=0 && ns2>=0 && able[i-1][ns1][ns2]) {
                // can!
                ans++;
                s1=ns1;s2=ns2;
                continue;
            }
            // not able
            c=(char)('T'+'F'-you[i-1]);
            if (c1[i-1]==c) s1--;
            if (c2[i-1]==c) s2--;
        }
        return ans;
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