package dispatcher.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by Sovalov.AV on 10.04.2018.
 */
public class StationDto {
    private int id;
    private Date date;
    private String name;
    private float coalValue;
    private float oilValue;
    private float gasValue;
    private float power;
    private int och;
    private String unitValue;
    private List<BlockDto> blockDtoList;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public int getOch() {
        return och;
    }

    public void setOch(int och) {
        this.och = och;
    }

    public String getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(String unitValue) {
        this.unitValue = unitValue;
    }

    public List<BlockDto> getBlockDtoList() {
        return blockDtoList;
    }

    public void setBlockDtoList(List<BlockDto> unitDtoList) {
        this.blockDtoList = unitDtoList;
    }

    @Override
    public String toString() {
        return "\nid: " + id +
        " date: " + date +
        " name: " + name +
        " coal: " + coalValue +
        " oil: " + oilValue +
        " gas: " + gasValue +
        " power: " + power +
        " sklad: " + unitValue +
        " blocks: " + blockDtoList;
    }
}
