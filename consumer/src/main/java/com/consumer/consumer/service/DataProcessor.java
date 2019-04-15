package com.consumer.consumer.service;

import com.consumer.consumer.model.RawData;

public interface DataProcessor {
    void process(RawData rawData) throws Exception;
}
