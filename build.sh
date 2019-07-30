mvn clean

mvn package -DskipTests

docker rmi -f lgj/demo-0.0.1-SNAPSHOT

mvn dockerfile:build

docker images
