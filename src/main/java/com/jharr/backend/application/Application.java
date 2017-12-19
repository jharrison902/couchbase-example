package com.jharr.backend.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableCouchbaseRepositories("com.jharr.backend.repository")
@ComponentScan({"com.jharr.backend.service", "com.jharr.backend.dto"})
@EntityScan("com.jharr.backend.entity")
public class Application extends AbstractCouchbaseConfiguration {
    public static void main(String... args){
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected List<String> getBootstrapHosts() {
        return Arrays.asList("127.0.0.1");
    }

    @Override
    protected String getBucketName() {
        return "note";
    }

    @Override
    protected String getBucketPassword() {
        return "password";
    }
}
