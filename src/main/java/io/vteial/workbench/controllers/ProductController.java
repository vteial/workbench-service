package io.vteial.workbench.controllers;

import io.quarkus.panache.common.Sort;
import io.quarkus.security.Authenticated;
import io.vteial.workbench.models.Product;
import io.vteial.workbench.repos.ProductRepository;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Authenticated
@Path("api/products")
@Produces("application/json")
@Consumes("application/json")
@ApplicationScoped
@Slf4j
public class ProductController {

    @Inject
    ProductRepository productRepository;

    @GET
    public List<Product> list() {
        log.debug("Product Count : {}", productRepository.count());
        return productRepository.listAll(Sort.by("name"));
    }

    //@RolesAllowed("wb-manager")
    @POST
    @Transactional
    public Response add(Product item) {
        productRepository.persist(item);
        return Response.ok(item).status(201).build();
    }

    @GET
    @Path("{id}")
    public Product findById(@PathParam("id") Long id) {
        Product item = productRepository.findById(id);
        if (item == null) {
            throw new WebApplicationException("Product with id of " + id + " does not exists", 404);
        }
        return item;
    }

    //@RolesAllowed("wb-manager")
    @PUT
    @Path("{id}")
    @Transactional
    public Product update(@PathParam("id") Long id, Product item) {
        Product eitem = productRepository.findById(id);
        if (eitem == null) {
            throw new WebApplicationException("Product with id of " + id + " does not exists", 404);
        }
        eitem.setCode(item.getCode());
        eitem.setCode(item.getCode());
        eitem.setName(item.getName());
        eitem.setDesc(item.getDesc());
        eitem.setUnit(item.getUnit());
        eitem.setRate(item.getRate());
        return eitem;
    }

   // @RolesAllowed("wb-manager")
    @DELETE
    @Path("{id}")
    @Transactional
    public Response remove(@PathParam("id") Long id) {
        Product eitem = productRepository.findById(id);
        if (eitem == null) {
            throw new WebApplicationException("Product with id of " + id + " does not exists", 404);
        }
        productRepository.delete(eitem);
        return Response.status(204).build();
    }
}