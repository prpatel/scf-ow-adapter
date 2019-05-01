package com.example;

import com.google.gson.JsonObject;
import org.springframework.boot.*;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class FunctionApp implements ApplicationRunner {
    JsonObject response;

    public static JsonObject main(JsonObject args) {
        // serialize the GSON to string, send raw to the Spring bean
        String[] stringifedJson = {args.toString()};

        // config using @Configuration bean
        SpringApplicationBuilder springApp = new SpringApplicationBuilder()
                .sources(FunctionApp.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE);

        // run this Function
        ConfigurableApplicationContext cac = springApp.run(stringifedJson);

        // get the result and return it
        FunctionApp f = cac.getBean(FunctionApp.class);
        return f.getResponse();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
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
