package dispatcher.rest;

import dispatcher.model.dto.StationDto;
import dispatcher.model.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Sovalov.AV on 20.04.2018.
 */
@RestController
public class StationConditionRESTController {

    @Autowired
    private StationService stationService;

    @GetMapping("/condition")
    public List<StationDto> getStationCondition(){
        return stationService.getStationCondition();
    }
}
