package edu.brandeis.cs.eureynoguchi.expenselog;

/**
 * Created by eureyuri on 2017/12/21.
 */

public class User {

    private int current;
    private int thisWeek;
    private int leftOver;
    private int threshold;

    public User(int current, int thisWeek, int leftOver, int threshold) {
        this.current = current;
        this.thisWeek = thisWeek;
        this.leftOver = leftOver;
        this.threshold = threshold;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public void setThisWeek(int thisWeek) {
        this.thisWeek = thisWeek;
    }

    public void setLeftOver(int leftOver) {
        this.leftOver = leftOver;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getCurrent() {
        return current;
    }

    public int getThisWeek() {
        return thisWeek;
    }

    public int getLeftOver() {
        return leftOver;
    }

    public int getThreshold() {
        return threshold;
    }

}
