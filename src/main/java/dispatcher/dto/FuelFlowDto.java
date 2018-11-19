package dispatcher.dto;

import java.util.Date;

/**
 * Created by Sovalov.AV on 23.10.2018.
 */
public class FuelFlowDto {

    private Date date;
    private String stationName;
    private int row;
    private int[] values = new int[4];

    public FuelFlowDto() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }
}
