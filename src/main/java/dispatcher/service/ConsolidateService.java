package dispatcher.service;

import dispatcher.dao.ConsolidateReportDaoImpl;
import dispatcher.model.BalanceIPSUkraine;
import dispatcher.model.ConsumptionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Sovalov.AV on 30.08.2018.
 */
@Service
public class ConsolidateService {

    private final ConsolidateReportDaoImpl consolidateReportDao;

    @Autowired
    public ConsolidateService(ConsolidateReportDaoImpl consolidateReportDao) {
        this.consolidateReportDao = consolidateReportDao;
    }

    public List<BalanceIPSUkraine> getConsolidateReport() {
        return consolidateReportDao.getBalance();
    }

    public List<ConsumptionControl> getConsumptionControl() {
        return consolidateReportDao.getConsumption();
    }

    public String getMaxTime(){
        return consolidateReportDao.getMaxTime();
    }
}
