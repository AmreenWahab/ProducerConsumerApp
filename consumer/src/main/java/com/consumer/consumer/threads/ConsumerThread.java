package com.consumer.consumer.threads;

import com.consumer.consumer.model.RawData;
import com.consumer.consumer.service.RawDataService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

@ComponentScan
@Scope("prototype")
public class ConsumerThread extends Thread{

    private RawDataService rawDataService;
    private HashMap<Integer, String> globalHashMap;
    private final BlockingQueue<RawData> blockingQueue;

    public ConsumerThread(RawDataService rawDataService, HashMap<Integer,String> globalHashMap, BlockingQueue<RawData> blockingQueue) {
        this.rawDataService = rawDataService;
        this.blockingQueue=blockingQueue;
        this.globalHashMap=globalHashMap;

    }

    @Override
    public void run(){
        try{
            //System.out.println("This Consumer instance of execution is by Thread:" Thread.currentThread().getId());
            processEachRecordInGlobalQueue();

        } catch (Exception e){
            System.out.println("This instance of execution with id "+Thread.currentThread().getId() + " failed with Exception: "+e);
        }
    }

    private void processEachRecordInGlobalQueue(){
        try{
            RawData rawData = null;
            if(!blockingQueue.isEmpty()){
                rawData = this.blockingQueue.take();

                if (rawData != null ) {
                    processAndUpdateRecord(rawData);
                }
            }

//            else{
//                System.out.println("THREADS WAITING: " + System.currentTimeMillis());
//            }
        } catch(InterruptedException e){
                e.printStackTrace();
        }
    }

    private void processAndUpdateRecord(RawData rawData) {
        try{
            this.rawDataService.processAndUpdateRecord(rawData);
        } catch(Exception e) {
            System.out.println(e);
            System.out.println("Removing " + rawData.getId() + " from HashMap by Thread:"+ Thread.currentThread().getId() +", so that it will get processed again");
            this.globalHashMap.remove((rawData.getId()));
            System.exit(0);
        }
    }
}
