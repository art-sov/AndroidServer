package dispatcher.dao;

import dispatcher.model.BalanceIPSUkraine;
import dispatcher.model.ConsumptionControl;
import dispatcher.model.HydroStationCondition;
import dispatcher.util.Constants;
import dispatcher.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Sovalov.AV on 30.08.2018.
 */
@Repository
public class ConsolidateReportDaoImpl implements ConsolidateReportDao {


    private JdbcTemplate jdbcTemplate;
    private DateUtil dateUtil;


    @Autowired
    public ConsolidateReportDaoImpl(JdbcTemplate jdbcTemplate, DateUtil dateUtil) {
        this.jdbcTemplate = jdbcTemplate;
        this.dateUtil = dateUtil;
    }

    public List<BalanceIPSUkraine> getBalance() {
        String date = dateUtil.getDate();

        String sql = "SELECT v.*, e.VALUE EMERGY FROM (SELECT 1 IND,'Година' NAME, a.H_MIN MIN_H, b.H_MAX MAX_H FROM\n" +
                "(SELECT /*+INDEX_COMBINE(VED)*/  TO_NUMBER(TO_CHAR(TIMES,'hh24')) H_MIN FROM OIK_BASE.VED WHERE IND=1498 " +
                "AND TIMES BETWEEN trunc(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))  +1/24 AND trunc(TO_DATE('" +
                date + "','dd.mm.yyyy HH24:MI:SS'))  +1 AND VALUE =  (SELECT MIN(VALUE) MIN_VAL FROM OIK_BASE.VED\n" +
                "WHERE IND=1498 AND TIMES BETWEEN trunc(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))  +1/24 AND " +
                "trunc(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))  +1 ) and ROWNUM=1) a, (SELECT /*+INDEX_COMBINE(VED)*/" +
                "TO_NUMBER(TO_CHAR(TIMES,'hh24')) H_MAX FROM OIK_BASE.VED WHERE IND=1498 AND TIMES BETWEEN trunc(TO_DATE('" +
                date + "','dd.mm.yyyy HH24:MI:SS'))  +1/24 AND trunc(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))  +1 " +
                "AND VALUE =  (SELECT MAX(VALUE) MAX_VAL FROM OIK_BASE.VED WHERE IND=1498 AND TIMES BETWEEN trunc(TO_DATE('" +
                date + "','dd.mm.yyyy HH24:MI:SS'))  +1/24 AND trunc(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))  +1) " +
                "and ROWNUM=1) b UNION SELECT mi.N_ORDER IND, mi.NAME, CASE WHEN mi.N_ORDER=28 THEN V_MIN/100 ELSE V_MIN " +
                "END AS V_MIN, CASE WHEN mi.N_ORDER=28 THEN V_MAX/100 ELSE V_MAX END AS V_MAX  FROM (SELECT i.N_ORDER,o.NAME, " +
                "SUM(VALUE*SIGN) V_MIN  FROM OIK_BASE.IND_SVEDEN i, OIK_BASE.VED v, OIK_BASE.LIST_SVEDEN o WHERE o.EGK is " +
                "NULL AND v.IND=i.IND AND TIMES=(SELECT /*+INDEX_COMBINE(VED)*/  TIMES FROM OIK_BASE.VED WHERE IND=1498 AND " +
                "TIMES BETWEEN trunc(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))  +1/24 AND trunc(TO_DATE('" + date +
                "','dd.mm.yyyy HH24:MI:SS'))  +1 AND VALUE =  (SELECT MIN(VALUE) MIN_VAL FROM OIK_BASE.VED WHERE IND=1498 " +
                "AND TIMES BETWEEN trunc(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))  +1/24 AND trunc(TO_DATE('" +
                date + "','dd.mm.yyyy HH24:MI:SS'))  +1 ) and ROWNUM=1) AND i.N_ORDER=o.N_ORDER GROUP BY i.N_ORDER,o.NAME\n" +
                "UNION SELECT o.N_ORDER IND, o.NAME, SUM(VALUE*SIGN) V_MIN FROM OIK_BASE.IND_SVEDEN i, OIK_BASE.VED v, " +
                "OIK_BASE.LIST_SVEDEN o WHERE o.EGK=1 AND v.IND=i.IND AND TIMES= (SELECT /*+INDEX_COMBINE(VED)*/  TIMES\n" +
                "FROM OIK_BASE.VED WHERE IND=1498 AND TIMES BETWEEN trunc(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))  +1/24 " +
                "AND trunc(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))  +1 AND VALUE =  (SELECT MIN(VALUE) MIN_VAL " +
                "FROM OIK_BASE.VED WHERE IND=1498 AND TIMES BETWEEN trunc(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))  +1/24 " +
                "AND trunc(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))  +1 ) and ROWNUM=1) AND i.EGK=1 GROUP BY " +
                "o.N_ORDER, o.NAME) mi, (SELECT i.N_ORDER,o.NAME, SUM(VALUE*SIGN) V_MAX  FROM OIK_BASE.IND_SVEDEN i, " +
                "OIK_BASE.VED v, OIK_BASE.LIST_SVEDEN o WHERE o.EGK is NULL AND v.IND=i.IND AND TIMES= ( SELECT " +
                "/*+INDEX_COMBINE(VED)*/  TIMES FROM OIK_BASE.VED  WHERE IND=1498 AND TIMES BETWEEN trunc(TO_DATE('" +
                date + "','dd.mm.yyyy HH24:MI:SS'))  +1/24 AND trunc(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))  " +
                "+1 AND VALUE =  (SELECT MAX(VALUE) MAX_VAL FROM OIK_BASE.VED WHERE IND=1498 AND TIMES BETWEEN " +
                "trunc(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))  +1/24 AND trunc(TO_DATE('" + date +
                "','dd.mm.yyyy HH24:MI:SS'))  +1) and ROWNUM=1) AND i.N_ORDER=o.N_ORDER GROUP BY i.N_ORDER,o.NAME\n" +
                "UNION SELECT o.N_ORDER IND, o.NAME, SUM(VALUE*SIGN) V_MAX FROM OIK_BASE.IND_SVEDEN i, OIK_BASE.VED v, " +
                "OIK_BASE.LIST_SVEDEN o WHERE o.EGK=1 AND v.IND=i.IND AND TIMES= (SELECT /*+INDEX_COMBINE(VED)*/  TIMES\n" +
                "FROM OIK_BASE.VED  WHERE IND=1498 AND TIMES BETWEEN trunc(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))  +1/24 " +
                "AND trunc(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))  +1 AND VALUE =  (SELECT MAX(VALUE) MAX_VAL " +
                "FROM OIK_BASE.VED WHERE IND=1498 AND TIMES BETWEEN trunc(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))  +1/24 " +
                "AND trunc(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))  +1) and ROWNUM=1) AND i.EGK=1 GROUP BY " +
                "o.N_ORDER, o.NAME) ma WHERE mi.N_ORDER=ma.N_ORDER) v, (SELECT c.ID, d.VALUE/1000 VALUE  FROM COMMON.V_ENERGY_KDEt d," +
                " COMMON.E_SVEDEN c WHERE DATES=trunc(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND (c.ID+20*100)=d.KDE_F) e\n" +
                "WHERE v.IND=e.ID(+) ORDER BY v.IND";

        List<BalanceIPSUkraine> balanceList = jdbcTemplate.query(sql, new RowMapper<BalanceIPSUkraine>() {
            public BalanceIPSUkraine mapRow(ResultSet resultSet, int i) throws SQLException {
                BalanceIPSUkraine row = new BalanceIPSUkraine();
                row.setColumn1(resultSet.getInt(Constants.TABLE_BALANCE_COLUMN_1));
                row.setColumn2(resultSet.getString(Constants.TABLE_BALANCE_COLUMN_2));
                row.setColumn3(resultSet.getFloat(Constants.TABLE_BALANCE_COLUMN_3));
                row.setColumn4(resultSet.getFloat(Constants.TABLE_BALANCE_COLUMN_4));
                row.setColumn5(resultSet.getFloat(Constants.TABLE_BALANCE_COLUMN_5));
                return row;
            }
        });
        return balanceList;
    }

    public ConsumptionControl getConsumption() {
        return null;
    }

    public HydroStationCondition getHydroStationCondition() {
        return null;
    }
}
