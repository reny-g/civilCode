package domain;

/**
 * @author 吴仁杨
 */
public class SearchInfo {
    private double time;
    private int totalNum;

    public SearchInfo(double time, int totalNum) {
        this.time = time;
        this.totalNum = totalNum;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }
}
