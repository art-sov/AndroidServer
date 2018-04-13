package dispatcher;

import dispatcher.model.dao.LastInfoDao;
import dispatcher.model.dao.StationDao;
import dispatcher.model.entity.LastInfo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sovalov.AV on 13.04.2018.
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                SpringJDBCConfiguration.class);

        StationDao stDao = applicationContext.getBean(StationDao.class);
        LastInfoDao lastInfoDao = applicationContext.getBean(LastInfoDao.class);

        String fullNameStation = stDao.getStationFullName(2);
        System.out.println("Station with id2: " + fullNameStation);

        List<LastInfo> lastInfoList = new ArrayList<LastInfo>();
        lastInfoList = lastInfoDao.getLastInfo();
        System.out.println(lastInfoList);

        applicationContext.close();




    }
}
