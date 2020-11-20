package cn.paul.interfacetest;

/**
 * Created by lfp on 2020/11/14.
 */
public interface Hockey extends Sports {

    public void homeGoalScored();
    public void visitingGoalScored();
    public void endOfPeriod(int period);
    public void overtimePeriod(int ot);

}
