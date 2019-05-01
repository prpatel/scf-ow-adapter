package com.ibm.openwhisk.scf.demo;

public class ScfDemo2 extends ScfOpenwhiskAdapter {

    @Override
    public String apply(String value) {
        return "Welcome to Spring Cloud Function ScfDemo1";
    }
}

