package io.swagger.petstore.testing.utils;

import io.swagger.petstore.testing.models.pet.Category;
import io.swagger.petstore.testing.models.pet.Pet;
import io.swagger.petstore.testing.models.pet.Status;
import io.swagger.petstore.testing.models.pet.Tag;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;
import java.util.Random;

public class TestDataGenerator {

    public static Pet generateFullDataPet() {
        return Pet.builder()
            .id(getRandomInt())
            .name(getRandomString())
            .photoUrls(Arrays.asList(getRandomString(), getRandomString()))
            .category(Category.builder().id(getRandomInt()).name(getRandomString()).build())
            .tags(Arrays.asList(Tag.builder().id(getRandomInt()).name(getRandomString()).build(),
                Tag.builder().id(getRandomInt()).name(getRandomString()).build()))
            .status(Status.available)
            .build();
    }

    public static Pet generateMinDataPet() {
        return Pet.builder()
            .id(getRandomInt())
            .name(getRandomString())
            .build();
    }

    private static Integer getRandomInt() {
        return new Random().nextInt((65536) - 32768);
    }

    private static String getRandomString() {
        return RandomStringUtils.randomAlphabetic(7);
    }
}
