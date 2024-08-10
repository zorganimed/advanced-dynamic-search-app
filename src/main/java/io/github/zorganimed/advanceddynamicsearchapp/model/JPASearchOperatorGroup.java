package io.github.zorganimed.advanceddynamicsearchapp.model;

import io.github.zorganimed.advanceddynamicsearchapp.exception.JPASearchException;
import io.github.zorganimed.advanceddynamicsearchapp.function.JPASearchFunctions;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum JPASearchOperatorGroup {

    AND("and", JPASearchFunctions.AND),
    OR("or", JPASearchFunctions.OR),
    NOT("not", JPASearchFunctions.NOT);

    private final String value;
    private final JPASearchFunction<?, ?> function;

    public static JPASearchOperatorGroup load(String name) {
        return Stream.of(JPASearchOperatorGroup.values()).filter(f -> f.getValue().equals(name)).findAny()
                .orElseThrow(() -> new JPASearchException("Unknown operator: " + name));
    }
}
