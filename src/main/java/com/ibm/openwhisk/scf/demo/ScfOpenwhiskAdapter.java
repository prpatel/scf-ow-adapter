package com.ibm.openwhisk.scf.demo;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

public class ScfOpenwhiskAdapter implements ApplicationRunner {

    private JsonObject request;
    private JsonObject response;

    @Autowired
    protected ApplicationContext ctx;

    public static JsonObject main(JsonObject args) {
        SpringApplicationBuilder springApp = new SpringApplicationBuilder()
                .sources(ScfOpenwhiskAdapter.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE);
        String[] stringifedJson = {args.toString()};
        ConfigurableApplicationContext cac = springApp.run(stringifedJson);
        ScfOpenwhiskAdapter f = cac.getBean(ScfOpenwhiskAdapter.class);
        System.out.println("Response: "+ f.getResponse());
        return f.getResponse();
    }

    @Bean
    public Function<String, String> reverseString() {
        return value -> new StringBuilder(value).reverse().toString();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(this);
        String requestJson = args.getSourceArgs()[0];
        Function f = ctx.getBean(Function.class);
        JsonObject parsedJson = new JsonParser().parse(requestJson).getAsJsonObject();
        String name = parsedJson.getAsJsonPrimitive("name").getAsString();
        String responseString = (String)f.apply(name);
        JsonObject response = new JsonObject();
        response.addProperty("greetings", responseString);
        setResponse(response);
    }

    protected void setResponse(JsonObject response) {
        this.response = response;
    }

    protected JsonObject getResponse() {
        return response;
    }

    protected void setRequest(JsonObject request) {
        this.request = request;
    }

    protected JsonObject getRequest() {
        return request;
    }


}
