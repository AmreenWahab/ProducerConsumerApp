package com.consumer.consumer.service;

import com.consumer.consumer.dao.ProcessedDataDao;
import com.consumer.consumer.model.ProcessedData;
import com.consumer.consumer.model.RawData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dbProcessor")
public class DBProcessor implements DataProcessor {

    @Autowired
    ProcessedDataDao processedDataDao;

    @Override
    public void process(RawData rawData) throws Exception {
        ProcessedData processedData = new ProcessedData();
        processedData.setId(rawData.getId());
        processedData.setData(rawData.getData());
        processedDataDao.insertRecordToProcessedData(processedData);
    }
}
