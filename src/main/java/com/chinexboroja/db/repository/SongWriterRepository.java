package com.chinexboroja.db.repository;

import com.chinexboroja.core.model.SongWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SongWriterRepository {
    private final List<SongWriter> songWriters;

    public SongWriterRepository() {
        this.songWriters = new ArrayList<>();
    }
    public SongWriterRepository(List<SongWriter> songWriters) {
        this.songWriters = songWriters;
    }

    public List<SongWriter> findAll() {
        return songWriters;
    }

    public Optional<SongWriter> getByName(final String name) {
       return songWriters.stream().filter(sw -> sw.getName().equals(name)).findFirst();
    }

}
