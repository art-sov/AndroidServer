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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sovalov.AV on 14.11.2018.
 */
@Repository
public class FuelFlowDaoImpl implements FuelFlowDao{

    private final JdbcTemplate jdbcTemplate;
    private DateUtil dateUtil;

    @Autowired
    public FuelFlowDaoImpl(JdbcTemplate jdbcTemplate, DateUtil dateUtil) {
        this.jdbcTemplate = jdbcTemplate;
        this.dateUtil = dateUtil;
    }

    @Override
    public List<FuelFlow> getListFuelFlow(int day) {

        String date = null;

        if (day == 0){
            date = dateUtil.getDateYesterday();
        }
        if (day == -1){
            date = dateUtil.getDateAfterYesterday();
        }
        if (day == -2) {
            date = dateUtil.getDateTwoDaysAgo();
        }

        List<FuelFlow> fuelFlowList = new ArrayList<>();

        String sql = "SELECT F.DATES, L.STAN_COD, L.STAN_FULLNAME_RUS, L.STAN_FULLNAME_UKR, K.NAME, F.INPUT, F.OUTPUT, F.REST " +
                "FROM COMMON.FUEL_MSSQL F, STATUS.LIST_STAN L, COMMON.FUEL_MSSQL_KEY K " +
                "WHERE F.STANCOD = L.STAN_COD " +
                "AND F.TYPE_FUEL = K.ID " +
                "AND DATES = TRUNC(TO_DATE('" + date + "', 'dd.mm.yyyy HH24:MI:SS')) " +
                "ORDER BY L.STAN_COD";
        fuelFlowList = jdbcTemplate.query(sql, new RowMapper<FuelFlow>() {
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
        //System.out.println(fuelFlowList);
        return fuelFlowList;
    }

    @Override
    public List<FuelFlow> getListOilFlow() {

        String dateYesterday = dateUtil.getDateYesterday();
        String dateAfterYesterday = dateUtil.getDateAfterYesterday();
        String dateTwoDaysAgo = dateUtil.getDateTwoDaysAgo();

         List<FuelFlow> list = new ArrayList<>();

        String sql = "SELECT DATES, L.STAN_FULLNAME_RUS, L.STAN_FULLNAME_UKR, STANCOD, INPUT, OUTPUT, REST" +
                "  FROM COMMON.FUEL_MSSQL,  STATUS.LIST_STAN L WHERE STANCOD = L.STAN_COD" +
                " AND TYPE_FUEL = 3" +
                " AND DATES IN (TRUNC(TO_DATE('" + dateYesterday + "', 'dd.mm.yyyy HH24:MI:SS'))," +
                "              TRUNC(TO_DATE('" + dateAfterYesterday + "', 'dd.mm.yyyy HH24:MI:SS'))," +
                "              TRUNC(TO_DATE('" + dateTwoDaysAgo + "', 'dd.mm.yyyy HH24:MI:SS')))" +
                " ORDER BY DATES";

        list = jdbcTemplate.query(sql, new RowMapper<FuelFlow>() {
            @Override
            public FuelFlow mapRow(ResultSet resultSet, int i) throws SQLException {
                FuelFlow fuelFlow = new FuelFlow();
                fuelFlow.setDate(resultSet.getDate(Constants.TABLE_FUEL_FLOW_DATE));
                fuelFlow.setStationNameRus(resultSet.getString(Constants.TABLE_FUEL_FLOW_STAN_NAME_RUS));
                fuelFlow.setStationNameUkr(resultSet.getString(Constants.TABLE_FUEL_FLOW_STAN_NAME_UKR));
                fuelFlow.setNameFuel("Мазут");
                fuelFlow.setStationCode(resultSet.getInt(Constants.TABLE_FUEL_FLOW_STANCOD));
                fuelFlow.setIn(resultSet.getInt(Constants.TABLE_FUEL_FLOW_IN));
                fuelFlow.setOut(resultSet.getInt(Constants.TABLE_FUEL_FLOW_OUT));
                fuelFlow.setRest(resultSet.getInt(Constants.TABLE_FUEL_FLOW_REST));
                return fuelFlow;
            }
        });
        return list;
    }
}
