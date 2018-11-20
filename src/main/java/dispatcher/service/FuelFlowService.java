package dispatcher.service;

import dispatcher.dao.FuelFlowDaoImpl;
import dispatcher.dto.FuelFlowDto;
import dispatcher.model.FuelFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Sovalov.AV on 14.11.2018.
 */
@Service
public class FuelFlowService {

    private static final String COAL_A_SH = "АШ стан";
    private static final String COAL_G_D = "ГиД стан";
    private static final String COAL_TOTAL = "Всего";
    private static final String GAS = "Газ";
    private static final String OIL = "Мазут";

    private Map<Integer, FuelFlow> mapCoalASH;
    private Map<Integer, FuelFlow> mapCoalGD;
    private Map<Integer, FuelFlow> mapCoalTotal;
    private Map<Integer, FuelFlow> mapGas;
    private Map<Integer, FuelFlow> mapOil;
    private Map<Integer, Integer> mapCodeByRow;
    private Map<Integer, FuelFlowDto> mapDto;

    private final FuelFlowDaoImpl fuelFlow;

    @Autowired
    public FuelFlowService(FuelFlowDaoImpl fuelFlow) {
        this.fuelFlow = fuelFlow;
    }

    public List<Map<Integer, FuelFlowDto>> getFuelFlowCoal(){

        initMaps();

        List<Map<Integer, FuelFlowDto>> resultList = new ArrayList<>();

        List<FuelFlow> listToday = fuelFlow.getListCoalFlow(0);
        List<FuelFlow> listYesterday = fuelFlow.getListCoalFlow(-1);
        List<FuelFlow> listAfterYesterday = fuelFlow.getListCoalFlow(-2);

        createDto(listAfterYesterday);
        resultList.add(mapDto);
        mapDto = new HashMap<>();
        createDto(listYesterday);
        resultList.add(mapDto);
        mapDto = new HashMap<>();
        createDto(listToday);
        resultList.add(mapDto);

        return resultList;
    }

    private void createDto(List<FuelFlow> list){
        createMaps(list);

        Date date = list.get(1).getDate();

        for (int i = 31; i > 3; i--) {
            FuelFlowDto dto = new FuelFlowDto();


            if (i == 6 || i == 5 || i == 4 || i == 7 || i == 15 || i == 17 || i == 20 || i == 24){
                dto = setCustomValueDto(i);
            }
            else {
                dto = setValueDto(i);
            }
            dto.setDate(date);
            mapDto.put(i, dto);
        }
    }

    private FuelFlowDto setValueDto(int row){

        FuelFlow fuelFlow = new FuelFlow();
        int stanCode = mapCodeByRow.get(row);

        if (row == 9 || row == 12 || row == 16 || row == 18 || row == 27 || row == 30){
            fuelFlow = mapCoalASH.get(stanCode);
        }
        else if (row == 10 || row == 13 || row == 14 || row == 19 || row == 21 || row == 22 ||
                row == 23 || row == 25 || row == 28 || row == 31){
            fuelFlow = mapCoalGD.get(stanCode);
        }
        else if (row == 29 || row == 26 || row == 11 || row == 8){
            fuelFlow = mapCoalTotal.get(stanCode);
        }

        String stationName = fuelFlow.getStationNameRus();

        int in = fuelFlow.getIn();
        int out = fuelFlow.getOut();
        int rest = fuelFlow.getRest();
        int[] values = new int[4];

        FuelFlowDto dto = new FuelFlowDto();
        dto.setStationName(stationName);
        dto.setRow(row);

        values[0] = in;
        values[1] = out;
        values[2] = rest;
        values[3] = in - out;
        dto.setValues(values);
        return dto;
    }

    private FuelFlowDto setCustomValueDto(int row) {
        FuelFlowDto customDto = new FuelFlowDto();
       int [] values = new int[4];
        switch (row){
            case 24:
                values[0] = mapDto.get(25).getValues()[0] + mapDto.get(26).getValues()[0] + mapDto.get(29).getValues()[0];
                values[1] = mapDto.get(25).getValues()[1] + mapDto.get(26).getValues()[1] + mapDto.get(29).getValues()[1];
                values[2] = mapDto.get(25).getValues()[2] + mapDto.get(26).getValues()[2] + mapDto.get(29).getValues()[2];
                customDto.setRow(24);
                customDto.setStationName("ГК Центр");
                break;
            case 20:
                values[0] = mapDto.get(21).getValues()[0] + mapDto.get(22).getValues()[0] + mapDto.get(23).getValues()[0];
                values[1] = mapDto.get(21).getValues()[1] + mapDto.get(22).getValues()[1] + mapDto.get(23).getValues()[1];
                values[2] = mapDto.get(21).getValues()[2] + mapDto.get(22).getValues()[2] + mapDto.get(23).getValues()[2];
                customDto.setRow(20);
                customDto.setStationName("ГК Захід");
                break;
            case 17:
                values[0] = mapDto.get(18).getValues()[0] + mapDto.get(19).getValues()[0];
                values[1] = mapDto.get(18).getValues()[1] + mapDto.get(19).getValues()[1];
                values[2] = mapDto.get(18).getValues()[2] + mapDto.get(19).getValues()[2];
                customDto.setRow(17);
                customDto.setStationName("ГК Схід");
                break;
            case 15:
                values[0] = mapDto.get(16).getValues()[0];
                values[1] = mapDto.get(16).getValues()[1];
                values[2] = mapDto.get(16).getValues()[2];
                customDto.setRow(15);
                customDto.setStationName("ГК Донбас");
                break;
            case 7:
                values[0] = mapDto.get(8).getValues()[0] + mapDto.get(11).getValues()[0] + mapDto.get(14).getValues()[0];
                values[1] = mapDto.get(8).getValues()[1] + mapDto.get(11).getValues()[1] + mapDto.get(14).getValues()[1];
                values[2] = mapDto.get(8).getValues()[2] + mapDto.get(11).getValues()[2] + mapDto.get(14).getValues()[2];
                customDto.setRow(7);
                customDto.setStationName("ГК Дніпро");
                break;
            case 6:
                values[0] = mapDto.get(10).getValues()[0] + mapDto.get(13).getValues()[0]
                        + mapDto.get(14).getValues()[0] + mapDto.get(19).getValues()[0]
                        + mapDto.get(20).getValues()[0] + mapDto.get(25).getValues()[0]
                        + mapDto.get(28).getValues()[0] + mapDto.get(31).getValues()[0];
                values[1] = mapDto.get(10).getValues()[1] + mapDto.get(13).getValues()[1]
                        + mapDto.get(14).getValues()[1] + mapDto.get(19).getValues()[1]
                        + mapDto.get(20).getValues()[1] + mapDto.get(25).getValues()[1]
                        + mapDto.get(28).getValues()[1] + mapDto.get(31).getValues()[1];
                values[2] = mapDto.get(10).getValues()[2] + mapDto.get(13).getValues()[2]
                        + mapDto.get(14).getValues()[2] + mapDto.get(19).getValues()[2]
                        + mapDto.get(20).getValues()[2] + mapDto.get(25).getValues()[2]
                        + mapDto.get(28).getValues()[2] + mapDto.get(31).getValues()[2];
                customDto.setRow(6);
                customDto.setStationName("- Г і Д");
                break;
            case 5:
                values[0] = mapDto.get(9).getValues()[0] + mapDto.get(12).getValues()[0]
                        + mapDto.get(16).getValues()[0] + mapDto.get(18).getValues()[0]
                        + mapDto.get(27).getValues()[0] + mapDto.get(30).getValues()[0];
                values[1] = mapDto.get(9).getValues()[1] + mapDto.get(12).getValues()[1]
                        + mapDto.get(16).getValues()[1] + mapDto.get(18).getValues()[1]
                        + mapDto.get(27).getValues()[1] + mapDto.get(30).getValues()[1];
                values[2] = mapDto.get(9).getValues()[2] + mapDto.get(12).getValues()[2]
                        + mapDto.get(16).getValues()[2] + mapDto.get(18).getValues()[2]
                        + mapDto.get(27).getValues()[2] + mapDto.get(30).getValues()[2];
                customDto.setRow(5);
                customDto.setStationName("з них: - АШ");
                break;
            case 4:
                values[0] = mapDto.get(5).getValues()[0] + mapDto.get(6).getValues()[0];
                values[1] = mapDto.get(5).getValues()[1] + mapDto.get(6).getValues()[1];
                values[2] = mapDto.get(5).getValues()[2] + mapDto.get(6).getValues()[2];
                customDto.setRow(4);
                customDto.setStationName("Сума ТЕС ГК");
                break;
        }
        values[3] = values[0] - values[1];
        customDto.setValues(values);
        return customDto;
    }

    private void createMaps(List<FuelFlow> list){
        for (FuelFlow fuelFlow: list) {
            String fuelType = fuelFlow.getNameFuel();
            switch (fuelType){
                case COAL_A_SH:
                    mapCoalASH.put(fuelFlow.getStationCode(), fuelFlow);
                    break;
                case COAL_G_D:
                    mapCoalGD.put(fuelFlow.getStationCode(), fuelFlow);
                    break;
                case COAL_TOTAL:
                    mapCoalTotal.put(fuelFlow.getStationCode(), fuelFlow);
                    break;
                case GAS:
                    mapGas.put(fuelFlow.getStationCode(), fuelFlow);
                    break;
                case OIL:
                    mapOil.put(fuelFlow.getStationCode(), fuelFlow);
                    break;
            }
        }
    }

    private void initMaps(){
        mapCoalASH = new HashMap<>();
        mapCoalGD = new HashMap<>();
        mapCoalTotal = new HashMap<>();
        mapGas = new HashMap<>();
        mapOil = new HashMap<>();
        mapCodeByRow = new HashMap<>();
        mapDto = new HashMap<>();
        //Криворожская
        mapCodeByRow.put(8, 513101);
        mapCodeByRow.put(9, 513101);
        mapCodeByRow.put(10, 513101);
        //Приднепровская
        mapCodeByRow.put(11, 513102);
        mapCodeByRow.put(12, 513102);
        mapCodeByRow.put(13, 513102);
        //Запорожская
        mapCodeByRow.put(14, 513103);
        //Славянская
        mapCodeByRow.put(16, 513003);
        //Луганская
        mapCodeByRow.put(18, 513001);
        //Кураховская
        mapCodeByRow.put(19, 513006);
        //Ладижинская
        mapCodeByRow.put(21, 513701);
        //Бурштынская
        mapCodeByRow.put(22, 513801);
        //Добротворская
        mapCodeByRow.put(23, 513802);
        //Углегорская
        mapCodeByRow.put(25, 513005);
        //Трипольская
        mapCodeByRow.put(26, 513501);
        mapCodeByRow.put(27, 513501);
        mapCodeByRow.put(28, 513501);
        //Змиевская
        mapCodeByRow.put(29, 513301);
        mapCodeByRow.put(30, 513301);
        mapCodeByRow.put(31, 513301);
    }

    public List<FuelFlow> getFuelFlowOil(){
        return fuelFlow.getListFuelFlow(3);
    }

    public List<FuelFlow> getFuelFlowGas(){
        return fuelFlow.getListFuelFlow(2);
    }

    public List<FuelFlow> getFuelFlowCoal2(){

        List<FuelFlow> resultList = new ArrayList<>();

        List<FuelFlow> coalASH = fuelFlow.getListFuelFlow(5);
        resultList.addAll(coalASH);

        List<FuelFlow> coalGD = fuelFlow.getListFuelFlow(6);
        resultList.addAll(coalGD);

        List<FuelFlow> coalTotal = fuelFlow.getListFuelFlow(4);
        resultList.addAll(coalTotal);

        return resultList;
    }
}
