package dispatcher.model.dto;

/**
* <param>station</param>          STATUS.LIST_STAN      STAN_FULLNAME_RUS
 * <param>blockNumber</param>     from name
 * <param>name</param>            STATUS.LIST_UNIT      UNIT_NAME
 * <param>power</param>           OIK_BASE.LAST_SREZ    PARAMS
 * <param>statusShortName</param> STATUS.TYPE_ACT       ACT_SHORTNAME
 * <param>statusFullName</param> STATUS.TYPE_ACT        ACT_FULLNAME
 * <param>repairStartTime</param> STATUS.LIST_ACTION    BEGI
 * <param>repairEndTime</param>   STATUS.LIST_ACTION    ENDI
 * <param>comment</param>         STATUS.LIST_ACTION    REM
 * <param>operator</param>        STATUS.LIST_ACTIOIN   COR_OP
 * <param>editTime</param>        STATUS.LIST_ACTION    COR_TIME
 * <param>color</param>           STATUS.TYPE_ACT       ACT_COLOR
 * */

import java.util.Date;

/**
 * Created by Sovalov.AV on 11.04.2018.
 */
public class UnitDto {
    private int id;
    private int power;
    private int ti;
    private int blockNumber;
    private int stationCode;
    private String station;
    private String name;
    private String statusShortName;
    private String statusFullName;
    private Date repairStartTime;
    private Date repairEndTime;
    private String comment;
    private String operator;
    private Date editTime;
    private String color;
    private int och;
    private float workUnit;

    public UnitDto() {
    }

    public int getId() {
        return id;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getTi() {
        return ti;
    }

    public void setTi(int ti) {
        this.ti = ti;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStation() {
        return station;
    }

    public int getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(int blockNumber) {
        this.blockNumber = blockNumber;
    }

    public int getStationCode() {
        return stationCode;
    }

    public void setStationCode(int stationCode) {
        this.stationCode = stationCode;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatusShortName() {
        return statusShortName;
    }

    public void setStatusShortName(String statusShortName) {
        this.statusShortName = statusShortName;
    }

    public String getStatusFullName() {
        return statusFullName;
    }

    public void setStatusFullName(String statusFullName) {
        this.statusFullName = statusFullName;
    }

    public Date getRepairStartTime() {
        return repairStartTime;
    }

    public void setRepairStartTime(Date repairStartTime) {
        this.repairStartTime = repairStartTime;
    }

    public Date getRepairEndTime() {
        return repairEndTime;
    }

    public void setRepairEndTime(Date repairEndTime) {
        this.repairEndTime = repairEndTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getOch() {
        return och;
    }

    public void setOch(int och) {
        this.och = och;
    }

    public float getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(float workUnit) {
        this.workUnit = workUnit;
    }

    @Override
    public String toString() {
        return "\nid: " + id +
                " ti: " + ti +
                " power: " + power +
        " block number: " + blockNumber +
        " stationCode: " + stationCode +
        " name: " + name +
        " statusShortName: " + statusShortName +
        " statusFullName: " + statusFullName +
        " repairStartTime: " + repairStartTime +
        " repairEndTime: " + repairEndTime +
        " comment: " + comment +
        " operator: " + operator +
        " editTime: " + editTime +
        " color: " + color +
        " och: " + och +
        " work_unit: " + workUnit;
    }
}
