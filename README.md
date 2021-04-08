# waschplan project

## Helm Deployment Files
All files used for the deployment with helm can be found in the folder helm inside this project root directory

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

## Build a small Docker image

### Build the native executable
```shell script
./mvnw package -Pnative
```
### Build the Docker image
```shell script
docker build -f src/main/docker/Dockerfile.native -t toky03/waschplan-backend:0.0.27 .
```
