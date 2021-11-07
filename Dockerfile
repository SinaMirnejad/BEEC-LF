FROM openjdk:11
EXPOSE 8080
ADD target/SinaMirnejadBEEC.jar SinaMirnejadBEEC.jar
ENTRYPOINT ["java","-jar","/SinaMirnejadBEEC.jar"]