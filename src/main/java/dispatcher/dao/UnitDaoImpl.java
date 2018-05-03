package dispatcher.dao;

import dispatcher.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sovalov.AV on 16.04.2018.
 */

@Repository
public class UnitDaoImpl implements UnitDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UnitDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Unit> getAllUnitsInfo() {
        List<Unit> unitList = new ArrayList<Unit>();

        String sql = "SELECT B.STAN_SHORTNAME_RUS, B.STAN_OCH, S.UNIT_NAME, S.UNIT_ID, S.OCH, S.STAN_COD, S.TI, S.TYPE_OBJ, \n" +
                "decode(S.TYPE_OBJ, 0, 1, 4, 0.5) WORK_UNIT \n" +
                "FROM STATUS.INI_STBLANK A, STATUS.LIST_STAN B, STATUS.FULL_UNIT_INFO_NEW S \n" +
                "WHERE A.STAN_COD = B.STAN_COD \n" +
                "AND B.STAN_COD = S.STAN_COD\n" +
                "AND S.UNIT_ID<>10039 \n" +
                "AND S.UNIT_ID<>10040 \n" +
                "AND S.UNIT_ID<>10041 \n" +
                "AND S.STAN_COD<>370084 \n" +
                "ORDER BY stan_cod, \n" +
                "decode(unit_id, 10184, 10030, 10185, 10031, 10186, 10032, unit_id)";
            unitList = jdbcTemplate.query(sql, new RowMapper<Unit>() {
            public Unit mapRow(ResultSet resultSet, int i) throws SQLException {
                Unit unit = new Unit();
                unit.setShortnameStationRus(resultSet.getString("stan_shortname_rus"));
                unit.setStationOch(resultSet.getInt("stan_och"));
                unit.setUnitName(resultSet.getString("unit_name"));
                unit.setUnitId(resultSet.getInt("unit_id"));
                unit.setOch(resultSet.getInt("och"));
                unit.setStationCode(resultSet.getInt("stan_cod"));
                unit.setTi(resultSet.getFloat("ti"));
                unit.setTypeObj(resultSet.getInt("type_obj"));
                unit.setWorkUnit(resultSet.getFloat("work_unit"));
                return unit;
            }
        });
            return unitList;
    }
}
