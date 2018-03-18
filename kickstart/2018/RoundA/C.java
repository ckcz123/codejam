import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * KickStart 2018 Round A
 * Problem C. Scrambled Words
 * Only small solved.
 */
public class Main {

    public String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    public String solveSmall(Scanner scanner) {
        int l=scanner.nextInt();
        String[] strings=new String[l];
        for (int i=0;i<l;i++) strings[i]=scanner.next();
        String s=input(scanner);
        int n=s.length();

        int[][] cnt=new int[n+1][26];
        for (int i=1;i<=n;i++) {
            cnt[i]=cnt[i-1].clone();
            cnt[i][s.charAt(i-1)-'a']++;
        }

        int ans=0;
        for (int i=0;i<l;i++) {
            if (check(n, cnt, s, strings[i]))
                ans++;
        }
        return String.valueOf(ans);
    }

    private boolean check(int n, int[][] cnt, String s, String curr) {
        int len=curr.length();int[] u=new int[26];
        for (char c: curr.toCharArray())
            u[c-'a']++;
        for (int j=len-1;j<n;j++) {
            int start=j-len+1, end=j;
            if (s.charAt(start)==curr.charAt(0) && s.charAt(end)==curr.charAt(len-1)) {
                boolean able=true;
                int[] v=cnt[end+1].clone();
                for (int w=0;w<26;w++) {
                    v[w] -= cnt[start][w];
                    if (v[w]!=u[w]) {
                        able=false;
                        break;

                    }
                }
                if (able) return true;
            }
        }
        return false;
    }

    private String input(Scanner scanner) {
        char s1=scanner.next().charAt(0), s2=scanner.next().charAt(0);
        int n=scanner.nextInt();
        long a=scanner.nextLong(), b=scanner.nextLong(), c=scanner.nextLong(), d=scanner.nextLong();
        char[] chars=new char[n];
        chars[0]=s1; chars[1]=s2;
        long[] x=new long[n];
        x[0]=chars[0]; x[1]=chars[1];
        for (int i=2;i<n;i++) {
            x[i]=(a*x[i-1]+b*x[i-2]+c)%d;
            chars[i]=(char)(x[i]%26+'a');
        }
        return new String(chars);
    }


    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
//        System.setIn(new FileInputStream("input.txt"));
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