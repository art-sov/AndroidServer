package dispatcher.service;

import dispatcher.dao.RemarksDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Sovalov.AV on 20.09.2018.
 */
@Service
public class RemarksService {

    private final RemarksDaoImpl remarksDao;

    @Autowired
    public RemarksService(RemarksDaoImpl remarksDao) {
        this.remarksDao = remarksDao;
    }

    public String getRemarks(){
        return remarksDao.getRemarks();
    }
}
