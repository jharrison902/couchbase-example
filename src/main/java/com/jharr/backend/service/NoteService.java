package com.jharr.backend.service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.search.SearchQuery;
import com.couchbase.client.java.search.result.SearchQueryResult;
import com.jharr.backend.dto.NoteDTO;
import com.jharr.backend.entity.Note;
import com.jharr.backend.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api")
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private Bucket bucket;

    @RequestMapping(value="/notes", method = GET)
    public List<NoteDTO> getAllNotes(){
        return newArrayList(noteRepository.findAll()).stream().map(x -> new NoteDTO(x)).collect(Collectors.toList());
    }

    @RequestMapping(value="/notes", method = POST, consumes = MediaType.ALL_VALUE)
    public NoteDTO postNewNote(@RequestBody Note note){
        String generatedId = UUID.randomUUID().toString();
        note.setId(generatedId);
        note.setUuid(generatedId);
        return new NoteDTO(noteRepository.save(note));
    }

    @RequestMapping(value="/notes", method=PUT, consumes = MediaType.ALL_VALUE)
    public NoteDTO updateNote(@RequestBody Note note){
        Note toEdit = noteRepository.getNoteById(note.getId());
        if(null!=toEdit){
            toEdit.setBody(note.getBody());
            return new NoteDTO(noteRepository.save(toEdit));
        } else {
            return null;
        }
    }

    @RequestMapping(value="/notes/{id}", method=GET, consumes = MediaType.ALL_VALUE)
    public NoteDTO getNoteById(@PathVariable String id){
        Note toReturn = noteRepository.getNoteById(id);
        if(null!=toReturn){
            return new NoteDTO(toReturn);
        } else {
            //Logging here
            return null;
        }
    }

    @RequestMapping(value="/notes", params = "query", method = GET, consumes = MediaType.ALL_VALUE)
    public List<NoteDTO> getNotesByBody(@RequestParam("query") String query){

        SearchQueryResult result = bucket.query(new SearchQuery("body",SearchQuery.matchPhrase(query)).fields("body"));
        return result.hits().stream().map(x -> new NoteDTO(bucket.get(x.id()).content())).collect(Collectors.toList());

        //Leaving this here so you can compare. Using the full text search index is nearly 4 times faster on my system than using a LIKE statement!
        //return newArrayList(noteRepository.findByBodyContaining(query)).stream().map(x -> new NoteDTO(x)).collect(Collectors.toList());
    }

}
