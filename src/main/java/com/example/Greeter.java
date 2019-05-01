package com.example;

import java.util.function.Function;

// @checkstyle:off
public class Greeter implements Function<String, String> {

    public String apply(String name) {
        return "Hello " + name;
    }

}
