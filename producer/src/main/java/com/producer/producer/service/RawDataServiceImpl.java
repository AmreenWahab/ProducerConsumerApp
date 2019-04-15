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

    @Override
    public void insertBulkRecordsToRawData(List<RawData> rawDatas) {
        rawDataDao.insertBulkRecordsToRawData(rawDatas);
    }

    public List<RawData> generateRandomRecords(int noOfRecords) {
       // System.out.println("Start generating random records");
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



}

