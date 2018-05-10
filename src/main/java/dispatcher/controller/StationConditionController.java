package dispatcher.controller;

import com.sun.istack.internal.Nullable;
import dispatcher.dto.StationDto;
import dispatcher.service.StationService;
import dispatcher.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Sovalov.AV on 20.04.2018.
 */
@RestController
public class StationConditionController {

    private final StationService stationService;
    private DateUtil dateUtil;

    @Autowired
    public StationConditionController(StationService stationService, DateUtil dateUtil) {
        this.stationService = stationService;
        this.dateUtil = dateUtil;
    }

    @RequestMapping(value = "/condition", method = RequestMethod.GET)
    public ResponseEntity<List<StationDto>> getStationCondition(@RequestParam String date){

        //todo решить проблему передачи в параметре null
        dateUtil.setDate(date);
        //dateUtil.setDate("09.05.2018 07:00");
        List<StationDto> stationDtoList = stationService.getStationCondition();
            if (stationDtoList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        return new ResponseEntity<>(stationDtoList, HttpStatus.OK);
    }
}
