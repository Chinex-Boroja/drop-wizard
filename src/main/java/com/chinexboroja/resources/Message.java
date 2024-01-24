package com.chinexboroja.resources;

import com.codahale.metrics.annotation.Timed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/new-message")
public class Message {
    @Timed
    @GET
    public String getMessage() {
        return "Hello from new resource";
    }
}
