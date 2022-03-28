FROM openjdk:11
EXPOSE 6004
ADD ./target/spring-boot-reactive-tipocambio*.jar micro-tipocambio.jar
ENTRYPOINT ["java","-jar","/micro-tipocambio.jar"]