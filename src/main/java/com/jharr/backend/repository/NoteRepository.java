package com.jharr.backend.repository;

import com.jharr.backend.entity.Note;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note, String> {
    public static final String RESOURCE_REL = "note";
    public static final String PATH = "note";

    Note getNoteById(String id);

    List<Note> findByBodyContaining(String body);


}


