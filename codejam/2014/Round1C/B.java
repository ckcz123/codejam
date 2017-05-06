import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2014 Round 1C
 * Problem B. Reordering Train Cars
 */
public class Main {

    private String solve(Scanner scanner) {
        int[] cnt=new int[26];
        long mod=1000000007;
        ArrayList<String> arrayList=new ArrayList<>();
        int n=scanner.nextInt();
        for (int i=0;i<n;i++) {
            String s=scanner.next();
            StringBuilder builder=new StringBuilder();
            char c='\0';
            for (char ch: s.toCharArray()) {
                if (c!=ch) {
                    if (c!='\0') builder.append(c);
                    c=ch;
                }
            }
            builder.append(c);
            if (builder.length()==1) cnt[c-'a']++;
            else arrayList.add(builder.toString());
        }
        long ans=1;
        for (int i=0;i<26;i++) {
            if (cnt[i]!=0) arrayList.add(String.valueOf((char)('a'+i)));
            ans*=fact(cnt[i], mod);
            ans%=mod;
        }
        int size=0;
        n=arrayList.size();
        boolean[] used=new boolean[n];
        boolean[][] linked=new boolean[n][n];
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                linked[i][j]=linked[j][i]=hasCommon(arrayList.get(i), arrayList.get(j));
            }
        }
        for (int i=0;i<n;i++) {
            if (used[i]) continue;
            used[i]=true;
            ArrayList<String> strings=new ArrayList<>();
            strings.add(arrayList.get(i));
            Queue<Integer> queue=new LinkedList<>();
            queue.offer(i);
            while (!queue.isEmpty()) {
                int u=queue.poll();
                for (int j=0;j<n;j++) {
                    if (!used[j] && linked[u][j]) {
                        used[j]=true; strings.add(arrayList.get(j));
                        queue.offer(j);
                    }
                }
            }
            size++;
            ans*=cal(strings, mod);
            ans%=mod;
        }
        return String.valueOf(ans*fact(size, mod)%mod);
    }

    private boolean hasCommon(String s, String t) {
        for (char c='a';c<='z';c++) {
            if (s.indexOf(c)!=-1 && t.indexOf(c)!=-1) return true;
        }
        return false;
    }

    private long cal(ArrayList<String> strings, long mod) {
        int n=strings.size();
        int[] has=new int[26];
        for (String s: strings) {
            boolean[] v=new boolean[26];
            for (char c: s.toCharArray()) {
                v[c-'a']=true;
            }
            for (int i=0;i<26;i++) if (v[i]) has[i]++;
        }
        return dfs(strings, new boolean[26], '\0', n, new boolean[n], 0, has, mod);
    }

    private long dfs(ArrayList<String> strings, boolean[] used, char c, int n, boolean[] visited,
                     int cnt, int[] has, long mod) {
        if (cnt==n) return 1;
        long ans=0;
        for (int i=0;i<strings.size();i++) {
            if (visited[i]) continue;
            String string=strings.get(i);
            char ch=c;
            if (c!=0 && string.charAt(0)!=c && has[c-'a']>0) continue;
            boolean[] uu=Arrays.copyOf(used, used.length);
            boolean[] vs=Arrays.copyOf(visited, visited.length);
            int[] h=Arrays.copyOf(has, has.length);
            boolean able=true;
            boolean[] v=new boolean[26];
            for (char curr: string.toCharArray()) {
                v[curr-'a']=true;
                if (uu[curr-'a'])  {
                    able=false;
                    break;
                }
                if (curr!=ch) {
                    if(ch!=0) uu[ch-'a']=true;
                    ch=curr;
                }
            }

            if (able) {
                for (int j=0;j<26;j++) {
                    if (v[j]) h[j]--;
                }
                vs[i]=true;
                ans+=dfs(strings, uu, ch, n, vs, cnt+1, h, mod);
                ans%=mod;
            }
        }
        return ans;
    }

    private long fact(int n, long mod) {
        if (n<=1) return 1;
        return n*fact(n-1, mod)%mod;
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
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}