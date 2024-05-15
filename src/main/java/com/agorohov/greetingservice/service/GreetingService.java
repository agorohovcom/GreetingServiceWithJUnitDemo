package com.agorohov.greetingservice.service;

import org.apache.commons.lang3.StringUtils;

public class GreetingService {

    public String generateGreetings() {
        return generateGreetings(null);
    }

    public String generateGreetings(String name) {
        if (name == null || name.isBlank()) {
            return "Greetings, stranger!";
        }

        if (!StringUtils.isAlpha(name)) {
            throw new IllegalArgumentException("Name contains invalid chars: " + name);
        }

        return "Greetings, " + StringUtils.capitalize(name.toLowerCase()) + "!";
    }
}
