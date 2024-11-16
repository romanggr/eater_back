package com.eater.eater.utils.formatter;

import org.springframework.format.Formatter;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalTimeFormatter implements Formatter<LocalTime> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public LocalTime parse(String text, Locale locale) throws ParseException {
        return LocalTime.parse(text, FORMATTER);
    }

    @Override
    public String print(LocalTime object, Locale locale) {
        return object.format(FORMATTER);
    }
}
