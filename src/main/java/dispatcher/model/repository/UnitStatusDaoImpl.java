package dispatcher.model.repository;

import dispatcher.model.dao.UnitStatusDao;
import dispatcher.model.entity.UnitStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sovalov.AV on 17.04.2018.
 */
@Repository
public class UnitStatusDaoImpl implements UnitStatusDao{

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UnitStatus> getAllUnitStatus(){

        List<UnitStatus> unitStatusList = new ArrayList<UnitStatus>();
        String sql = "SELECT L.STAN_COD, C.*, T.ACT_COLOR, T.ACT_SHORTNAME, T.ACT_FULLNAME\n" +
                "FROM STATUS.LIST_ACTION C, STATUS.TYPE_ACT T, STATUS.LIST_UNIT L \n" +
                "WHERE C.ACT = T.ACT_SHORTNAME \n" +
                "AND L.UNIT_ID <> 10021 \n" +
                "AND L.UNIT_ID <> 10039 \n" +
                "AND L.UNIT_ID <> 10040 \n" +
                "AND L.UNIT_ID <> 10041 \n" +
                "AND C.UNIT_ID = L.UNIT_ID \n" +
                "AND ((C.BEGI <= TO_DATE('19.04.2018','dd.mm.yyyy') \n" +
                "\tAND C.ENDI >= TO_DATE('19.04.2018','dd.mm.yyyy'))\n" +
                "OR (C.ENDI <= TO_DATE('19.04.2018','dd.mm.yyyy') \n" +
                "\tAND C.F_END = 0)) \n" +
                "AND NOT L.STAN_COD \n" +
                "IN (370084, 513130, 513133, 513134, 513135, 513532, 513531, 513533, 563737)\n" +
                "ORDER BY  L.STAN_COD, decode(C.unit_id, 10184, 10030, 10185, 10031, 10186, 10032, C.unit_id)";

        unitStatusList = jdbcTemplate.query(sql, new RowMapper<UnitStatus>() {
            public UnitStatus mapRow(ResultSet resultSet, int i) throws SQLException {
                UnitStatus unitStatus = new UnitStatus();
                unitStatus.setStationCode(resultSet.getInt("stan_cod"));
                unitStatus.setActionId(resultSet.getInt("action_id"));
                unitStatus.setUnitId(resultSet.getInt("unit_id"));
                unitStatus.setActionShortName(resultSet.getString("act"));
                unitStatus.setActionFullName((resultSet.getString("act_fullname").trim()));
                unitStatus.setActionBegin(resultSet.getDate("begi"));
                unitStatus.setGetActionEnd(resultSet.getDate("endi"));
                unitStatus.setfEnd(resultSet.getInt("f_end"));
                unitStatus.setComment(resultSet.getString("rem"));
                unitStatus.setActionEdit(resultSet.getDate("cor_time"));
                unitStatus.setOperator(resultSet.getString("cod_op"));
                unitStatus.setColor(resultSet.getString("act_color"));
                return unitStatus;
            }
        });
        return unitStatusList;
    }
}
