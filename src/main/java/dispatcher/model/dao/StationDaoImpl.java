package dispatcher.model.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Sovalov.AV on 13.04.2018.
 */
@Repository
public class StationDaoImpl implements StationDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getStationFullName(int id) {
        String sql = "select stan_fullname_rus from STATUS.LIST_STAN where N = ?";
        String name = jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
        return name;
    }
}
