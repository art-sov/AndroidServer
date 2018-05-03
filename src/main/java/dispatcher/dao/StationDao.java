package dispatcher.dao;

import dispatcher.dto.StationDto;
import dispatcher.model.Station;

import java.util.List;

/**
 * Created by Sovalov.AV on 13.04.2018.
 */

public interface StationDao {
    String getStationFullName(int id);

    List<StationDto> getAllStationDto();

    List<Station> getAllStation();
}
