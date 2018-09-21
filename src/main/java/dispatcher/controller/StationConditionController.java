package dispatcher.controller;

import com.google.gson.Gson;
import dispatcher.dto.StationDto;
import dispatcher.model.*;
import dispatcher.service.ConsolidateService;
import dispatcher.service.RemarksService;
import dispatcher.service.StationService;
import dispatcher.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    private RemarksService remarksService;

    private static final Gson gson = new Gson();

    @Autowired
    public StationConditionController(StationService stationService,
                                      DateUtil dateUtil,
                                      ConsolidateService consolidateService,
                                      RemarksService remarksService) {
        this.stationService = stationService;
        this.dateUtil = dateUtil;
        this.consolidateService = consolidateService;
        this.remarksService = remarksService;
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

    //consolidate report table 1
    @RequestMapping(value = "/consolidate_balance", method = RequestMethod.GET)
    public ResponseEntity<List<BalanceIPSUkraine>> getBalanceReport(@RequestParam String date){
        dateUtil.setDate(date);

        List<BalanceIPSUkraine> balance = consolidateService.getConsolidateReport();
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    //consolidate report table 2
    @RequestMapping(value = "/consolidate_consumption_maxtime", method = RequestMethod.GET)
    public ResponseEntity<MaxTime> getTimeMax(@RequestParam String date){
        dateUtil.setDate(date);
        String result = consolidateService.getMaxTime();
        return new ResponseEntity<>(new MaxTime(result), HttpStatus.OK);
    }

    @RequestMapping(value = "/consolidate_consumption", method = RequestMethod.GET)
    public ResponseEntity<List<ConsumptionControl>> getConsumption(@RequestParam String date){
        dateUtil.setDate(date);

        List<ConsumptionControl> consumption = consolidateService.getConsumptionControl();
        return new ResponseEntity<>(consumption, HttpStatus.OK);
    }

    //consolidate report table 3
    @RequestMapping(value = "consolidate_hydrostation", method = RequestMethod.GET)
    public ResponseEntity<List<HydroStationCondition>> getHydroStation(@RequestParam String date){
        dateUtil.setDate(date);

        List<HydroStationCondition> hydroStationConditions = consolidateService.getHydroStationCondition();
        return new ResponseEntity<List<HydroStationCondition>>(hydroStationConditions, HttpStatus.OK);
    }

    //remarks
    @RequestMapping(value = "remarks", method = RequestMethod.GET)
    public ResponseEntity<String> getRemarks(@RequestParam String date) {
        dateUtil.setDate(date);

        String remarks = remarksService.getRemarks();
        return new ResponseEntity<>(remarks, HttpStatus.OK);
    }
}
