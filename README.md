# ProducerConsumerApp

To build a simple standalone Java application that works as a consumer of messages in a database. The consumer is responsible for sending the message to difference targets like file, db etc. The consumer must ensure integrity of data and guarantee exactly-once processing of messages. Another Java application will act as a producer to inject data into database.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

1. Make sure your system has Java installed preferably version 1.8
2. Latest version of MySQL database

### Installing
git clone this repository in your local machine

## Running the application

The producer and consumer folder are two different application. 

### Running the producer application

The producer application must be run first to populate the RawData DB. 

Add the database connection details in the **ProducerConsumer/producer/src/main/resources/application.properties** file. 
Update the connection URL, username, password and database in the above file.
The ddl sql file is available in the **ProducerConsumer/producer/src/main/resources** folder.

Run the java file present in **ProducerConsumer/producer/src/main/java/com/producer/producer/ProducerApplication.java** in the IDE of your choice.
You can either stop the producer application before starting consumer application if enough data has been injected into the database or run it simultaneously along with consumer application.

### Running the consumer application

Add the database connection details in the **ProducerConsumer/consumer/src/main/resources/application.properties** file. 
Update the connection URL, username, password and database in the above file.
The ddl sql file for ProcessedData whose target is "DB" is present in **ProducerConsumer/consumer/src/main/resources** folder

Run the java file present in **ProducerConsumer/consumer/src/main/java/com/producer/producer/ConsumerApplication.java** in the IDE of your choice.
The folder of the output file for messages having target as "File" is created using my local folder location. Make sure to edit the folder path in the location **ProducerConsumer/consumer/src/main/java/com/consumer/consumer/service/FileProcessor.java** within your local machine



