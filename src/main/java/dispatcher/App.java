package dispatcher;

import dispatcher.model.dao.StationDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Sovalov.AV on 13.04.2018.
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                SpringJDBCConfiguration.class);

        StationDao stDao = applicationContext.getBean(StationDao.class);

        String fullNameStation = stDao.getStationFullName(2);
        System.out.println("Station with id2: " + fullNameStation);
        applicationContext.close();
    }
}
