package com.jharr.backend.entity;

import com.couchbase.client.java.repository.annotation.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;

import javax.validation.constraints.NotNull;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

/**
 * Note Entity Class
 */
@Document
public class Note {

    @Id
    @Field
    private String uuid;

    @Field
    @NotNull
    private String id;

    @Field
    @NotNull
    private String body;

    public void setUuid(String uuid){
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
