package com.consumer.consumer.dao;

import com.consumer.consumer.model.RawData;

import java.sql.SQLException;
import java.util.List;

public interface RawDataDao {
//    void insertRecordToRawData(RawData rawData);
//    void insertBulkRecordsToRawData(List<RawData> rawDatas);
    void updateRecordInRawData(RawData rawData) throws SQLException;
    List<RawData> getRecordsFromRawData(int noofRecords);
    RawData getRecordById(int id);
    List<RawData> getUnprocessedRecordsFromRawData(int noOfRecords);
}
