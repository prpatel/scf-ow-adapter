mvn install
#ibmcloud fn action update spring-simple-java8action target/spring-simple-java8action.jar --main com.example.FunctionApp

ibmcloud fn action update spring-simple-java8action target/spring-simple-java8action.jar --main com.ibm.openwhisk.scf.demo.ScfOpenwhiskAdapter
ibmcloud fn action invoke spring-simple-java8action --result  --param name Pratik

ibmcloud fn action update spring-simple-java8action target/spring-simple-java8action.jar --main com.ibm.openwhisk.scf.demo.ScfOpenwhiskAdapterBean
ibmcloud fn action invoke spring-simple-java8action --result
