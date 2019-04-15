package com.producer.producer.service;

import com.producer.producer.dao.RawDataDao;
import com.producer.producer.model.RawData;
import com.producer.producer.model.Target;
import com.producer.producer.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RawDataServiceImpl implements RawDataService {

    @Autowired
    RawDataDao rawDataDao;

//    @Resource(name="dbProcessor")
//    DataProcessor dbProcessor;
//
//    @Resource(name="fileProcessor")
//    DataProcessor fileProcessor;

    @Override
    public void insertRecordToRawData(RawData rawData) {
        rawDataDao.insertRecordToRawData(rawData);
    }

    @Override
    public void insertBulkRecordsToRawData(List<RawData> rawDatas) {
        rawDataDao.insertBulkRecordsToRawData(rawDatas);
    }

    public List<RawData> generateRandomRecords(int noOfRecords) {
        System.out.println("Start generating random records");
        if(noOfRecords <= 0){
            return null;
        }

        List<RawData> rawDataList = new ArrayList<RawData>();

        for(int i=0; i< noOfRecords;i ++){
            int random = Utility.getRandom();
            int targetRandom = Utility.getRandomForTarget();
            RawData rawData = new RawData(Utility.getRandom(),
                    Target.values()[targetRandom -1].toString(),
                    "Randomtext_"+new Integer(random).toString());
            rawDataList.add(rawData);
        }

        return  rawDataList;
    }

//    @Override
//    public void processAndUpdateRecord(RawData rawData) throws Exception {
//        if(rawData == null)
//            return;
//
//        try {
//            String target = rawData.getTarget();
//            String trimmedUpperTarget = target.trim().toUpperCase();
//
//            switch(trimmedUpperTarget) {
//                case "DB":
//                    dbProcessor.process(rawData);
//                    break;
//                case "FILE":
//                    fileProcessor.process(rawData);
//                    break;
//
//            }
//            rawDataDao.updateRecordInRawData(rawData);
//
//            } catch (Exception e) {
//                throw e;
//        }
//    }

    @Override
    public void updateRecordInRawData(RawData rawData){
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

