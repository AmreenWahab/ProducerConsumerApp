package com.consumer.consumer;


import com.consumer.consumer.model.RawData;
import com.consumer.consumer.service.RawDataService;
import com.consumer.consumer.threads.ConsumerThread;
import com.consumer.consumer.threads.QueuePopulator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


import java.util.HashMap;
import java.util.concurrent.*;

@SpringBootApplication
@ComponentScan(basePackages = "com.consumer.consumer")
public class ConsumerApplication {

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context = SpringApplication.run(ConsumerApplication.class, args);
        RawDataService rawDataService = context.getBean(RawDataService.class);

        HashMap<Integer,String > globalHashMapOfProcessingRecords = new HashMap<Integer, String>();
        final BlockingQueue<RawData> blockingQueue = new ArrayBlockingQueue<RawData>(2000);

        Thread queuePopulator = new QueuePopulator(rawDataService,globalHashMapOfProcessingRecords,blockingQueue);

        try {
            ScheduledExecutorService scheduledQueuePopulator = Executors.newSingleThreadScheduledExecutor();
            scheduledQueuePopulator.scheduleAtFixedRate(queuePopulator, 0, 3, TimeUnit.SECONDS);
        } catch(Exception e) {
            System.out.println("scheduledQueuePopulator Instance Failed:" + Thread.currentThread().getId());
        }

        try {
            ExecutorService pooledConsumers = Executors.newFixedThreadPool(50);

            while (true) {
                pooledConsumers.submit(createNewConsumerThread(rawDataService, globalHashMapOfProcessingRecords, blockingQueue));
                Thread.sleep(1);
            }
        } catch(Exception e) {
            System.out.println("Exception in ConsumerThread Instance :" + Thread.currentThread().getId());
        }

	}

	private static Thread createNewConsumerThread(RawDataService rawDataService, HashMap<Integer,String> globalHashMap,
                                                  BlockingQueue<RawData> blockingQueue){
	    return new ConsumerThread(rawDataService,globalHashMap,blockingQueue);
    }



}
