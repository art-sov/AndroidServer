package dispatcher.model.dto;

import java.util.List;

/**
 * Created by Sovalov.AV on 10.04.2018.
 */
public class StationDto {
    private int id;
    private String name;
    private float coalValue;
    private float oilValue;
    private float gasValue;
    private float currentPower;
    private String unitValue;
    private List<UnitDto> unitDtoList;

    public StationDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCoalValue() {
        return coalValue;
    }

    public void setCoalValue(float coalValue) {
        this.coalValue = coalValue;
    }

    public float getOilValue() {
        return oilValue;
    }

    public void setOilValue(float oilValue) {
        this.oilValue = oilValue;
    }

    public float getGasValue() {
        return gasValue;
    }

    public void setGasValue(float gasValue) {
        this.gasValue = gasValue;
    }

    public float getCurrentPower() {
        return currentPower;
    }

    public void setCurrentPower(float currentPower) {
        this.currentPower = currentPower;
    }

    public String getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(String unitValue) {
        this.unitValue = unitValue;
    }

    public List<UnitDto> getUnitDtoList() {
        return unitDtoList;
    }

    public void setUnitDtoList(List<UnitDto> unitDtoList) {
        this.unitDtoList = unitDtoList;
    }
}
