package com.producer.producer.dao;

import com.producer.producer.model.RawData;
import java.util.List;

public interface RawDataDao {
    void insertRecordToRawData(RawData rawData);
    void insertBulkRecordsToRawData(List<RawData> rawDatas);
    void updateRecordInRawData(RawData rawData);
    List<RawData> getRecordsFromRawData(int noofRecords);
    RawData getRecordById(int id);
    List<RawData> getUnprocessedRecordsFromRawData(int noOfRecords);
}
