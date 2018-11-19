package dispatcher.util;

/**
 * Created by Sovalov.AV on 30.08.2018.
 */
public class Constants {

    //columns of table 1 "Balance of Energy System of Ukraine"
    //28 rows
    public final static String TABLE_BALANCE_COLUMN_1 = "IND";
    public final static String TABLE_BALANCE_COLUMN_2 = "NAME";
    public final static String TABLE_BALANCE_COLUMN_3 = "MIN_H";
    public final static String TABLE_BALANCE_COLUMN_4 = "MAX_H";
    public final static String TABLE_BALANCE_COLUMN_5 = "EMERGY";

    //columns of table 2 "Control consumption"
    //9 rows
    public final static String TABLE_CONSUMPTION_COLUMN_1 = "ID";
    public final static String TABLE_CONSUMPTION_COLUMN_2 = "E_FAKT";
    public final static String TABLE_CONSUMPTION_COLUMN_3 = "O_FAKT";
    public final static String TABLE_CONSUMPTION_COLUMN_4 = "PLN";
    public final static String TABLE_CONSUMPTION_COLUMN_5 = "DIFF";

    //columns of table 3 "Condition of HydroStation"
    //8 rows
    public final static String TABLE_HYDROSTATION_COLUMN1 = "ID";
    public final static String TABLE_HYDROSTATION_COLUMN2 = "NAME";
    public final static String TABLE_HYDROSTATION_COLUMN3 = "NPR";
    public final static String TABLE_HYDROSTATION_COLUMN4 = "HI_BJEF";
    public final static String TABLE_HYDROSTATION_COLUMN5 = "INCOME";
    public final static String TABLE_HYDROSTATION_COLUMN6 = "OUTCOME";

    //columns of fuel flow table
    public final static String TABLE_FUEL_FLOW_DATE = "DATES";
    public final static String TABLE_FUEL_FLOW_STAN_COD = "STAN_COD";
    public final static String TABLE_FUEL_FLOW_STANCOD = "STANCOD";
    public final static String TABLE_FUEL_FLOW_STAN_NAME_RUS = "STAN_FULLNAME_RUS";
    public final static String TABLE_FUEL_FLOW_STAN_NAME_UKR = "STAN_FULLNAME_UKR";
    public final static String TABLE_FUEL_FLOW_NAME_FUEL = "NAME";
    public final static String TABLE_FUEL_FLOW_IN = "INPUT";
    public final static String TABLE_FUEL_FLOW_OUT = "OUTPUT";
    public final static String TABLE_FUEL_FLOW_REST = "REST";
}
