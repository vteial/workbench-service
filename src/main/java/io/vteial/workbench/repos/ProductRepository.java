package io.vteial.workbench.repos;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.vteial.workbench.models.Product;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

}
