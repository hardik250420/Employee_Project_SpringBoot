FROM openjdk:17
EXPOSE 8080
COPY target/spring-boot-docker.jar spring-boot-docker.jar 
ENTRYPOINT ["java","-jar","/spring-boot-docker.jar"]