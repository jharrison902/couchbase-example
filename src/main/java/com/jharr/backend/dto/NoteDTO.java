package com.jharr.backend.dto;

import com.couchbase.client.java.document.json.JsonObject;
import com.jharr.backend.entity.Note;

/**
 * The DTO for the {@link com.jharr.backend.entity.Note}
 */
public class NoteDTO {

    private String id;

    private String body;

    public NoteDTO(Note note){
        this.id = note.getId();
        this.body = note.getBody();
    }

    public NoteDTO(JsonObject content) {
        this.id = content.getString("id");
        this.body = content.getString("body");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
