mvn clean

mvn package -DskipTests

docker rmi -f lgj/demo-0.0.1-SNAPSHOT.jar

mvn dockerfile:build

docker images
