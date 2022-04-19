package io.vteial.workbench.data;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import io.vteial.workbench.models.Product;

@ApplicationScoped
public class ProductRepository implements  PanacheRepository<Product> {

}
