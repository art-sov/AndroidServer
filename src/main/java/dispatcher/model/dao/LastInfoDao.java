package dispatcher.model.dao;

import dispatcher.model.entity.LastInfo;

import java.util.Date;
import java.util.List;

/**
 * Created by Sovalov.AV on 13.04.2018.
 */
public interface LastInfoDao {

    List<LastInfo> getLastInfo();
}
