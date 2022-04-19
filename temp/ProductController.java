package io.vteial.workbench.controllers;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.validation.constraints.Null;
import javax.ws.rs.*;

import lombok.extern.jbosslog.JBossLog;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.Logger;

import io.vteial.workbench.models.Product;

@Slf4j
@Path("api/products")
public class ProductController {

    private long counter = 1;

    private static Set<Product> items = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public ProductController() {
        Set<Product> items = this.getRepository();

        Product item = Product.builder()
                .code("INR")
                .name("Indian Rupee")
                .unit(1)
                .rate(1.0f)
                .build();
        this.add(item);

        item = Product.builder()
                .code("USD")
                .name("United States Dollar")
                .unit(1)
                .rate(75.60f)
                .build();
        this.add(item);
        log.info("Products : {}", items);
    }

    @GET
    public Set<Product> list() {
        return items;
    }

    @GET
    @Path("{id}")
    public Product get(@PathParam("id") Long id) {
        Set<Product> items = this.getRepository();
        Product item = items.stream()
                .filter(cItem -> cItem.getId() == id)
                .findFirst()
                .orElse(null);
        return item;
    }

    @POST
    public Set<Product> add(Product item) {
        Set<Product> items = this.getRepository();
        item.setId(counter++);
        items.add(item);
        return items;
    }

    @PUT
    @Path("{id}")
    public void set(@PathParam("id") Long id, Product eItem) {
        Set<Product> items = this.getRepository();
        Product item = items.stream()
                .filter(cItem -> cItem.getId() == id)
                .findFirst()
                .orElse(null);
        if(item != null) {
            item.setCode(eItem.getCode());
            item.setName(eItem.getName());
            item.setDesc(eItem.getDesc());
            item.setUnit(eItem.getUnit());
            item.setRate(eItem.getRate());
        }
    }

    @DELETE
    @Path("{id}")
    public Set<Product> remove(@PathParam("id") Long id) {
        Set<Product> items = this.getRepository();
        items.removeIf(existingItem -> existingItem.getId() == id);
        return items;
    }

    private Set<Product> getRepository() {
        return ProductController.items;
    }
}