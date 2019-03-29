# Bike Rental Application
[![Build Status](https://travis-ci.com/adityagupta1089/Bike-Rental-App.svg?token=4v3vJQcDSzzVad8yn6JN&branch=master)](https://travis-ci.com/adityagupta1089/Bike-Rental-App)
[![Maintainability](https://api.codeclimate.com/v1/badges/7eafcae08a876b764447/maintainability)](https://codeclimate.com/github/adityagupta1089/Bike-Rental-App/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/7eafcae08a876b764447/test_coverage)](https://codeclimate.com/github/adityagupta1089/Bike-Rental-App/test_coverage)

## 1. Introduction 
This is the server component of an application that is used for renting or leasing cycles.

## 2. Requirements
- [Maven](https://maven.apache.org/)
- [Java JDK](https://www.oracle.com/technetwork/java/javase/downloads/index.html)

## 3. Getting started
1. Clone this repository: 
2. Setup Database
3. Import into your IDE & Run.

### 3.1 Cloning
```
git clone https://github.com/adityagupta1089/Bike-Rental-App.git
```

### 3.2. Setting up database
1. Install [postgreSQL](https://www.postgresql.org/download/)
2. Open pgAdmin and create a user name with password the same as in `dev.yml` database user and password.
3. Create a database the same as in `dev.yml` database url.
4. Grant that user access to the database. 

### 3.3 Importing into IDE
1. Open terminal
2. `mvn dependency:resolve`
3. `mvn clean compile install`
4. Follow IDE specific steps
5. Can be run using: 
```
java -jar bike-rental-app-0.1.jar server dev.yml
```

#### 3.3.1. Eclipse
1. `mvn eclipse:eclipse -DdownloadSources=true`
2. From eclipse, File –> Import –> Existing Project into workspace
3. Create initial Database Tables: `java -jar target/bike-rental-app-0.1.jar db migrate dev.yml`
4. Can be run using `com.csl456.BikeRentalApplication server dev.yml`

#### 3.3.2. IntelliJ IDEA
1. Import project (type `Maven`)
2. You may need to setup jdk path to `java8`
3. Create initial Database Tables: `java -jar target/bike-rental-app-0.1.jar db migrate dev.yml`
4. Can be run using `com.csl456.BikeRentalApplication server dev.yml`

### 3.4 Incremental building (after making changes)
```
mvn compile install
```

## 4. Miscellaneous:

### 4.1. Database Operations:

#### 4.1.1. Check if database state matches:
```
java -jar bike-rental-app-0.1.jar db status dev.yml
```

#### 4.1.2. Prepare rollback for migration (before appplying migrations):
```
java -jar bike-rental-app-0.1.jar db prepare-rollback dev.yml
```

#### 4.1.3. Migrate database changes:
Please use `--dry-run` first

```
java -jar bike-rental-app-0.1.jar db migrate dev.yml
```

