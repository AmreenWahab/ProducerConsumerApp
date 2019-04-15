package com.producer.producer.service;

import com.producer.producer.model.RawData;
import java.util.List;

public interface RawDataService {
    void insertRecordToRawData(RawData rawData);
    void insertBulkRecordsToRawData(List<RawData> rawDatas);
    void updateRecordInRawData(RawData rawData);
    List<RawData> getRecordsFromRawData(int noofRecords);
    RawData getRecordById(int id);
    List<RawData> getUnprocessedRecordsFromRawData(int noOfRecords);
    List<RawData> generateRandomRecords(int noOfRecords);


  //  void processAndUpdateRecord(RawData rawData) throws Exception;
}
