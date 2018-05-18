package dispatcher.controller;

import com.google.common.collect.ImmutableList;
import dispatcher.dto.StationDto;
import dispatcher.service.StationService;
import dispatcher.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StationConditionControllerTest {

    @Mock
    private StationService stationService;
//    @Mock
//    private DateUtil dateUtil;

    @InjectMocks
    private StationConditionController sut;


    @Test
    public void getStationCondition() {

        //prepare
        when(stationService.getStationCondition()).thenReturn(ImmutableList.of());

        //testing
        ResponseEntity<List<StationDto>> list = sut.getStationCondition("");

        //validate
        verify(stationService).getStationCondition();
    }
}