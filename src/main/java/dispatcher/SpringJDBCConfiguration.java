package dispatcher;

import dispatcher.model.dao.LastInfoDao;
import dispatcher.model.dao.StationDao;
import dispatcher.model.dao.UnitDao;
import dispatcher.model.dao.UnitStatusDao;
import dispatcher.model.repository.LastInfoDaoImpl;
import dispatcher.model.repository.StationDaoImpl;
import dispatcher.model.repository.UnitDaoImpl;
import dispatcher.model.repository.UnitStatusDaoImpl;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by Sovalov.AV on 13.04.2018.
 */
@Configuration
public class SpringJDBCConfiguration {

    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@//10.63.11.46:1521/necp");
        dataSource.setUsername("INET_MPL");
        dataSource.setPassword("mpl_inet");
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(10);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public StationDao stationDao(){
        StationDaoImpl empDao = new StationDaoImpl();
        empDao.setJdbcTemplate(jdbcTemplate());
        return empDao;
    }

    @Bean
    public LastInfoDao lastInfoDao(){
        LastInfoDaoImpl lastInfoDao = new LastInfoDaoImpl();
        lastInfoDao.setJdbcTemplate(jdbcTemplate());
        return lastInfoDao;
    }

    @Bean
    public UnitDao unitDao(){
        UnitDaoImpl unitDao = new UnitDaoImpl();
        unitDao.setJdbcTemplate(jdbcTemplate());
        return unitDao;
    }
    @Bean
    public UnitStatusDao UnitStatusDao(){
        UnitStatusDaoImpl unitStatusDao = new UnitStatusDaoImpl();
        unitStatusDao.setJdbcTemplate(jdbcTemplate());
        return unitStatusDao;
    }
}
