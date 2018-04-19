package dispatcher.model.dto;

import dispatcher.model.entity.Unit;

/**
 * Created by Sovalov.AV on 17.04.2018.
 */
public class BlockDto {
    private int stationCode;
    private int number;
    private int power;
    private UnitDto unit1;
    private UnitDto unit2;

    public BlockDto() {
    }

    public int getStationCode() {
        return stationCode;
    }

    public void setStationCode(int stationCode) {
        this.stationCode = stationCode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public UnitDto getUnit1() {
        return unit1;
    }

    public void setUnit1(UnitDto unit1) {
        this.unit1 = unit1;
    }

    public UnitDto getUnit2() {
        return unit2;
    }

    public void setUnit2(UnitDto unit2) {
        this.unit2 = unit2;
    }

    @Override
    public String toString() {
        return "\nnumber: " + number +
                " station id: " + stationCode +
                " power: " + power +
                " unit1: " + unit1 +
                "\nunit2: " + unit2;
    }
}
