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
```mvn package && java -jar target/smartnation-0.1.0.jar``` 

## Endpoints
```/classes```

```/classes/{klass}```

```/classes/{klass}/this_week```

```/area/{type}```, where type is **food** or **meeting**

```/stats/{area_id}?[from,to,part]```, where from and to are timestamps, and part is **hour**, **day** or **minute** 