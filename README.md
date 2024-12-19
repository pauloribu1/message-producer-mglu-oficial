#Message Producer
A Communication Platform Project built in Java 21 SE, enabling the scheduling and delivery of messages through four distinct channels (SMS, Push Notifications, WhatsApp, and Email) using Spring Boot 6.1.5, RabbitMQ, MySQL, JUnit, and Docker.

BUILD
To build the project:

Open the source code in a text editor.
Run the docker-compose.yaml file located in the root directory.
This file will create Docker containers for the database and RabbitMQ instances.
Verify the database connection is functional and access the RabbitMQ Management Console to configure the Exchanges and Queues needed for producer-consumer communication.
RabbitMQ Setup
Access RabbitMQ at: http://localhost:15672/#/
Create the following configurations in RabbitMQ:
Exchange: message-exchange
Queue: message-queue
Routing Key: message-route-key
RabbitMQ requires administrator configuration to function correctly.
Credentials can be found in the application.properties file.

After this, compile and run the Java project.

PROJECT
This project implements a communication platform using RabbitMQ as the messaging service. It includes a microservice responsible for both the producer and consumer functionalities, as well as a connection to a relational database.

Note
For larger-scale development, it is recommended to decouple the producer and consumer from the same Docker instance. Additionally, designing separate databases for each service improves maintainability during outages and enhances performance management.

The API was built following the Three-Layer Architecture pattern, adhering to the Controller-Service-Repository hierarchy.

Additional Features
Configuration file for global exceptions.
Database initialization file to ensure required enum data is preloaded.
ENUMS AND DATABASE DESIGN
A separate table was created for enums to ensure scalability. This approach supports the addition of new states and communication channels as needed, while improving indexing and query performance for specific database fields.

PRODUCER AND CONSUMER
The Producer and Consumer have specific functions:

The Producer sends messages based on business rules.
The Consumer processes these messages according to the same rules.
The status of scheduled messages is crucial in determining how and when they are delivered through the appropriate channels.
