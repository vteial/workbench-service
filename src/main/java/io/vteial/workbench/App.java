package io.vteial.workbench;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
@Slf4j
public class App {

    private long stTime;

    private long edTime;

    void onStart(@Observes StartupEvent ev) {
        log.info("Application started...");
        stTime = System.currentTimeMillis();
    }

    void onStop(@Observes ShutdownEvent ev) {
        edTime = System.currentTimeMillis();
        long time = ((edTime - stTime) / 1000) / 60;
        log.info("App run for {} minutes", time);
        log.info("Application ended...");
    }
}
