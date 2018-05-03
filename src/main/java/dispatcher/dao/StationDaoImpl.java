package dispatcher.dao;

import dispatcher.dto.StationDto;
import dispatcher.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Sovalov.AV on 13.04.2018.
 */

@Repository
public class StationDaoImpl implements StationDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getStationFullName(int id) {
        String sql = "select stan_fullname_rus from STATUS.LIST_STAN where N = ?";
        String name = jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
        return name;
    }

    public List<StationDto> getAllStationDto() {
       String sql = "SELECT U.DATES, U.STANCOD, trunc(U.COAL_REST/1000, 1) COAL, trunc(U.OIL_REST/1000, 1) OIL, \n" +
               "trunc(U.GAS_OUT/24, 1) GAS \n" +
               "FROM COMMON.FUEL U WHERE U.DATES = TRUNC(TO_DATE('18.04.2018','dd.mm.yyyy')) ORDER BY STANCOD";
       List<StationDto> stationList = jdbcTemplate.query(sql, new RowMapper<StationDto>() {
           public StationDto mapRow(ResultSet resultSet, int i) throws SQLException {
               StationDto station = new StationDto();
               station.setId(resultSet.getInt("stancod"));
               station.setCoalValue(resultSet.getFloat("coal"));
               station.setOilValue(resultSet.getFloat("oil"));
               station.setGasValue(resultSet.getFloat("gas"));
               station.setDate(resultSet.getDate("dates"));
               return station;
           }
       });
       return stationList;
    }

    public List<Station> getAllStation() {
        String sql = "SELECT * FROM STATUS.LIST_STAN ORDER BY STAN_COD";

        List<Station> stationList = jdbcTemplate.query(sql, new RowMapper<Station>() {
            public Station mapRow(ResultSet resultSet, int i) throws SQLException {
                Station station = new Station();
                station.setId(resultSet.getInt("stan_cod"));
                station.setCodeEnergySystem(resultSet.getInt("ensys_cod"));
                station.setType(resultSet.getString("stan_type"));
                station.setOch(resultSet.getInt("stan_och"));
                station.setShortNameRus(resultSet.getString("stan_shortname_rus"));
                station.setShortNameUkr(resultSet.getString("stan_shortname_ukr"));
                station.setFullNameRus(resultSet.getString("stan_fullname_rus"));
                station.setFullNameUkr(resultSet.getString("stan_fullname_ukr"));
                return station;
            }
        });
        return stationList;
    }
}
