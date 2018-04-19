package dispatcher;

import dispatcher.model.dao.LastInfoDao;
import dispatcher.model.dao.StationDao;
import dispatcher.model.dao.UnitDao;
import dispatcher.model.dao.UnitStatusDao;
import dispatcher.model.dto.BlockDto;
import dispatcher.model.dto.StationDto;
import dispatcher.model.dto.UnitDto;
import dispatcher.model.entity.LastInfo;
import dispatcher.model.entity.Station;
import dispatcher.model.entity.Unit;
import dispatcher.model.entity.UnitStatus;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sovalov.AV on 13.04.2018.
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                SpringJDBCConfiguration.class);

        StationDao stationDao = applicationContext.getBean(StationDao.class);
        LastInfoDao lastInfoDao = applicationContext.getBean(LastInfoDao.class);
        UnitDao unitDao = applicationContext.getBean(UnitDao.class);
        UnitStatusDao unitStatusDao = applicationContext.getBean(UnitStatusDao.class);

        String fullNameStation = stationDao.getStationFullName(2);
        //System.out.println("Station with id2: " + fullNameStation);


        List<Unit> unitList = unitDao.getAllUnitsInfo();
        List<UnitStatus> statusList = unitStatusDao.getAllUnitStatus();
        List<LastInfo> infoList = lastInfoDao.getLastInfo();

        List<UnitDto> dtoList = new ArrayList<UnitDto>();
        for (Unit unit: unitList){
            UnitDto dto = new UnitDto();
            dto.setId(unit.getUnitId());
            dto.setName(unit.getUnitName());
            dto.setStationCode(unit.getStationCode());
            dto.setWorkUnit(unit.getWorkUnit());
            dto.setTi((int)unit.getTi());
            dto.setOch(unit.getOch());

            for (UnitStatus status: statusList) {
                if (dto.getId() == status.getUnitId()) {
                    dto.setStatusShortName(status.getActionShortName());
                    dto.setStatusFullName(status.getActionFullName());
                    dto.setRepairStartTime(status.getActionBegin());
                    dto.setRepairEndTime(status.getGetActionEnd());
                    dto.setEditTime(status.getActionEdit());
                    dto.setComment(status.getComment());
                    dto.setOperator(status.getOperator());
                    dto.setColor(status.getColor());
                }
            }
            dtoList.add(dto);
        }

        for (UnitDto dto: dtoList){
            for (LastInfo info: infoList){
                if (dto.getTi() == info.getIndex()) {
                    dto.setPower(info.getValue());
                }
            }
        }

        //System.out.println(infoList);
        //System.out.println(dtoList);
        List<BlockDto> blockList = new ArrayList<BlockDto>();

        for (int i = 0; i < dtoList.size(); i++) {
            if (dtoList.get(i).getWorkUnit() == 0){
                BlockDto block = new BlockDto();
                block.setStationCode(dtoList.get(i).getStationCode());
                block.setNumber(Integer.parseInt(dtoList.get(i).getName()));
                block.setPower(dtoList.get(i).getPower());
                block.setUnit1(dtoList.get(i + 1));
                block.setUnit2(dtoList.get(i + 2));
                dtoList.get(i + 1).setBlockNumber(block.getNumber());
                dtoList.get(i + 2).setBlockNumber(block.getNumber());
                blockList.add(block);
                continue;
            }
            if (dtoList.get(i).getWorkUnit() == 0.5){
                continue;
            }
            BlockDto block = new BlockDto();
            block.setStationCode(dtoList.get(i).getStationCode());
            block.setNumber(Integer.parseInt(dtoList.get(i).getName()));
            dtoList.get(i).setBlockNumber(block.getNumber());
            block.setPower(dtoList.get(i).getPower());
            block.setUnit1(dtoList.get(i));
            block.setUnit2(null);
            blockList.add(block);
        }

        //System.out.println(blockList);
        List<Station> stationEntityList = stationDao.getAllStation();
        List<StationDto> stationList = stationDao.getAllStationDto();

        for (StationDto station: stationList) {
            for (Station entity: stationEntityList) {
                if (station.getId() == entity.getId()){
                    station.setName(entity.getFullNameRus());
                    station.setOch(entity.getOch());
                }
            }

            station.setBlockDtoList(new ArrayList<BlockDto>());
            int power = 0;
            String och1;
            String och2;
            float ochArray [] = new float[]{0, 0};

            for (BlockDto block: blockList) {
                if (station.getId() == block.getStationCode()) {
                    station.getBlockDtoList().add(block);
                    power += block.getPower();

                    if (station.getOch() == 1){
                        //> 10 ,так как показатели телеметрии могут быть 1 или 2 при выключенном блоке
                        if (block.getPower() > 10){
                            if (block.getUnit2() != null){
                                if (block.getUnit1().getStatusShortName() == null){
                                    ochArray[0] += 0.5;
                                }
                                if (block.getUnit2().getStatusShortName() == null){
                                    ochArray[0] += 0.5;
                                }
                            }
                            else {
                                ochArray[0] += 1;
                            }
                        }

                        if (ochArray[0]%1 == 0){
                            och1 = String.valueOf((int)ochArray[0]);
                        }
                        else {
                            och1 = String.valueOf(ochArray[0]);
                        }
                        station.setUnitValue(och1);
                    }
                    //если станция из двух очередей блоков
                    else {
                        if (block.getPower() >10) {
                            if (block.getUnit2() != null){

                                if (block.getUnit1().getOch() == 1 && block.getUnit1().getStatusShortName() == null){
                                    ochArray[0] += 0.5;
                                }
                                if (block.getUnit1().getOch() == 2 && block.getUnit1().getStatusShortName() == null){
                                    ochArray[1] += 0.5;
                                }
                                if (block.getUnit2().getOch() == 1 && block.getUnit2().getStatusShortName() == null){
                                    ochArray[0] += 0.5;
                                }
                                if (block.getUnit2().getOch() == 2 && block.getUnit2().getStatusShortName() == null){
                                    ochArray[1] += 0.5;
                                }
                            }
                            else {
                                if (block.getUnit1().getOch() == 1){
                                    ochArray[0] += 1;
                                }
                                if (block.getUnit1().getOch() == 2){
                                    ochArray[1] += 1;
                                }
                            }
                        }

                        if (ochArray[0]%1 == 0){
                            och1 = String.valueOf((int)ochArray[0]);
                        }
                        else{
                            och1 = String.valueOf(ochArray[0]);
                        }

                        if (ochArray[1]%1 == 0){
                           och2 = String.valueOf((int)ochArray[1]);
                        }
                        else{
                           och2 = String.valueOf(ochArray[1]);
                        }
                        station.setUnitValue(och1 + "+" + och2);
                    }
                }
                station.setPower(power);
            }
        }
        System.out.println(stationList);
        applicationContext.close();
    }
}
