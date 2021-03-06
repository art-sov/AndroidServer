package dispatcher.controller;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import com.google.common.collect.ImmutableList;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import dispatcher.dto.StationDto;
import dispatcher.service.StationService;
import dispatcher.util.DateUtil;
import org.json.JSONException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StationConditionControllerTest {

    private static String content;

    @BeforeClass
    public static void parseJSONFile() throws IOException {
        String filename = "src/test/resources/response.txt";
        content = new String(Files.readAllBytes(Paths.get(filename)));
    }

    @Mock
    private StationService stationServiceMock;
    @Mock
    private DateUtil dateUtil;

    @InjectMocks
    private StationConditionController sut;

    @Test
    public void isGetStationConditionInvoked() {
        //prepare
        when(stationServiceMock.getStationCondition()).thenReturn(ImmutableList.of());
        //testing
        ResponseEntity<List<StationDto>> list = sut.getStationCondition("");
        //validate
        verify(stationServiceMock).getStationCondition();
    }

    @Test
    public void testSizeListStations() {

        JsonParser jsonParser = new JsonParser();
        JsonArray array = jsonParser.parse(content).getAsJsonArray();
        Gson gson = new Gson();
        Type token = new TypeToken<List<StationDto>>() {
        }.getType();
        List<StationDto> list = gson.fromJson(array, token);

        when(stationServiceMock.getStationCondition()).thenReturn(list);
        List<StationDto> response = sut.getStationCondition("current").getBody();
        assertEquals(46, response.size());
    }

    @Test
    public void testListUnitFromLuhanskStation() throws JSONException {

        JsonParser jsonParser = new JsonParser();
        JsonArray array = jsonParser.parse(content).getAsJsonArray();
        Gson gson = new Gson();
        Type token = new TypeToken<List<StationDto>>() {
        }.getType();
        List<StationDto> list = gson.fromJson(array, token);

        //prepare
        when(stationServiceMock.getStationCondition()).thenReturn(list);

        List<String> listUnitOfLuhansk = new ArrayList<>();
        listUnitOfLuhansk.add("9");
        listUnitOfLuhansk.add("10");
        listUnitOfLuhansk.add("11");
        listUnitOfLuhansk.add("12");
        listUnitOfLuhansk.add("13");
        listUnitOfLuhansk.add("14");
        listUnitOfLuhansk.add("15");

        JsonElement element = array.get(0);
        JsonObject obj = element.getAsJsonObject();
        String name = obj.get("name").getAsString();

        List<String> nameOfUnits = new ArrayList<>();

        if (name.equals("Луганская ТЭС")) {
            JsonArray arrayBlock = (JsonArray) obj.get("blockDtoList");
            for (int i = 0; i < arrayBlock.size(); i++) {
                JsonObject block = arrayBlock.get(i).getAsJsonObject();
                JsonObject unit1 = block.get("unit1").getAsJsonObject();
                String nameUnit = unit1.get("name").getAsString();
                nameOfUnits.add(nameUnit);
            }
        }
        //validate
        assertThat(nameOfUnits, is(listUnitOfLuhansk));
    }

    @Test
    public void testHttpStatusNotFound(){

        ResponseEntity actual = new ResponseEntity(HttpStatus.NOT_FOUND);
        List<StationDto> list = new ArrayList<>();

        when(stationServiceMock.getStationCondition()).thenReturn(list);
        ResponseEntity expected = sut.getStationCondition("");

        assertEquals(expected, actual);
    }
}