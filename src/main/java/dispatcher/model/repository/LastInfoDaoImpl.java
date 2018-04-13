package dispatcher.model.repository;

import dispatcher.model.dao.LastInfoDao;
import dispatcher.model.entity.LastInfo;
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

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<LastInfo> getLastInfo() {
        List<LastInfo> listInfo = new ArrayList<LastInfo>();

        String sql = "SELECT time, ind, params FROM oik_base.last_srez";
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
