package dispatcher.model.entity;

import java.util.Date;

/**
 * Created by Sovalov.AV on 12.04.2018.
 */
public class ArchivTI {
    private Date time;
    private int index;
    private int params;

    public ArchivTI() {
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getInd() {
        return index;
    }

    public void setInd(int ind) {
        this.index = ind;
    }

    public int getParams() {
        return params;
    }

    public void setParams(int params) {
        this.params = params;
    }
}
