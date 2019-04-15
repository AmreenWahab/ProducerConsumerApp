package com.consumer.consumer.threads;


import com.consumer.consumer.model.RawData;
import com.consumer.consumer.service.RawDataService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;

@ComponentScan
@Scope("prototype")
public class QueuePopulator extends Thread {
    private RawDataService rawDataService;
    private HashMap<Integer, String> globalHashMap;
    private final BlockingQueue<RawData> blockingQueue;

    public QueuePopulator(RawDataService rawDataService, HashMap<Integer,String> globalHashMap, BlockingQueue<RawData> blockingQueue) {
        this.rawDataService = rawDataService;
        this.blockingQueue=blockingQueue;
        this.globalHashMap=globalHashMap;

    }

    @Override
    public void run(){
        try{
          //  System.out.println("The Queue Populator instance of execution is by Thread:" +Thread.currentThread().getId());

            // 50 unprocessed records are fetched from RawData table. This value can be changed depending on load
            List<RawData> rawDataList = fetchUnprocessedRecordsFromRawData(50);
            System.out.println("Length of fetched rcrd "+rawDataList.size());
            populateGlobalQueue(rawDataList);

        } catch (Exception e){
            System.out.println("This instance of execeution with id "+Thread.currentThread().getId() + " failed with Exception: "+e);
        }
    }

    private void populateGlobalQueue(List<RawData> rawDataList){
        if(rawDataList.isEmpty())
            return;
        try
        {
            //if record not present in globalHashMap -> process for first time or process again
            for(RawData rawData : rawDataList){
                if(!globalHashMap.containsKey((rawData.getId()))){
                    this.globalHashMap.put(rawData.getId(),"Processed");
                    this.blockingQueue.put(rawData);
                }
            }
        } catch (InterruptedException e){
            System.out.println(e);
        }
    }

    private List<RawData> fetchUnprocessedRecordsFromRawData(int i){
      //  System.out.println("Inside fetchUnprocessedRecordsFromRawData");
        List<RawData> rawDataList = rawDataService.getUnprocessedRecordsFromRawData(i);
        return rawDataList;
    }
}
