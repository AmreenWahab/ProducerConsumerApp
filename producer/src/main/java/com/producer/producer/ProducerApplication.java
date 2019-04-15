package com.producer.producer;

import com.producer.producer.service.RawDataService;
import com.producer.producer.threads.ProducerThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@ComponentScan(basePackages = "com.producer.producer")
public class ProducerApplication {

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context = SpringApplication.run(ProducerApplication.class, args);
        RawDataService rawDataService = context.getBean(RawDataService.class);

        Thread producerThread = new ProducerThread(rawDataService);

        //create a scheduler thread
        ScheduledExecutorService scheduledProducer = Executors.newSingleThreadScheduledExecutor();
        System.out.println("START: "+System.currentTimeMillis());
        // a fixed scheduler pushes performs production of data every 60 seconds
        scheduledProducer.scheduleAtFixedRate(producerThread,0,60, TimeUnit.SECONDS);

	}

}
