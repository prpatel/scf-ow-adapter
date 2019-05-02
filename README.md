# scf-ow-adapter
Prototype Spring Cloud Functions to Openwhisk Adapter and Sample Project
Unlike other samples out there, this one does not use a docker container, but runs in the standard Openwhisk java8action container.

Quick start guide:
mvn install

# Basic Spring and Openwhisk app:

```
ibmcloud fn action create spring-simple-java8action target/spring-simple-java8action.jar --main com.example.FunctionApp
```

### This ScfOpenwhiskAdapter app uses an embedded @Bean java Function for the business logic

```
ibmcloud fn action update spring-simple-java8action target/spring-simple-java8action.jar --main com.ibm.openwhisk.scf.demo.ScfOpenwhiskAdapter

ibmcloud fn action invoke spring-simple-java8action --result  --param name Pratik
```

### This ScfOpenwhiskAdapter app uses an external Java Function class for the business logic
Per the Spring Cloud Function implementation, you must define the Function class in application.properties
so the component scanner can find it.

```
ibmcloud fn action update spring-simple-java8action target/spring-simple-java8action.jar --main com.ibm.openwhisk.scf.demo.ScfOpenwhiskAdapterBean

ibmcloud fn action invoke spring-simple-java8action --result
```

### Implementation notes
- This project uses an uber jar (via the maven shade plugin) to contain all dependencies
- The dependencies have been trimmed to make a smaller jar
- The @SpringBootApplication annotation is NOT used, as it pulls in and instantiates a full Servlet engine
- This is a work in progress, additional optimizations can be made to speed up startup time and reduce jar size
- The  SCF Reactive and Messaging stuff is not implemented

