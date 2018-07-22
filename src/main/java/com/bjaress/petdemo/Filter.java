package com.bjaress.petdemo;

import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.function.Function;

public class Filter {

    //Header to position
    private Map<String, Integer> headerMap;

    //Header to required value
    private Map<String, String> query;


    public static Filter from(String[] headers, Map<String, String> query) {

        Map<String, Integer> headerMap = new HashMap<>();
        IntStream.range(0, headers.length)
            .forEach(i -> headerMap.put(headers[i], i));

        return new Filter(headerMap, query);
    }

    public Filter(Map<String, Integer> headerMap, Map<String, String> query) {
        this.query = query;
        this.headerMap = headerMap;
    }

    public boolean isMatch(String[] row) {
        return query.entrySet().stream()
            .allMatch(pair -> isMatch(pair.getKey(), pair.getValue(), row));
    }

    private boolean isMatch(String header, String value, String[] row) {
        return Optional.ofNullable(header)
            .map(headerMap::get)
            .filter(index -> index < row.length)
            .map(index -> row[index])
            .filter(data -> data.equals(value))
            .isPresent();
    }
}
