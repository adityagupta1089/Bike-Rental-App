# Bike Rental Application
[![Build Status](https://travis-ci.com/adityagupta1089/Bike-Rental-App.svg?token=4v3vJQcDSzzVad8yn6JN&branch=master)](https://travis-ci.com/adityagupta1089/Bike-Rental-App)
[![Maintainability](https://api.codeclimate.com/v1/badges/7eafcae08a876b764447/maintainability)](https://codeclimate.com/github/adityagupta1089/Bike-Rental-App/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/7eafcae08a876b764447/test_coverage)](https://codeclimate.com/github/adityagupta1089/Bike-Rental-App/test_coverage)

## Introduction 
This is the server component of an application that is used for renting or 
leasing cycles.

## Requirements
- [Maven](https://maven.apache.org/)
- [Java JDK](https://www.oracle.com/technetwork/java/javase/downloads/index.html)

## Steps to create `jar`
1. Open terminal
2. `mvn dependency:resolve`
3. `mvn clean compile install`

## Incremental building
1. `mvn compile install`

## Setting up database
1. Install [postgreSQL](https://www.postgresql.org/download/)
2. Open pgAdmin and create a user name with password the same as in `dev.yml` database user and password.
3. Create a database the same as in `dev.yml` database url.
4. Grant that user access to the database. 

## Running locally
**Note**: `config.yml` refers to `dev.yml` or `prod.yml`

`java -jar jarname.jar server config.yml`

## Database Operations:
**Note**: 
- `config.yml` refers to `dev.yml` or `prod.yml`
- Replace `jarname.jar` with `bike-rental-app-0.1.jar` or so

### Check if database state matches:
`java -jar jarname.jar db status config.yml`

### Prepare rollback for migration:
`java -jar jarname.jar db prepare-rollback config.yml`

### Migrate database changes:
Please use `--dry-run` first

`java -jar jarname.jar db migrate config.yml`

