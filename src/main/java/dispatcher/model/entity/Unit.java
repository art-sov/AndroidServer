package dispatcher.model.entity;

/**
 * Created by Sovalov.AV on 12.04.2018.
 */
public class Unit {
   private String shortnameStationRus;
   private int stationOch;
   private String unitName;
   private int unitId;
   private int och;
   private int stationCode;
   private float ti;
   private int typeObj;
   private float workUnit;

    public Unit() {
    }

    public String getShortnameStationRus() {
        return shortnameStationRus;
    }

    public void setShortnameStationRus(String shortnameStationRus) {
        this.shortnameStationRus = shortnameStationRus;
    }

    public int getStationOch() {
        return stationOch;
    }

    public void setStationOch(int stationOch) {
        this.stationOch = stationOch;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getOch() {
        return och;
    }

    public void setOch(int och) {
        this.och = och;
    }

    public int getStationCode() {
        return stationCode;
    }

    public void setStationCode(int stationCode) {
        this.stationCode = stationCode;
    }

    public float getTi() {
        return ti;
    }

    public void setTi(float ti) {
        this.ti = ti;
    }

    public int getTypeObj() {
        return typeObj;
    }

    public void setTypeObj(int typeObj) {
        this.typeObj = typeObj;
    }

    public float getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(float workUnit) {
        this.workUnit = workUnit;
    }

    @Override
    public String toString() {
        return "\nShortNameStation: " + shortnameStationRus +
        " OCH: "  + stationOch +
        " UNIT_NAME: " + unitName +
        " UNIT_ID: " + unitId +
        " OCH: " + och +
        " STAN_COD: " + stationCode +
        " TI: " + ti +
        " TYPE_OBJ: " + typeObj +
        " WORK_UNIT: " + workUnit;
    }
}
