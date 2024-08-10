package io.github.zorganimed.advanceddynamicsearchapp.util;

import io.github.zorganimed.advanceddynamicsearchapp.annotation.Searchable;
import io.github.zorganimed.advanceddynamicsearchapp.exception.InvalidFieldException;
import io.github.zorganimed.advanceddynamicsearchapp.model.JPASearchOperatorFilter;

import java.util.stream.Stream;

public class ValidationUtils {
    public static void searchableValidations(Searchable searchable, String field, JPASearchOperatorFilter searchFilter) {

        if (searchable.allowedFilters() != null && searchable.allowedFilters().length > 0 && Stream.of(searchable.allowedFilters()).noneMatch(sf -> sf.equals(searchFilter))) {
            throw new InvalidFieldException("Not allowed filters [" + searchFilter.getValue() + "] for field [" + field + "]", field);
        }

        if (searchable.notAllowedFilters() != null && searchable.notAllowedFilters().length > 0 && Stream.of(searchable.notAllowedFilters()).anyMatch(sf -> sf.equals(searchFilter))) {
            throw new InvalidFieldException("Not allowed filters [" + searchFilter.getValue() + "] for field [" + field + "]", field);
        }

        if (!searchable.allowLikeFilters() && searchFilter.isLike()) {
            throw new InvalidFieldException("Not allowed filters [" + searchFilter.getValue() + "] for field [" + field + "]", field);
        }

    }

}
