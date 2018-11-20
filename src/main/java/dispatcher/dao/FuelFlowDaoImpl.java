package dispatcher.dao;

import dispatcher.model.FuelFlow;
import dispatcher.util.Constants;
import dispatcher.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Sovalov.AV on 14.11.2018.
 */
@Repository
public class FuelFlowDaoImpl implements FuelFlowDao{

    private final JdbcTemplate jdbcTemplate;
    private DateUtil dateUtil;
    private Map<Integer, String> mapFuel = new HashMap<>();

    @Autowired
    public FuelFlowDaoImpl(JdbcTemplate jdbcTemplate, DateUtil dateUtil) {
        this.jdbcTemplate = jdbcTemplate;
        this.dateUtil = dateUtil;
        mapFuel.put(3,"Мазут");
        mapFuel.put(1,"АШ");
        mapFuel.put(0,"ГиД");
        mapFuel.put(2,"Газ");
        mapFuel.put(4,"Всего");
        mapFuel.put(5,"АШ стан");
        mapFuel.put(6,"ГиД стан");
    }

    @Override
    public List<FuelFlow> getListCoalFlow(int day) {

        Date selectedDate = dateUtil.getSelectedDate();
        java.sql.Date dateSql = null;

        if (day == 0){
            dateSql = getDate(selectedDate, -1);
        }
        if (day == -1){
            dateSql = getDate(selectedDate, -2);
        }
        if (day == -2) {
            dateSql = getDate(selectedDate, -3);
        }

        List<FuelFlow> fuelFlowList = new ArrayList<>();

        String sql = "SELECT F.DATES, L.STAN_COD, L.STAN_FULLNAME_RUS, L.STAN_FULLNAME_UKR, K.NAME, F.INPUT, F.OUTPUT, F.REST " +
                "FROM COMMON.FUEL_MSSQL F, STATUS.LIST_STAN L, COMMON.FUEL_MSSQL_KEY K " +
                "WHERE F.STANCOD = L.STAN_COD " +
                "AND F.TYPE_FUEL = K.ID " +
                "AND DATES = ? " +
                "ORDER BY L.STAN_COD";

        fuelFlowList = jdbcTemplate.query(sql, new Object[]{dateSql}, new RowMapper<FuelFlow>() {
            @Override
            public FuelFlow mapRow(ResultSet resultSet, int i) throws SQLException {
                FuelFlow fuelFlow = new FuelFlow();
                fuelFlow.setDate(resultSet.getDate(Constants.TABLE_FUEL_FLOW_DATE));
                fuelFlow.setStationCode(resultSet.getInt(Constants.TABLE_FUEL_FLOW_STAN_COD));
                fuelFlow.setStationNameRus(resultSet.getString(Constants.TABLE_FUEL_FLOW_STAN_NAME_RUS));
                fuelFlow.setStationNameUkr(resultSet.getString(Constants.TABLE_FUEL_FLOW_STAN_NAME_UKR));
                fuelFlow.setNameFuel(resultSet.getString(Constants.TABLE_FUEL_FLOW_NAME_FUEL));
                fuelFlow.setIn(resultSet.getInt(Constants.TABLE_FUEL_FLOW_IN));
                fuelFlow.setOut(resultSet.getInt(Constants.TABLE_FUEL_FLOW_OUT));
                fuelFlow.setRest(resultSet.getInt(Constants.TABLE_FUEL_FLOW_REST));
                return fuelFlow;
            }
        });
        return fuelFlowList;
    }

    @Override
    public List<FuelFlow> getListFuelFlow(int type_fuel) {

        Date selectedDate = dateUtil.getSelectedDate();

        //List<FuelFlow> list = new ArrayList<>();

        String sql = "SELECT DATES, L.STAN_FULLNAME_RUS, L.STAN_FULLNAME_UKR, STANCOD, INPUT, OUTPUT, REST" +
                " FROM COMMON.FUEL_MSSQL,  STATUS.LIST_STAN L" +
                " WHERE STANCOD = L.STAN_COD" +
                " AND TYPE_FUEL = ?" +
                " AND DATES IN (?, ?, ?)" +
                " ORDER BY DATES";

        return  jdbcTemplate.query(sql, new Object[]{type_fuel, getDate(selectedDate, -1),
                getDate(selectedDate,-2), getDate(selectedDate, -3)},
                new RowMapper<FuelFlow>() {
            @Override
            public FuelFlow mapRow(ResultSet resultSet, int i) throws SQLException {
                FuelFlow fuelFlow = new FuelFlow();
                fuelFlow.setDate(resultSet.getDate(Constants.TABLE_FUEL_FLOW_DATE));
                fuelFlow.setStationNameRus(resultSet.getString(Constants.TABLE_FUEL_FLOW_STAN_NAME_RUS));
                fuelFlow.setStationNameUkr(resultSet.getString(Constants.TABLE_FUEL_FLOW_STAN_NAME_UKR));
                fuelFlow.setNameFuel(mapFuel.get(type_fuel));
                fuelFlow.setStationCode(resultSet.getInt(Constants.TABLE_FUEL_FLOW_STANCOD));
                fuelFlow.setIn(resultSet.getInt(Constants.TABLE_FUEL_FLOW_IN));
                fuelFlow.setOut(resultSet.getInt(Constants.TABLE_FUEL_FLOW_OUT));
                fuelFlow.setRest(resultSet.getInt(Constants.TABLE_FUEL_FLOW_REST));
                return fuelFlow;
            }
        });
    }

    private java.sql.Date getDate(Date selectedDate, int numberOfDaysAgo){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedDate);
        calendar.add(Calendar.DATE, numberOfDaysAgo);

        selectedDate = calendar.getTime();

        return new java.sql.Date(selectedDate.getTime());
    }
}
