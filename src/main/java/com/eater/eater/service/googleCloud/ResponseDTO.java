package com.eater.eater.service.googleCloud;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDTO {
    private List<Row> rows;
    private String status;


    @Data
    public static class Row {
        private List<Element> elements;
    }

    @Data
    public static class Element {
        private Distance distance;
    }

    @Data
    public static class Distance {
        private String text;
        private int value;
    }
}