# smartnation
lee hsien loong will be proud

# Installation
## Install Maven
https://maven.apache.org/download.cgi

## Create Postgres Database
```createdb smartnation```

## Install Lombok
https://projectlombok.org/download

## Install packages and migrate database
```mvn install```
```mvn flyway:migrate```
```mvn package && java -jar target/gs-rest-service-0.1.0.jar``` 