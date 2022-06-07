FROM openjdk:8-jdk-alpine
COPY /Users/nataly/IdeaProjects/AlfaBank_test/build/libs/AlfaBank_test-1.0.0.jar /AlfaBank_test-1.0.0.jar
ENTRYPOINT ["java","-jar","AlfaBank_test-1.0.0.jar"]