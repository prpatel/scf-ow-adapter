package com.ibm.openwhisk.scf.demo;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.function.Function;

public abstract class ScfOpenwhiskAdapterBean implements ApplicationRunner {

    private JsonObject response;
    @Autowired
    private ApplicationContext ctx;

    public static JsonObject main(JsonObject args) {
        // serialize the GSON to string, send raw to the Spring bean
        String[] stringifedJson = {args.toString()};

        // config using @Configuration bean
        SpringApplicationBuilder springApp = new SpringApplicationBuilder()
                .sources(ScfDemo1.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE);

        ConfigurableApplicationContext cac = springApp.run(stringifedJson);

//        ScfDemo1 f = cac.getBean(ScfDemo1.class);
//        return f.getResponse();
        return new JsonObject();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String requestJson = args.getSourceArgs()[0];
        Function fn = ctx.getBean(Function.class);
        String responseString = (String)fn.apply(requestJson);
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
}
