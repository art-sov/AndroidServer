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

    //consolidate report table 1
    public List<BalanceIPSUkraine> getBalance() {
        String date = dateUtil.getDateYesterday();

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

    //-----------------------------------------------------------------------------------
    //consolidate report table 2
    public List<ConsumptionControl> getConsumption() {
        String date = dateUtil.getDateForConsolidateReport();

        String sql = "SELECT CASE WHEN n.N=1 THEN 1 WHEN n.N=2 THEN 2 WHEN n.N=3  THEN 3\n" +
                "            WHEN n.N=4 THEN 4 WHEN n.N=5 THEN 5 WHEN n.N=6  THEN 6\n" +
                "            WHEN n.N=7 THEN 7 WHEN n.N=8 THEN 8 WHEN n.N=10 THEN 10 \n" +
                "            END ID,\n" +
                "       r.PARAMS E_FAKT,o.O_FAKT,g.PLN, o.O_FAKT-g.PLN DIFF FROM\n" +
                "(SELECT  1 N FROM DUAL UNION\n" +
                "SELECT  2 N FROM DUAL UNION\n" +
                "SELECT  3 N FROM DUAL UNION\n" +
                "SELECT  4 N FROM DUAL UNION\n" +
                "SELECT  5 N FROM DUAL UNION\n" +
                "SELECT  6 N FROM DUAL UNION\n" +
                "SELECT  7 N FROM DUAL UNION\n" +
                "SELECT  8 N FROM DUAL UNION\n" +
                "SELECT 10 N FROM DUAL) n,       \n" +
                "--  ФАКТ ЕНЕРГОСИСТЕМ\n" +
                "(SELECT                  1 N, NULL PARAMS FROM DUAL  \n" +
                "  UNION\n" +
                "SELECT /*+PK_ARHIVTI */  2 N, PARAMS FROM OIK_BASE.ARHIVTI WHERE IND=1609 and TIME= \n" +
                "(SELECT /* +INDEX (PK_ARHIVTI) */  TIME FROM OIK_BASE.ARHIVTI  \n" +
                "WHERE IND =1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50) AND PARAMS =(\n" +
                "SELECT MAX(PARAMS) FROM OIK_BASE.ARHIVTI \n" +
                "WHERE IND=1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50)) AND ROWNUM=1)\n" +
                "  UNION\n" +
                "SELECT /*+PK_ARHIVTI */  3 N, PARAMS FROM OIK_BASE.ARHIVTI WHERE IND=1615 and TIME= \n" +
                "(SELECT /* +INDEX (PK_ARHIVTI) */  TIME FROM OIK_BASE.ARHIVTI  \n" +
                "WHERE IND =1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50) AND PARAMS =(\n" +
                "SELECT MAX(PARAMS) FROM OIK_BASE.ARHIVTI \n" +
                "WHERE IND=1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50)) AND ROWNUM=1)\n" +
                "  UNION\n" +
                "SELECT /*+PK_ARHIVTI */  4 N, PARAMS FROM OIK_BASE.ARHIVTI WHERE IND=1611 and TIME= \n" +
                "(SELECT /* +INDEX (PK_ARHIVTI) */  TIME FROM OIK_BASE.ARHIVTI  \n" +
                "WHERE IND =1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50) AND PARAMS =(\n" +
                "SELECT MAX(PARAMS) FROM OIK_BASE.ARHIVTI \n" +
                "WHERE IND=1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50)) AND ROWNUM=1)\n" +
                "  UNION\n" +
                "SELECT /*+PK_ARHIVTI */  5 N, PARAMS FROM OIK_BASE.ARHIVTI WHERE IND=1608 and TIME= \n" +
                "(SELECT /* +INDEX (PK_ARHIVTI) */  TIME FROM OIK_BASE.ARHIVTI  \n" +
                "WHERE IND =1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50) AND PARAMS =(\n" +
                "SELECT MAX(PARAMS) FROM OIK_BASE.ARHIVTI \n" +
                "WHERE IND=1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50)) AND ROWNUM=1)\n" +
                "  UNION\n" +
                "SELECT /*+PK_ARHIVTI */  6 N, PARAMS FROM OIK_BASE.ARHIVTI WHERE IND=1614 and TIME= \n" +
                "(SELECT /* +INDEX (PK_ARHIVTI) */  TIME FROM OIK_BASE.ARHIVTI  \n" +
                "WHERE IND =1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50) AND PARAMS =(\n" +
                "SELECT MAX(PARAMS) FROM OIK_BASE.ARHIVTI \n" +
                "WHERE IND=1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50)) AND ROWNUM=1)\n" +
                "  UNION\n" +
                "SELECT /*+PK_ARHIVTI */  7 N, PARAMS FROM OIK_BASE.ARHIVTI WHERE IND=1613 and TIME= \n" +
                "(SELECT /* +INDEX (PK_ARHIVTI) */  TIME FROM OIK_BASE.ARHIVTI  \n" +
                "WHERE IND =1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50) AND PARAMS =(\n" +
                "SELECT MAX(PARAMS) FROM OIK_BASE.ARHIVTI \n" +
                "WHERE IND=1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50)) AND ROWNUM=1) \n" +
                "  UNION\n" +
                "SELECT   8 N, NULL FROM DUAL   \n" +
                "  UNION\n" +
                "SELECT /*+PK_ARHIVTI */  10 N, PARAMS FROM OIK_BASE.ARHIVTI WHERE IND=1628 and TIME= \n" +
                "(SELECT /* +INDEX (PK_ARHIVTI) */  TIME FROM OIK_BASE.ARHIVTI  \n" +
                "WHERE IND =1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50) AND PARAMS =(\n" +
                "SELECT MAX(PARAMS) FROM OIK_BASE.ARHIVTI \n" +
                "WHERE IND=1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50)) AND ROWNUM=1)) r,\n" +
                "--  ФАКТ обленерго\n" +
                "(SELECT /*+PK_ARHIVTI */  1 N, NULL O_FAKT FROM DUAL \n" +
                "    UNION \n" +
                " SELECT /*+PK_ARHIVTI */  2 N, SUM(PARAMS)  O_FAKT FROM OIK_BASE.ARHIVTI WHERE \n" +
                " IND IN(1096,1097,1098) and   TIME =  \n" +
                " (SELECT /* +INDEX (PK_ARHIVTI) */  TIME FROM OIK_BASE.ARHIVTI  \n" +
                "WHERE IND =1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50) AND PARAMS =(\n" +
                "SELECT MAX(PARAMS) FROM OIK_BASE.ARHIVTI \n" +
                "WHERE IND=1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50)) AND ROWNUM=1)  GROUP BY TIME\n" +
                "    UNION \n" +
                " SELECT /*+PK_ARHIVTI */  3 N, SUM(PARAMS)  O_FAKT FROM OIK_BASE.ARHIVTI \n" +
                " WHERE IND IN(1126,1127,1128,1099,1100) and TIME = \n" +
                " (SELECT /* +INDEX (PK_ARHIVTI) */  TIME FROM OIK_BASE.ARHIVTI  \n" +
                "WHERE IND =1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50) AND PARAMS =(\n" +
                "SELECT MAX(PARAMS) FROM OIK_BASE.ARHIVTI \n" +
                "WHERE IND=1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50)) AND ROWNUM=1) GROUP BY TIME\n" +
                "    UNION \n" +
                " SELECT /*+PK_ARHIVTI */  4 N, SUM(PARAMS)  O_FAKT FROM OIK_BASE.ARHIVTI WHERE \n" +
                " IND in (1101,1106,1107,1108,1109) AND TIME = \n" +
                " (SELECT /* +INDEX (PK_ARHIVTI) */  TIME FROM OIK_BASE.ARHIVTI  \n" +
                "WHERE IND =1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50) AND PARAMS =(\n" +
                "SELECT MAX(PARAMS) FROM OIK_BASE.ARHIVTI \n" +
                "WHERE IND=1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50)) AND ROWNUM=1) GROUP BY TIME \n" +
                "    UNION \n" +
                " SELECT /*+PK_ARHIVTI */  5 N, SUM(PARAMS)  O_FAKT FROM OIK_BASE.ARHIVTI WHERE \n" +
                " IND IN(1092,1093,1094,1095) and TIME = \n" +
                " (SELECT /* +INDEX (PK_ARHIVTI) */  TIME FROM OIK_BASE.ARHIVTI  \n" +
                "WHERE IND =1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50) AND PARAMS =(\n" +
                "SELECT MAX(PARAMS) FROM OIK_BASE.ARHIVTI \n" +
                "WHERE IND=1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50)) AND ROWNUM=1) GROUP BY TIME\n" +
                "    UNION \n" +
                " SELECT /*+PK_ARHIVTI */  6 N, SUM(PARAMS)  O_FAKT FROM OIK_BASE.ARHIVTI WHERE \n" +
                " IND IN(1119,1124,1125) and TIME = \n" +
                " (SELECT /* +INDEX (PK_ARHIVTI) */  TIME FROM OIK_BASE.ARHIVTI  \n" +
                "WHERE IND =1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50) AND PARAMS =(\n" +
                "SELECT MAX(PARAMS) FROM OIK_BASE.ARHIVTI \n" +
                "WHERE IND=1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50)) AND ROWNUM=1) GROUP BY TIME\n" +
                "    UNION \n" +
                " SELECT /*+PK_ARHIVTI */  7 N, SUM(PARAMS)  O_FAKT FROM OIK_BASE.ARHIVTI WHERE \n" +
                " IND IN(1112,1113,1116,1117,1118) and TIME = \n" +
                " (SELECT /* +INDEX (PK_ARHIVTI) */  TIME FROM OIK_BASE.ARHIVTI  \n" +
                "WHERE IND =1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50) AND PARAMS =(\n" +
                "SELECT MAX(PARAMS) FROM OIK_BASE.ARHIVTI \n" +
                "WHERE IND=1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50)) AND ROWNUM=1) GROUP BY TIME\n" +
                "    UNION \n" +
                " SELECT /*+PK_ARHIVTI */  8 N, NULL O_FAKT FROM DUAL\n" +
                "    UNION \n" +
                " SELECT /*+PK_ARHIVTI */  10 N, SUM(PARAMS) O_FAKT FROM OIK_BASE.ARHIVTI WHERE IND in \n" +
                "         (1096,1097,1098,1126,1127,1128,1099,1100,1101,1106,1107,1108,1109,1092,1093,\n" +
                "          1094,1095,1119,1124,1125,1112,1113,1116,1117,1118) AND  TIME=    \n" +
                "(SELECT /* +INDEX (PK_ARHIVTI) */  TIME FROM OIK_BASE.ARHIVTI  \n" +
                "WHERE IND =1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50) AND PARAMS =(\n" +
                "SELECT MAX(PARAMS) FROM OIK_BASE.ARHIVTI \n" +
                "WHERE IND=1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50)) AND ROWNUM=1)  GROUP BY TIME) o,\n" +
                                "(SELECT /*+INDEX_COMBINE(PLAN)*/  1 N, NULL PLN FROM DUAL  \n" +
                "    UNION \n" +
                " SELECT /*+INDEX_COMBINE(PLAN)*/  2 N, VALUE PLN FROM OIK_BASE.PLAN WHERE IND=650 and TIMES = TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1/24 \n" +
                "    UNION \n" +
                " SELECT /*+INDEX_COMBINE(PLAN)*/  4 N, VALUE PLN FROM OIK_BASE.PLAN WHERE IND=652 and TIMES = TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1/24  \n" +
                "    UNION \n" +
                " SELECT /*+INDEX_COMBINE(PLAN)*/  5 N, VALUE PLN FROM OIK_BASE.PLAN WHERE IND=649 and TIMES = TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1/24  \n" +
                "    UNION \n" +
                " SELECT /*+INDEX_COMBINE(PLAN)*/  6 N, VALUE PLN FROM OIK_BASE.PLAN WHERE IND=655 and TIMES = TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1/24  \n" +
                "    UNION \n" +
                " SELECT /*+INDEX_COMBINE(PLAN)*/  7 N, VALUE PLN FROM OIK_BASE.PLAN WHERE IND=654 and TIMES = TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1/24  \n" +
                "    UNION \n" +
                " SELECT /*+INDEX_COMBINE(PLAN)*/  8 N, VALUE PLN FROM OIK_BASE.PLAN WHERE IND=653 and TIMES = TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1/24  \n" +
                "    UNION \n" +
                " SELECT /*+INDEX_COMBINE(PLAN)*/  3 N, SUM(VALUE) PLN FROM OIK_BASE.PLAN WHERE IND in ( 651,656) AND TIMES=\n" +
                "    TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1/24  GROUP BY TIMES \n" +
                "    UNION \n" +
                " SELECT /*+INDEX_COMBINE(PLAN)*/  10 N, SUM(VALUE) PLN FROM OIK_BASE.PLAN WHERE IND in \n" +
                "    (650,652,649,655,654,653, 651,656) AND TIMES=TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1/24 GROUP BY TIMES) g\n" +
                "WHERE n.N=r.N(+) AND \n" +
                "      n.N=o.N(+) AND \n" +
                "      n.N=g.N(+)\n";
        List<ConsumptionControl> consumptionList = jdbcTemplate.query(sql, new RowMapper<ConsumptionControl>() {

            public ConsumptionControl mapRow(ResultSet resultSet, int i) throws SQLException {
                ConsumptionControl row = new ConsumptionControl();
                row.setColumn1(resultSet.getInt(Constants.TABLE_CONSUMPTION_COLUMN_1));
                row.setColumn2(resultSet.getInt(Constants.TABLE_CONSUMPTION_COLUMN_2));
                row.setColumn3(resultSet.getInt(Constants.TABLE_CONSUMPTION_COLUMN_3));
                row.setColumn4(resultSet.getInt(Constants.TABLE_CONSUMPTION_COLUMN_4));
                row.setColumn5(resultSet.getInt(Constants.TABLE_CONSUMPTION_COLUMN_5));
                return row;
            }
        });
        return consumptionList;
    }

    public String getMaxTime() {
        String date = dateUtil.getDateForConsolidateReport();
        String sql = "SELECT /* +INDEX(PK_ARHIVTI)  */  TO_CHAR(TIME,'hh24:mi') T_MAX FROM OIK_BASE.ARHIVTI  WHERE IND = 1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1 \n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50) AND PARAMS =(\n" +
                "SELECT /* +INDEX(PK_ARHIVTI)  */  MAX(PARAMS) FROM OIK_BASE.ARHIVTI WHERE IND=1628 AND TIME BETWEEN TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS')) AND TRUNC(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))+1\n" +
                "AND TO_NUMBER(TO_CHAR(TIME,'MI')) IN (0,10,20,30,40,50)) AND ROWNUM=1\n";
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    //---------------------------------------------------------------------------------
    //consolidate report table 3
    public List<HydroStationCondition> getHydroStationCondition() {
        String date = dateUtil.getDateForConsolidateReport();

        String sql = "SELECT ID,NAME,TO_CHAR(NPR,'999.99') NPR,HI_BJEF,INCOME,OUTCOME FROM\n" +
                "  (SELECT 1 ID, 103.0 NPR, 513531 STAN_COD FROM DUAL UNION\n" +
                "   SELECT 2 ID, 91.5  NPR, 513533 STAN_COD FROM DUAL UNION\n" +
                "   SELECT 3 ID, 81.0  NPR, 513133 STAN_COD FROM DUAL UNION\n" +
                "   SELECT 4 ID, 64.0  NPR, 513134 STAN_COD FROM DUAL UNION\n" +
                "   SELECT 5 ID, 51.4  NPR, 513130 STAN_COD FROM DUAL UNION\n" +
                "   SELECT 6 ID, 16.0  NPR, 513135 STAN_COD FROM DUAL UNION\n" +
                "   SELECT 7 ID, 121.0 NPR, 563737 STAN_COD FROM DUAL UNION\n" +
                "   SELECT 8 ID, NULL  NPR, 563738 STAN_COD FROM DUAL) n,\n" +
                "  (SELECT g.STAN_COD,l.STAN_SHORTNAME_UKR NAME,g.HI_BJEF,g.INCOME,g.OUTCOME\n" +
                "   FROM STATUS.LIST_GES_PARAMS g, STATUS.LIST_STAN l\n" +
                "   WHERE DATES=trunc(TO_DATE('" + date + "','dd.mm.yyyy HH24:MI:SS'))\n" +
                "         AND g.STAN_COD=l.STAN_COD ) v\n" +
                "WHERE n.STAN_COD=v.STAN_COD(+)\n" +
                "ORDER BY ID";
        List<HydroStationCondition> resultList = jdbcTemplate.query(sql, new RowMapper<HydroStationCondition>() {
            @Override
            public HydroStationCondition mapRow(ResultSet resultSet, int i) throws SQLException {
                HydroStationCondition row = new HydroStationCondition();
                row.setColumn1(resultSet.getInt(Constants.TABLE_HYDROSTATION_COLUMN1));
                row.setColumn2(resultSet.getString(Constants.TABLE_HYDROSTATION_COLUMN2));
                row.setColumn3(resultSet.getFloat(Constants.TABLE_HYDROSTATION_COLUMN3));
                row.setColumn4(resultSet.getFloat(Constants.TABLE_HYDROSTATION_COLUMN4));
                row.setColumn5(resultSet.getInt(Constants.TABLE_HYDROSTATION_COLUMN5));
                row.setColumn6(resultSet.getInt(Constants.TABLE_HYDROSTATION_COLUMN6));
                return row;
            }
        });
        return resultList;
    }
}
