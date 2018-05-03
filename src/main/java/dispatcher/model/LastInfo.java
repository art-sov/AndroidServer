package dispatcher.model;

import java.util.Date;

/**
 * Created by Sovalov.AV on 13.04.2018.
 */
public class LastInfo {

    private Date time;
    private int index;
    private int value;

    public LastInfo() {
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "\nTime: " + time + "  ****  " +
                " IND: " + index + "  ****  " +
                "PARAMS: " + value;
    }
}
