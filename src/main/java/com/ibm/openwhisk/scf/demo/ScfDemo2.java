package com.ibm.openwhisk.scf.demo;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component("myfunction")
public class ScfDemo2 implements Function<String, String> {

    @Override
    public String apply(String value) {
        return "Welcome to Spring Cloud Function ScfDemo2";
    }
}

