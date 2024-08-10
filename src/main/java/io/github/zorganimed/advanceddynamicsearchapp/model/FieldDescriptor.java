package io.github.zorganimed.advanceddynamicsearchapp.model;

import io.github.zorganimed.advanceddynamicsearchapp.annotation.Searchable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldDescriptor {

    private String path;
    private Searchable searchable;
    private JPASearchType searchType;
    private String entityKey;
}
