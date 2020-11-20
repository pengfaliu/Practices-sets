package cn.paul.interfacetest;

/**
 * Created by lfp on 2020/11/14.
 */
public interface FootBall extends Sports {

    public void homeTeamScored(int points);
    public void visitingTeamScored(int points);
    public void endOfQuarter(int quarter);

}

