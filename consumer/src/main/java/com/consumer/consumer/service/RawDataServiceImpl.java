package com.consumer.consumer.service;

import com.consumer.consumer.dao.RawDataDao;
import com.consumer.consumer.model.RawData;
import com.consumer.consumer.model.Target;
import com.consumer.consumer.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RawDataServiceImpl implements RawDataService {

    private static int counter = 0;

    @Autowired
    RawDataDao rawDataDao;

    @Resource(name="dbProcessor")
    DataProcessor dbProcessor;

    @Resource(name="fileProcessor")
    DataProcessor fileProcessor;

//    @Override
//    public void insertRecordToRawData(RawData rawData) {
//        rawDataDao.insertRecordToRawData(rawData);
//    }
//
//    @Override
//    public void insertBulkRecordsToRawData(List<RawData> rawDatas) {
//        rawDataDao.insertBulkRecordsToRawData(rawDatas);
//    }
//
//    public List<RawData> generateRandomRecords(int noOfRecords) {
//        System.out.println("Start generating random records");
//        if(noOfRecords <= 0){
//            return null;
//        }
//
//        List<RawData> rawDataList = new ArrayList<RawData>();
//
//        for(int i=0; i< noOfRecords;i ++){
//            int random = Utility.getRandom();
//            int targetRandom = Utility.getRandomForTarget();
//            RawData rawData = new RawData(Utility.getRandom(),
//                    Target.values()[targetRandom -1].toString(),
//                    "Randomtext_"+new Integer(random).toString());
//            rawDataList.add(rawData);
//        }
//
//        return  rawDataList;
//    }

    @Override
    public void processAndUpdateRecord(RawData rawData) throws Exception {
        if(rawData == null)
            return;

        try {
            String target = rawData.getTarget();
            String trimmedUpperTarget = target.trim().toUpperCase();

            switch(trimmedUpperTarget) {
                case "DB":
                    dbProcessor.process(rawData);
                    //counter++;
                    break;
                case "FILE":
                    fileProcessor.process(rawData);
                    break;

            }
            rawDataDao.updateRecordInRawData(rawData);

            } catch (Exception e) {
                throw e;
        }
    }

    @Override
    public void updateRecordInRawData(RawData rawData) throws SQLException {

        rawDataDao.updateRecordInRawData(rawData);
    }

    @Override
    public List<RawData> getRecordsFromRawData(int noOfRecords) {
        return rawDataDao.getRecordsFromRawData(noOfRecords);
    }

    @Override
    public RawData getRecordById (int id) {
        return rawDataDao.getRecordById(id);
    }

    @Override
    public List<RawData> getUnprocessedRecordsFromRawData(int noOfRecords) {
        return rawDataDao.getUnprocessedRecordsFromRawData(noOfRecords);
    }

}

