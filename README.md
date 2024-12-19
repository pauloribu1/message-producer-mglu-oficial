Message Producer


A Communication Platform Project built in Java 21 SE, enabling the scheduling and delivery of messages through four distinct channels (SMS, PUSH, WHATSAPP, and EMAIL) using Spring Boot 6.1.5, RabbitMQ, MySQL, JUnit, and Docker.

BUILD


To build the project, open the source code in a text editor and run the docker-compose.yaml file located in the root folder. This file will create database and RabbitMQ instances within Docker containers. After this process, verify the database connection is functional and access the RabbitMQ Management to create the Exchanges and Queues (message-exchange, message-queue, message-route-key) needed for producer-consumer communication.

To access RabbitMQ: http://localhost:15672/#/. It is necessary to create the Exchanges and Queues in RabbitMQ according to the native code. RabbitMQ requires configuration by an administrator to function. Credentials can be found in application.properties.

You can then compile and run the Java project.

PROJECT


This project implements a communication platform that uses RabbitMQ as a messaging service, where a microservice is responsible for both the consumer and the producer and connects to a relational database. It is essential to understand the architectural decisions made. (NOTE: Ideally, for larger developments, it is highly recommended to decouple both from the same Docker instance and design a database model that allows each to use exclusive databases. This improves maintenance during downtimes and performance management). This API was created following the Three-Layer Architecture pattern, adhering to the Controller-Service-Repository hierarchy.

Additionally, configuration files for global exceptions and a database initialization file were created to generate the required data for the Enum tables. Regarding Enum, the idea of creating specific tables for enums comes from the possibility of increased flow, scope changes, or system complexity growth. This could mean more states during the process or additional communication channels. Therefore, normalizing the database is a forward step toward indexing specific points of our tables, such as these.

The Producer and Consumer have specific functions, but essentially, they are about sending a message based on business rules and consuming it according to business rules as well. In this case, it is closely related to the STATUS of the scheduled messages, as they depend on this to communicate through the proper channel.
