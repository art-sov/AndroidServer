package dispatcher.dao;

import dispatcher.model.BalanceIPSUkraine;
import dispatcher.model.ConsumptionControl;
import dispatcher.model.HydroStationCondition;

import java.util.List;

/**
 * Created by Sovalov.AV on 30.08.2018.
 */
public interface ConsolidateReportDao {

    List<BalanceIPSUkraine> getBalance();

    ConsumptionControl getConsumption();

    HydroStationCondition getHydroStationCondition();

}
