# scf-ow-adapter
Spring Cloud Functions to Openwhisk Adapter and Sample Project

Quick start guide:
mvn install

# Basic Spring and Openwhisk app:
ibmcloud fn action create spring-simple-java8action target/spring-simple-java8action.jar --main com.example.FunctionApp

## This ScfOpenwhiskAdapter app uses an embedded @Bean java Function for the business logic
`ibmcloud fn action update spring-simple-java8action target/spring-simple-java8action.jar --main com.ibm.openwhisk.scf.demo.ScfOpenwhiskAdapter
ibmcloud fn action invoke spring-simple-java8action --result  --param name Pratik`

## This ScfOpenwhiskAdapter app uses an external Java Function class for the business logic
Per the Spring Cloud Function implementation, you must define the Function class in application.properties
so the component scanner can find it
`ibmcloud fn action update spring-simple-java8action target/spring-simple-java8action.jar --main com.ibm.openwhisk.scf.demo.ScfOpenwhiskAdapterBean
ibmcloud fn action invoke spring-simple-java8action --result`
