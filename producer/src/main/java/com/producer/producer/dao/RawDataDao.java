package com.producer.producer.dao;

import com.producer.producer.model.RawData;
import java.util.List;

public interface RawDataDao {
    void insertBulkRecordsToRawData(List<RawData> rawDatas);
}
