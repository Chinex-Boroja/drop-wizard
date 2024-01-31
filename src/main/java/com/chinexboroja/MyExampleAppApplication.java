package com.chinexboroja;

import com.chinexboroja.core.model.SongWriter;
import com.chinexboroja.db.repository.SongWriterRepository;
import com.chinexboroja.health.TemplateHealthCheck;
import com.chinexboroja.resources.HelloWorldResource;
import com.chinexboroja.resources.Message;
import com.chinexboroja.resources.SongWriters;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import java.util.ArrayList;
import java.util.List;

public class MyExampleAppApplication extends Application<MyExampleAppConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MyExampleAppApplication().run(args);
    }

    @Override
    public String getName() {
        return "MyExampleApp";
    }

    @Override
    public void initialize(final Bootstrap<MyExampleAppConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final MyExampleAppConfiguration configuration,
                    final Environment environment) {
        //Implement application
        HelloWorldResource resource = new HelloWorldResource(configuration.getTemplate(), configuration.getDefaultName());
        environment.jersey().register(resource);

        environment.jersey().register(Message.class);

        TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        //Registering the SongWriters handler

        // preload songwriters
        List<SongWriter> preload = new ArrayList<>();
        configuration.getSongWriters().forEach(sw -> preload.add(new SongWriter(sw, new ArrayList<>())));
        SongWriterRepository songWriterRepository = new SongWriterRepository(preload);
        SongWriters songWriters = new SongWriters(songWriterRepository);
        environment.jersey().register(songWriters);



    }

}
