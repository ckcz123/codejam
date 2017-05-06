import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Codejam 2014 Round 1A
 * Problem C. Proper Shuffle
 */
public class Main {

    private String solve(Scanner scanner) {
        Random random=new Random();
        int[] w=new int[1000];
        int[][] score=new int[1000][1000];
        for (int i=0;i<3000000;i++) {
            for (int j=0;j<1000;j++) {
                w[j]=j;
            }
            for (int j=0;j<1000;j++) {
                int k=random.nextInt(1000);
                int tmp=w[j];w[j]=w[k];w[k]=tmp;
            }
            for (int j=0;j<1000;j++) {
                score[j][w[j]]++;
            }
        }
        int cases=scanner.nextInt();
        int[][] rates=new int[cases][2];
        for (int t=0;t<cases;t++) {
            rates[t][0]=t;
            int n=scanner.nextInt();
            for (int i=0;i<n;i++) {
                rates[t][1]+=score[i][scanner.nextInt()];
            }
        }
        Arrays.sort(rates, Comparator.comparingInt(o->o[1]));
        boolean[] v=new boolean[1000];
        for (int i=0;i<cases/2;i++) {
            v[rates[i][0]]=true;
        }
        for (int i=0;i<cases;i++) {
            System.out.println(String.format("Case #%d: %s", i+1, v[i]?"GOOD":"BAD"));
        }

        return "";
    }

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream("output.txt"));
        long start=System.currentTimeMillis();
        Scanner scanner=new Scanner(System.in);
        /*
        int times=scanner.nextInt();
        for (int t=1;t<=times;t++) {
            System.out.println(String.format("Case #%d: %s", t, new Main().solve(scanner)));
        }
        */
        new Main().solve(scanner);
        long end=System.currentTimeMillis();
        System.err.println(String.format("Time used: %.3fs", (end-start)/1000.0));

    }

}