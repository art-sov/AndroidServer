package dispatcher.dao;

import dispatcher.model.FuelFlow;

import java.util.List;
import java.util.Map;

/**
 * Created by Sovalov.AV on 14.11.2018.
 */
public interface FuelFlowDao {

    List<FuelFlow> getListCoalFlow(int day);

    List<FuelFlow> getListFuelFlow(int type_fuel);
}
