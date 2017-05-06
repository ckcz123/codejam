import java.io.PrintStream;
import java.util.*;

/**
 * Codejam 2013 Round 1C
 * Problem C. The Great Wall
 * Only small solved.
 */
public class Main {

    public String solve(Scanner sc) {
        return solveSmall(sc);
    }

    class Attack {
        int d,n,w,e,s,delta_d,delta_p,delta_s;
        public Attack(int _d, int _n, int _w, int _e, int _s,
                      int _delta_d, int _delta_p, int _delta_s) {
            d=_d;n=_n;w=_w;e=_e;s=_s;delta_d=_delta_d;delta_p=_delta_p;
            delta_s=_delta_s;
        }
    }

    private String solveSmall(Scanner sc) {
        int n=sc.nextInt();
        Attack[] attacks=new Attack[n];
        for (int i=0;i<n;i++) {
            attacks[i]=new Attack(sc.nextInt(), sc.nextInt(),
                    sc.nextInt(), sc.nextInt(), sc.nextInt(),
                    sc.nextInt(), sc.nextInt(), sc.nextInt());
        }
        int ans=0;
        int[] height=new int[1000];
        for (int d=0;d<=676060;d++) {
            int[] nh=height.clone();
            for (int i=0;i<n;i++) {
                Attack attack=attacks[i];
                boolean atk=false;
                if (attack.n>0 && attack.d==d) {
                    int s=attack.s, w=attack.w, e=attack.e;
                    for (int u=500+w;u<500+e;u++) {
                        if (nh[u]<s) {
                            atk=true;
                            height[u]=Math.max(height[u], s);
                        }
                    }
                    attack.n--;
                    attack.d+=attack.delta_d;
                    attack.w+=attack.delta_p;
                    attack.e+=attack.delta_p;
                    attack.s+=attack.delta_s;
                }
                if (atk) ans++;
            }
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) throws  Exception{
        System.setOut(new PrintStream("output.txt"));
        Scanner sc = new Scanner(System.in);
        int times = sc.nextInt();
        for(int i=1; i<=times; i++){
            try {
                System.out.println(String.format("Case #%d: %s", i, new Main().solve(sc)));
            }catch (Exception e){
                System.err.println(String.format("error at case#%d", i));
            }
        }
    }

}
