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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@ComponentScan(basePackages = "${spring.cloud.function.scan.packages}")
public class ScfOpenwhiskAdapterBean implements ApplicationRunner {

    private JsonObject response;
    @Autowired
    private ApplicationContext ctx;

    public static JsonObject main(JsonObject args) {
        // serialize the GSON to string, send raw to the Spring bean
        String[] stringifedJson = {args.toString()};

        SpringApplicationBuilder springApp = new SpringApplicationBuilder()
                .sources(ScfOpenwhiskAdapterBean.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE);

        ConfigurableApplicationContext cac = springApp.run(stringifedJson);
        ScfOpenwhiskAdapterBean f = cac.getBean(ScfOpenwhiskAdapterBean.class);
        System.out.println("Response: "+ f.getResponse());
        return f.getResponse();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String requestJson = args.getSourceArgs()[0];
        // by name
        // Function fn = (Function)ctx.getBean("myfunction");

        // by type, but must be registered as a spring bean
        Function fn = (Function)ctx.getBean(Function.class);
        System.out.println(fn.getClass().getName());

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
