package dispatcher.model.dto;

/**
 * Created by Sovalov.AV on 11.04.2018.
 */
public class UnitDto {
    private int id;
    private String station;
    private String name;
    private float power;
    private String repair;
    private String repairStartTime;
    private String repairEndTime;
    private String comments;
    private String operator;
    private String editTime;

    public UnitDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStation() {
        return station;
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

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public String getRepair() {
        return repair;
    }

    public void setRepair(String repair) {
        this.repair = repair;
    }

    public String getRepairStartTime() {
        return repairStartTime;
    }

    public void setRepairStartTime(String repairStartTime) {
        this.repairStartTime = repairStartTime;
    }

    public String getRepairEndTime() {
        return repairEndTime;
    }

    public void setRepairEndTime(String repairEndTime) {
        this.repairEndTime = repairEndTime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }
}
