FROM eclipse-temurin:21
RUN mkdir /opt/gateway
COPY ./target/gateway-0.0.1-SNAPSHOT.jar /opt/gateway
CMD ["java","-jar", "/opt/gateway/gateway-0.0.1-SNAPSHOT.jar"]
