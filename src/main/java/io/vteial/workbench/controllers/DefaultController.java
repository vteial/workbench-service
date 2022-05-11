package io.vteial.workbench.controllers;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.Authenticated;
import io.vteial.workbench.models.Message;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import static java.util.Objects.requireNonNull;

@Slf4j
@Path("/")
public class DefaultController {

    private final Template index;

    @ConfigProperty(name = "app.name")
    private String appName = "-";

    public DefaultController(Template index) {
        this.index = requireNonNull(index, "index template is required");
    }

    @GET
    @Path("index")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return index.data("appName", this.appName);
    }

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        return "Ping Pong!!!";
    }

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public Message hello() {
        Message message = Message.builder().build();
        message.setText("Hello World");
        return message;
    }

    @GET
    @Path("/secureHello")
    @Produces(MediaType.TEXT_PLAIN)
    @Authenticated
    public Message secureHello() {
        Message message = Message.builder().build();
        message.setText("Hello Secured Page");
        return message;
    }

    @GET
    @Path("/secureHome")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed("home")
    public Message secureHome() {
        Message message = Message.builder().build();
        message.setText("Hello Secured Home Page");
        return message;
    }

    @GET
    @Path("/secureList")
    @Produces(MediaType.TEXT_PLAIN)
    @RolesAllowed("list")
    public Message secureList() {
        Message message = Message.builder().build();
        message.setText("Hello List Page");
        return message;
    }

}
