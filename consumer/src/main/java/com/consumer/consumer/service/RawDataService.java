package com.consumer.consumer.service;

import com.consumer.consumer.model.RawData;

import java.sql.SQLException;
import java.util.List;

public interface RawDataService {
//    void insertRecordToRawData(RawData rawData);
//    void insertBulkRecordsToRawData(List<RawData> rawDatas);
    void updateRecordInRawData(RawData rawData) throws SQLException;
    List<RawData> getRecordsFromRawData(int noofRecords);
    RawData getRecordById(int id);
    List<RawData> getUnprocessedRecordsFromRawData(int noOfRecords);
//    List<RawData> generateRandomRecords(int noOfRecords);


    void processAndUpdateRecord(RawData rawData) throws Exception;
}
