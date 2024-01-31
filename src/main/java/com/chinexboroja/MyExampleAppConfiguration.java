package com.chinexboroja;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.dropwizard.core.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import org.hibernate.validator.constraints.*;
import jakarta.validation.constraints.*;

public class MyExampleAppConfiguration extends Configuration {

    @NotEmpty
    private String template;
    @NotEmpty
    private String defaultName = "Stranger";
    @JsonProperty
    public String getTemplate() {
        return template;
    }
    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }
    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }
    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }

    private List<String> songWriters;

    @JsonCreator
    public MyExampleAppConfiguration(@JsonProperty("songWriters") List<String> songWriters) {
        this.songWriters = songWriters;
    }
    public List<String> getSongWriters() {
        return this.songWriters;
    }
}
