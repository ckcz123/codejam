import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2012 Round 1A
 * Problem C. Cruise Control
 * Only small solved.
 */
public class Main {

    private String solve(Scanner scanner) {
        return solveSmall(scanner);
    }

    private String solveSmall(Scanner scanner) {
        int n=scanner.nextInt(), tn=1<<n;
        int state=0;
        double epos=1e-9;
        double[] speed=new double[n], position=new double[n];
        for (int i=0;i<n;i++) {
            String line=scanner.next(); if (line.charAt(0)=='R') state|=(1<<i);
            speed[i]=scanner.nextDouble(); position[i]=scanner.nextDouble();
        }

        // calculate all key time
        ArrayList<Double> validTime=new ArrayList<>();
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (speed[i]>speed[j]) {
                    validTime.add(Math.max(0, (position[j]-position[i]-5)/(speed[i]-speed[j])));
                    validTime.add(Math.max(0, (position[j]-position[i]+5)/(speed[i]-speed[j])));
                }
            }
        }
        validTime.add(0.); validTime.add(0.); validTime.add(1e10);
        Collections.sort(validTime);
        boolean[][] able=new boolean[validTime.size()][tn];
        able[0][state]=true;
        double ans=.0;
        for (int i=0;i<validTime.size()-1;i++) {
            for (int j=0;j<tn;j++) {
                if (!able[i][j]) continue;
                ans=validTime.get(i);
                // check if valid to go on..
                double t=(validTime.get(i)+validTime.get(i+1))/2;
                boolean ok=true;
                for (int a=0;a<n;a++) {
                    for (int b=a+1;b<n;b++) {
                        // same line, collide?
                        if (((j&(1<<a))==0) == ((j&(1<<b))==0)) {
                            if (Math.abs((position[a]+speed[a]*t)
                                    -(position[b]+speed[b]*t))<5-epos) {
                                ok=false; break;
                            }
                        }
                    }
                }
                if (!ok) continue;

                t=validTime.get(i+1);
                ans=t;
                // update state
                for (int k=0;k<tn;k++) {
                    ok=true;
                    for (int a=0;a<n;a++) {
                        for (int b=a+1;b<n;b++) {
                            if ((((j&(1<<a))==0) != ((k&(1<<a))==0))
                                    && (((j&(1<<b))==0) != ((k&(1<<b))==0))){
                                if (Math.abs((position[a]+speed[a]*t)
                                        -(position[b]+speed[b]*t))<5-epos) {
                                    ok=false; break;
                                }
                            }
                        }
                    }
                    if (ok) able[i+1][k]=true;
                }
            }
        }
        return ans>1e9?"Possible":String.format("%.9f", ans);
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