package model.entity;

import java.util.Date;

/**
 * Created by Sovalov.AV on 12.04.2018.
 */
public class Fuel {
    private Date date;
    private int codeStantoin;
    private float coalIn;
    private float coalOut;
    private float coalRest;
    private float oilIn;
    private float oilOut;
    private float oilRest;
    private float gasOut;

    public Fuel() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCodeStantoin() {
        return codeStantoin;
    }

    public void setCodeStantoin(int codeStantoin) {
        this.codeStantoin = codeStantoin;
    }

    public float getCoalIn() {
        return coalIn;
    }

    public void setCoalIn(float coalIn) {
        this.coalIn = coalIn;
    }

    public float getCoalOut() {
        return coalOut;
    }

    public void setCoalOut(float coalOut) {
        this.coalOut = coalOut;
    }

    public float getCoalRest() {
        return coalRest;
    }

    public void setCoalRest(float coalRest) {
        this.coalRest = coalRest;
    }

    public float getOilIn() {
        return oilIn;
    }

    public void setOilIn(float oilIn) {
        this.oilIn = oilIn;
    }

    public float getOilOut() {
        return oilOut;
    }

    public void setOilOut(float oilOut) {
        this.oilOut = oilOut;
    }

    public float getOilRest() {
        return oilRest;
    }

    public void setOilRest(float oilRest) {
        this.oilRest = oilRest;
    }

    public float getGasOut() {
        return gasOut;
    }

    public void setGasOut(float gasOut) {
        this.gasOut = gasOut;
    }
}
