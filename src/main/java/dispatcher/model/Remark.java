package dispatcher.model;

import java.sql.Clob;
import java.util.Date;

/**
 * Created by Sovalov.AV on 20.09.2018.
 */
public class Remark {

    private Date dates;

    private Clob memos;

    public Remark() {
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public Clob getMemos() {
        return memos;
    }

    public void setMemos(Clob memos) {
        this.memos = memos;
    }
}
