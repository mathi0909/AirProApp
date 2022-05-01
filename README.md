# AirProApp

## _AirPro - Leading Airline Booking Application_

AirPro is a fictitious airline booking organization. This Spring boot project is written in Java to fetch the flights for the current date. 


## _High-level Architecture for Production:_

![This is an image](../main/src/main/resources/asset/HighLevelArchitecture.png)

All the request to the service will come via API Gateway. We can make use of _Apigee or Spring API Gateway_. We will take care of the _authentication and authorization in API Gateway_. This ensures that only valid service request will hit the AirproApp Micro Service. 

All the properties files like 'application.properties' will be provided by Cloud Config. All the properties will be bootstrapped when the micro service is started. 

We can make use of cloud bus to update the property files on the fly. We can make use of Rabbit MQ or any other queuing mechanism to update the property files.  


## _Instructions to Run the Project Locally_

This project make use of Lombok. In order to run the project locally please install the lombok-1.18.22.jar from https://projectlombok.org/download.  Please install lombok by double clicking it or by running the jar manually from command prompt. 

![This is an image](../main/src/main/resources/asset/AddingLombok.png)


The project make use of H2 in-memory DB and the application runs on port 8084. The application has implemented open API documentation. You can find  swagger UI in http://localhost:8084/swagger-ui.html

![This is an image](../main/src/main/resources/asset/SwaggerUI.png)


Alternatively we can import the below collection in Postman 
[AirProAPI Collection](../main/src/main/resources/asset/AirProApp.postman_collection.json)
