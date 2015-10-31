# Live coding demo for Feign (15min)
## Requirements
JDK 8 and maven 3.x

## Build

```
$ mvn clean install
```

## Server

```
java -jar fegin/target/server-1.0-SNAPSHOT.jar
```

### A cat

```json
{
    "id": "422f9dfb-a900-4c2e-8361-d99c37edc826",
    "name": "Isis",
    "race": "Sphynx"
}
```

### List all cats

```
GET http://localhost:8000/cat
```

### Get cat by ID

```
GET http://localhost:8000/cat/:id
```

### Create cat

```
POST http://localhost:8000/cat/
{
    "name": "Isis",
    "race": "Sphynx"
}
```

### Update a cat

```
PUT http://localhost:8000/cat/:id
{
    "name": "Isis",
    "race": "Sphynx"
}
```

### Delete a cat

```
DELETE http://localhost:8000/cat/:id
```
