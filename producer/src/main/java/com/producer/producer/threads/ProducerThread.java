package com.producer.producer.threads;

import com.producer.producer.model.RawData;
import com.producer.producer.model.Target;
import com.producer.producer.util.Utility;
import com.producer.producer.service.RawDataService;
import com.producer.producer.service.RawDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
@Scope("prototype")
public class ProducerThread extends Thread{

    private RawDataService rawDataService;

    public ProducerThread(RawDataService rawDataService) {
        this.rawDataService = rawDataService;
    }

    @Override
    public void run(){
        try{
            // the producer thread pushes random messages to the database
            pushRandomDataToDB();
        } catch(Exception e){
            System.out.println(" This instance of execution with id: "+ Thread.currentThread().getId() + "failed with Exception "+ e);
        }
    }

    //generates random data and calls bulkinsert method for db insert
    private void pushRandomDataToDB(){
        List<RawData> recordsToInsertInDB = this.rawDataService.generateRandomRecords(Utility.getRandomForMaxRecords());
        System.out.println("No. of Random records generated to insert into DB "+ recordsToInsertInDB.size());
        this.rawDataService.insertBulkRecordsToRawData(recordsToInsertInDB);
    }

}
