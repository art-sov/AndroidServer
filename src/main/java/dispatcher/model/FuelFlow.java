package dispatcher.model;

import java.util.Date;

public class FuelFlow {
    private Date date;
    private int stationCode;
    private String stationNameRus;
    private String stationNameUkr;
    private String nameFuel;
    private int in;
    private int out;
    private int rest;

    public FuelFlow() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStationCode() {
        return stationCode;
    }

    public void setStationCode(int stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationNameRus() {
        return stationNameRus;
    }

    public void setStationNameRus(String stationNameRus) {
        this.stationNameRus = stationNameRus;
    }

    public String getStationNameUkr() {
        return stationNameUkr;
    }

    public void setStationNameUkr(String stationNameUkr) {
        this.stationNameUkr = stationNameUkr;
    }

    public String getNameFuel() {
        return nameFuel;
    }

    public void setNameFuel(String nameFuel) {
        this.nameFuel = nameFuel;
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public int getRest() {
        return rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    @Override
    public String toString() {
        return "date: " + date +
                "\nstationCode: " + stationCode +
                "\nstationNameRus: " + stationNameRus +
                "\nstationNameUkr: " + stationNameUkr +
                "\nnameFuel: " + nameFuel +
                "\nin: " + in +
                "\nout: " + out +
                "\nrest: " + rest;
    }
}
