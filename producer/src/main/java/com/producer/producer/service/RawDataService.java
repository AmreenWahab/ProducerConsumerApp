package com.producer.producer.service;

import com.producer.producer.model.RawData;
import java.util.List;

public interface RawDataService {
    void insertBulkRecordsToRawData(List<RawData> rawDatas);
    List<RawData> generateRandomRecords(int noOfRecords);
}
