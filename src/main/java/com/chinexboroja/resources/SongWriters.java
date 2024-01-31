package com.chinexboroja.resources;

import com.chinexboroja.core.model.SongWriter;
import com.chinexboroja.db.repository.SongWriterRepository;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/songwriters")
@Produces(MediaType.APPLICATION_JSON)
public class SongWriters {

    private final SongWriterRepository repository;
    public SongWriters(SongWriterRepository repository) {
        this.repository = repository;
    }

    @GET
    public List<SongWriter> getBrands() {
        return repository.findAll();
    }
}
