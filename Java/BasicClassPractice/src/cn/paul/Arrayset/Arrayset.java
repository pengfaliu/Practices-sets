package cn.paul.Arrayset;

/**
 * Created by lfp on 2020/11/2.
 */
public class Arrayset {
    public static void main(String[] args) {
        int[] arrayx;
        arrayx = new int[100];
        arrayx[0] = 100;
        arrayx[1] = 101;
        arrayx[2] = 102;

        for (int b:arrayx){
            System.out.print(b + ",");
        }
        System.out.println("\n"+arrayx.length);

        int[]  arrayb = {1,2,3,4,5,6};
        for (int x:arrayb) {
            System.out.print(x + ",");
        }
        System.out.println();

        String[][]  arrayc;

        arrayc = new String[10][10];
        arrayc[0][0] = "ABC song,there is a story";
        arrayc[9][0] = "I'm working now! I'm sad!";
        for(int i=0; i<10; i++){
            for (int k=0; k<10; k++){
                System.out.printf("[%d][%d] is %s\n",i,k,arrayc[i][k]);
            }
        }
    }
}
