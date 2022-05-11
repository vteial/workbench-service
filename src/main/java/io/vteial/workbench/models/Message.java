package io.vteial.workbench.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Cacheable;
import javax.persistence.Entity;

@Builder
@AllArgsConstructor
@Data
public class Message {
    private String text;
}
