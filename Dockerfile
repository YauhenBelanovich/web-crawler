FROM maven:3.6-alpine as DEPS

WORKDIR /opt/app
COPY service-module/pom.xml service-module/pom.xml
COPY spring-boot-module/pom.xml spring-boot-module/pom.xml
COPY pom.xml .
RUN mvn -B -e -C org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline

FROM maven:3.6-alpine as BUILDER
WORKDIR /opt/app
COPY --from=deps /root/.m2 /root/.m2
COPY --from=deps /opt/app/ /opt/app
COPY service-module/src /opt/app/service-module/src
COPY spring-boot-module/src /opt/app/spring-boot-module/src

RUN mvn -B -e clean install -DskipTests=true

FROM openjdk:11-alpine
WORKDIR /opt/app
COPY --from=builder /opt/app/spring-boot-module/target/spring-boot-module-0.0.1-SNAPSHOT.jar .
EXPOSE 8081
CMD [ "java", "-jar", "-Xmx512m", "./spring-boot-module-0.0.1-SNAPSHOT.jar" ]

LABEL maintainer="yauhen2012@gmail.com"
