package dispatcher.controller;

import dispatcher.dto.StationDto;
import dispatcher.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Sovalov.AV on 20.04.2018.
 */
@RestController
public class StationConditionController {

    private final StationService stationService;

    @Autowired
    public StationConditionController(StationService stationService) {
        this.stationService = stationService;
    }

    @RequestMapping(value = "/condition", method = RequestMethod.GET)
    public ResponseEntity<List<StationDto>> getStationCondition(){
        List<StationDto> stationDtoList = stationService.getStationCondition();
            if (stationDtoList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        return new ResponseEntity<>(stationDtoList, HttpStatus.OK);
    }
}
