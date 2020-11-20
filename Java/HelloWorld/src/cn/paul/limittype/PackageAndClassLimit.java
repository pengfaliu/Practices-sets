package cn.paul.limittype;

/**
 * Created by lfp on 2020/10/25.
 * @author lfp
 *
 */

public class PackageAndClassLimit {
    public  static void main(String[] args) {
        /**
         *
         */
        System.out.print(publicclass());
        System.out.print()
    }
    public static boolean publicclass(){

        return false;
    }
    private static boolean privateclass() {
        return true;

    }
     static boolean defalutclass(){
        return true;
    }
    protected static boolean protectedclass() {
        return false;
    }
}
