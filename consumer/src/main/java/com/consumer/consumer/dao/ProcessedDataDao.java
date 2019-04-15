package com.consumer.consumer.dao;

import com.consumer.consumer.model.ProcessedData;

public interface ProcessedDataDao {
    void insertRecordToProcessedData(ProcessedData processedData);
}
