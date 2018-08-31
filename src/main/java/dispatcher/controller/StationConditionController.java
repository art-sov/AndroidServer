package dispatcher.controller;

import dispatcher.dto.StationDto;
import dispatcher.model.BalanceIPSUkraine;
import dispatcher.model.User;
import dispatcher.service.ConsolidateService;
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

   // @Autowired
    private StationService stationService;

   // @Autowired
    private DateUtil dateUtil;

    private ConsolidateService consolidateService;

    @Autowired
    public StationConditionController(StationService stationService,
                                      DateUtil dateUtil,
                                      ConsolidateService consolidateService) {
        this.stationService = stationService;
        this.dateUtil = dateUtil;
        this.consolidateService = consolidateService;
    }

    @RequestMapping(value = "/condition", method = RequestMethod.GET)
    public ResponseEntity<List<StationDto>> getStationCondition(@RequestParam String date){

        dateUtil.setDate(date);

        List<StationDto> stationDtoList = stationService.getStationCondition();
            if (stationDtoList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        return new ResponseEntity<>(stationDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public ResponseEntity<User> getAuthToken(){
        User user = new User("authToken");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/consolidate_balance", method = RequestMethod.GET)
    public ResponseEntity<List<BalanceIPSUkraine>> getBalanceReport(@RequestParam String date){
        dateUtil.setDate(date);

        List<BalanceIPSUkraine> balance = consolidateService.getConsolidateReport();
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }
}
