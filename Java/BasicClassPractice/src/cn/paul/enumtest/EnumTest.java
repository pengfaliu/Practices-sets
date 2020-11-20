package cn.paul.enumtest;

/**
 * Created by lfp on 2020/11/14.
 */
public class   EnumTest {
    public static void main(String[] args) {
/*
        for (Color myVar : Color.values()) {
            System.out.println(myVar);
        }
            System.out.println("----Color end----");

        for (Week myweek : Week.values()) {
            System.out.println(myweek);
            }

            System.out.println("----Week end----");*/

        for (Season s : Season.values()) {
                System.out.println(s.ordinal()+" index num is "+ s);
//                System.out.println(s.valueOf("Spring"));
            }
            System.out.println("----Season end----");

        Season S = Season.Summer;
        S.colorInfo();
        SwitchInEnum sie = new SwitchInEnum();
        System.out.println(sie.SIE(S));

    }
}

enum  Color {
    RED,GREEN,BLUE,YELLOW,GREY,PURPLE,PINK,ORANGE;

    private Color(){
        System.out.println("Constructor called for " + this.toString());
    }

}

enum Week {
    Monday,Tuseday,Wednesday,Thursday,Friday,Saturday,Sunday;
}

enum Season {
    Spring,Summer,Autumn,Winter;

    int seasonAcount = 4;

    private Season(){
        System.out.println("Constructor called for " + this.toString());
    }

    public void colorInfo() {
        System.out.println("Universal Color");
        System.out.println(this.seasonAcount);
    }
}

class SwitchInEnum {
    public int SIE(Season element) {
        switch (element) {
            case Spring:
                System.out.println("春天");
                break;
            case Summer:
                System.out.println("夏天");
                break;
            case Autumn :
                System.out.println("秋天");
                break;
            case Winter:
                System.out.print("冬天");
                break;

        }
        return 0;
    }
}