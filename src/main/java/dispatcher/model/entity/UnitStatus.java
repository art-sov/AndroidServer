package dispatcher.model.entity;

import java.util.Date;

/**
 * Created by Sovalov.AV on 16.04.2018.
 */
public class UnitStatus {
    private int stationCode;
    private int actionId;
    private int unitId;
    private String actionShortName;
    private String actionFullName;
    private Date actionBegin;
    private Date getActionEnd;
    private int fEnd;
    private String comment;
    private Date actionEdit;
    private String operator;
    private String color;

    public UnitStatus() {
    }

    public int getStationCode() {
        return stationCode;
    }

    public void setStationCode(int stationCode) {
        this.stationCode = stationCode;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getActionShortName() {
        return actionShortName;
    }

    public void setActionShortName(String actionShortName) {
        this.actionShortName = actionShortName;
    }

    public String getActionFullName() {
        return actionFullName;
    }

    public void setActionFullName(String actionFullName) {
        this.actionFullName = actionFullName;
    }

    public Date getActionBegin() {
        return actionBegin;
    }

    public void setActionBegin(Date actionBegin) {
        this.actionBegin = actionBegin;
    }

    public Date getGetActionEnd() {
        return getActionEnd;
    }

    public void setGetActionEnd(Date getActionEnd) {
        this.getActionEnd = getActionEnd;
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

    public Date getActionEdit() {
        return actionEdit;
    }

    public void setActionEdit(Date actionEdit) {
        this.actionEdit = actionEdit;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return    "\nCode station: " + stationCode +
        " id action: " + actionId +
        " id unit: " + unitId +
        " action short name: " + actionShortName +
        " begin: " + actionBegin +
        " end: " + getActionEnd +
        " fact end: " + fEnd +
        " comment: " + comment +
        " edit: " + actionEdit +
        " operator: " + operator +
        " color: " + color;
    }
}
