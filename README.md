# Bike Rental Application 

## Requirements
- [Maven](https://maven.apache.org/)
- [Java JDK](https://www.oracle.com/technetwork/java/javase/downloads/index.html)

## Steps to create `jar`
1. Open terminal
2. `mvn dependency:resolve`
3. `mvn clean compile install`

## Incremental building
1. `mvn compile install`


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

### Generate documentation:
`java -jar jarname.jar db generate-docs config.yml ~/db-docs/`

