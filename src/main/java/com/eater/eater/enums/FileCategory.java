package com.eater.eater.enums;

import lombok.Getter;

@Getter
public enum FileCategory {
    AVATAR("avatars/"),
    RESTAURANT_PHOTO("restaurant/photo/"),
    RESTAURANT_DISH("restaurant/dish/");

    private final String directory;

    FileCategory(String directory) {
        this.directory = directory;
    }

}