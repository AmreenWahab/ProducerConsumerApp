package com.consumer.consumer.service;

import com.consumer.consumer.model.RawData;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;

@Service("fileProcessor")
public class FileProcessor implements DataProcessor{
    @Override
    public void process(RawData rawData) throws  Exception {
        // change the path to required path where the output files should be generated
        String filename = "/Users/amreenwahab/Desktop/ProducerConsumer/consumer/src/main/resources/outputfiles/" + rawData.getId() + ".json";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename,true));
        writer.write(rawData.getData());
        writer.close();
    }

}
