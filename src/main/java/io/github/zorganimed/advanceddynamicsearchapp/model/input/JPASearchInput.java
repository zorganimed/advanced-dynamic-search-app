package io.github.zorganimed.advanceddynamicsearchapp.model.input;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
public class JPASearchInput {

    @NotNull
    private RootFilter filter;

    @Nullable
    @Valid
    private JPASearchOptions options;

    @Data
    public static class JPASearchOptions {
        private String sortKey;
        private Boolean sortDesc = false;
        private Integer pageSize;
        private Integer pageOffset;
    }

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.DEDUCTION,
            defaultImpl = FilterSingleValue.class
    )
    @JsonSubTypes({
            @JsonSubTypes.Type(RootFilter.class),
            @JsonSubTypes.Type(FilterSingleValue.class),
            @JsonSubTypes.Type(FilterMultipleValues.class)
    })
    @Data
    public abstract static class Filter {

        @NotEmpty
        private String operator;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class RootFilter extends Filter {

        @NotEmpty
        private List<Filter> filters;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class FieldFilter extends Filter {

        @NotEmpty
        private String key;

        @Nullable
        private JPASearchFilterOptions options;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class FilterSingleValue extends FieldFilter {
        private Object value;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class FilterMultipleValues extends FieldFilter {
        private List<Object> values;
    }

    @Data
    public static class JPASearchFilterOptions {
        private boolean ignoreCase;
        private boolean trim;
        private boolean negate;
    }
}
