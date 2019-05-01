package com.example;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.google.gson.JsonObject;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//import org.springframework.boot.autoconfigure.SpringBootApplication;

// this scans everything so don't do it!
//@SpringBootApplication
/*
We can include multiple packages if we want using this in the component scan below:

basePackages = {"com.example.packageA",
        "com.example.packageB"
        }
*/


//@Configuration
//@ComponentScan(basePackageClasses = FunctionApp.class)
public class FunctionAppNotWorking implements ApplicationRunner {
  JsonObject response;

  public static JsonObject main(JsonObject args) {
    // serialize the JSON GSON to string to process inside the
    // Spring bean
    String jsonString = args.toString();
    String[] stringifedJson = {jsonString};

//     Java based configuration, no scanning at all
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
    ctx.register(FunctionAppNotWorking.class, Greeter.class);
    ctx.refresh();


    SpringApplicationBuilder springApp = new SpringApplicationBuilder()
            .parent(ctx)
            .bannerMode(Banner.Mode.OFF)
            .web(WebApplicationType.NONE);



// config using @Configuration bean
//    SpringApplicationBuilder springApp = new SpringApplicationBuilder()
//            .sources(FunctionApp.class)
//            .bannerMode(Banner.Mode.OFF)
//            .web(WebApplicationType.NONE);

    ConfigurableApplicationContext cac = springApp.run(stringifedJson);

    FunctionAppNotWorking f = cac.getBean(FunctionAppNotWorking.class);
    return f.getResponse();
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    System.out.println(args.getSourceArgs()[0]);
    JsonObject response = new JsonObject();
    response.addProperty("greetings", "Hello! Welcome to OpenWhisk");
    setResponse(response);
  }
  private void setResponse(JsonObject response) {
    this.response = response;
  }

  private JsonObject getResponse() {
    return response;
  }


}
