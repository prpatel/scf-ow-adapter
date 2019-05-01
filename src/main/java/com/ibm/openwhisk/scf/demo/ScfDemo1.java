package com.ibm.openwhisk.scf.demo;

import org.springframework.context.annotation.Bean;

import java.util.function.Function;

public class ScfDemo1  {
    @Bean
    public Function<String, String> reverseString() {
        return value -> new StringBuilder(value).reverse().toString();
    }
}
