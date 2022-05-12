package io.vteial.workbench.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Cacheable
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @GeneratedValue
    @Id
    Long id;

    String code;

    String name;

    String desc;

    long unit;

    float rate;
}
