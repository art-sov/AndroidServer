package dispatcher.dao;

import dispatcher.model.Remark;
import dispatcher.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Sovalov.AV on 20.09.2018.
 */
@Repository
public class RemarksDaoImpl implements RemarksDao {

    private JdbcTemplate jdbcTemplate;
    private DateUtil dateUtil;

    @Autowired
    public RemarksDaoImpl(JdbcTemplate jdbcTemplate, DateUtil dateUtil) {
        this.jdbcTemplate = jdbcTemplate;
        this.dateUtil = dateUtil;
    }

    public String getRemarks() {
        String date = dateUtil.getDateYesterday();

        String sql = "select m.DATES, m.MEMOS from status.list_syst_memo m where m.DATES = TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))";

        Remark remark = jdbcTemplate.queryForObject(sql, new RowMapper<Remark>(){

            @Override
            public Remark mapRow(ResultSet resultSet, int i) throws SQLException {
                Remark remark = new Remark();
                remark.setDates(resultSet.getDate("DATES"));
                remark.setMemos(resultSet.getClob("MEMOS"));
                return remark;
            }
        });

        Clob clob = remark.getMemos();
        return fromClobToString(clob);
    }

    private String fromClobToString(Clob clob) {
        long len = 0;
        String substring = "";
        try {
            len = clob.length();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            substring = clob.getSubString(1, (int)len);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return substring;
    }
}
