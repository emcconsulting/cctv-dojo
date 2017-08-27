# CCTV DOJO
# Microservices components

Steps to run the application:
1. Build all applications individually

2. Run config server with 'native' profile \
```
java -jar -Dspring.profiles.active=native config-server.jar
```
Note: Only config server is using native profile. Rest all services run with default profile.


3. Run Eureka server

4. Run zipkin server

5. Run 'user-service', 'review-service', 'composite-service' and 'zuul-service'. \
Note: 'user-service' and 'review-service' use in-memory H2 database. 

6. To populate data in user-service: \
```
curl -H "Content-Type: application/json" -X POST -d '{  "firstName" : "Abhishek", "lastName" : "Jawali" }' http://localhost:9090/user
``` 

7. To populate data in review-service, first check that user-id returned when creating user in user-service. Use the ID in 'userId' field below: \
```
curl -H "Content-Type: application/json" -X POST -d '{  "userId" : 1, "reviewComment" : "Review 1" }' http://localhost:9091/reviews
```

8. To retrieve the data from API gateway:
```
http://localhost:8765/api/composite/composite/reviews/1
```





