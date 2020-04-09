package com.letscard.utils.faker;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class AbstractFaker<T> {

    private Faker faker = new Faker(new Locale("it"));

    public Faker getFaker() {
        return faker;
    }

    public abstract T create();

    public List<T> create(int quantity) {
        List<T> list = new ArrayList<>(quantity);
        for (int i = 0; i < quantity; i++)
            list.add(create());
        return list;
    }
}
