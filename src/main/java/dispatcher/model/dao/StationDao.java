package dispatcher.model.dao;

import dispatcher.model.dto.StationDto;
import dispatcher.model.entity.Station;

import java.util.List;

/**
 * Created by Sovalov.AV on 13.04.2018.
 */
public interface StationDao {
    String getStationFullName(int id);

    List<StationDto> getAllStationDto();

    List<Station> getAllStation();
}
