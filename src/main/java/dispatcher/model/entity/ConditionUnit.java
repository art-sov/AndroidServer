package dispatcher.model.entity;

import java.util.Date;

/**
 * Created by Sovalov.AV on 12.04.2018.
 */
public class ConditionUnit {
    private long id;
    private int unitId;
    private String action;
    private Date beginAction;
    private Date endAction;
    private int fEnd;
    private String comment;
    private Date corTime;
    private String operator;

    public ConditionUnit() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getBeginAction() {
        return beginAction;
    }

    public void setBeginAction(Date beginAction) {
        this.beginAction = beginAction;
    }

    public Date getEndAction() {
        return endAction;
    }

    public void setEndAction(Date endAction) {
        this.endAction = endAction;
    }

    public int getfEnd() {
        return fEnd;
    }

    public void setfEnd(int fEnd) {
        this.fEnd = fEnd;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCorTime() {
        return corTime;
    }

    public void setCorTime(Date corTime) {
        this.corTime = corTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
