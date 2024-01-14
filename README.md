•	Clonen des Repositories von https://github.com/IKobraa/SpringMicroService
•	Starten des Docker Servers mit dem Command:
docker run --name mysqlContainer -p 3307:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql
•	Command: mvn install (wenn Maven vorhanden) 
Oder: Projekt in der IDE öffnen und dort maven install ausführen
•	Starten der jar-File im Ordner: SpringMicroService/target/spring-boot-docker.jar im Terminal mit dem command: java -jar spring-boot-docker.jar
