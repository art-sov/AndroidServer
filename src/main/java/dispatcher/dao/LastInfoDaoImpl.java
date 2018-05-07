package dispatcher.dao;

import dispatcher.model.LastInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sovalov.AV on 13.04.2018.
 */

@Repository
public class LastInfoDaoImpl implements LastInfoDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LastInfoDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<LastInfo> getLastInfo() {
        List<LastInfo> listInfo = new ArrayList<LastInfo>();

        String sql = "SELECT /*+INDEX(AR TIME_ARHIVTI)*/ AR.TIME, AR.IND, AR.PARAMS \n" +
                "FROM OIK_BASE.ARHIVTI AR \n" +
                "WHERE (AR.TIME, AR.IND) IN (SELECT /*+INDEX_COMBINE(BR TIME_ARHIVTI IND_ARHIVTI)*/ \n" +
                "\tMAX(BR.TIME), BR.IND \n" +
                "FROM OIK_BASE.ARHIVTI BR WHERE BR.TIME \n" +
                "BETWEEN TRUNC((TO_DATE('07.05.2018','dd.mm.yyyy'))-2/1440,'HH') - 1/24 \n" +
                "AND (TO_DATE('07.05.2018','dd.mm.yyyy')) \n" +
                "GROUP BY BR.IND) \n" +
                "AND AR.TIME BETWEEN TRUNC((TO_DATE('07.05.2018','dd.mm.yyyy')) - 2/1440,'HH') - 1/24 \n" +
                "AND (TO_DATE('07.05.2018','dd.mm.yyyy')) \n" +
                "AND AR.IND IN (SELECT FU.TI FROM STATUS.FULL_UNIT_TI FU ) ORDER BY 2 ASC";
        //SELECT l.ind, l.params, b.name FROM OIK_BASE.LAST_SREZ l, OIK_BASE.BOOK_TI b WHERE l.IND = b.IND

        listInfo = jdbcTemplate.query(sql, new RowMapper<LastInfo>() {
            public LastInfo mapRow(ResultSet resultSet, int i) throws SQLException {
                LastInfo lastInfo = new LastInfo();
                lastInfo.setTime(resultSet.getDate("time"));
                lastInfo.setIndex(resultSet.getInt("ind"));
                lastInfo.setValue(resultSet.getInt("params"));
                return lastInfo;
            }
        });
        return listInfo;
    }
}
