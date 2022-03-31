package com.zalando.enums;

import edu.emory.mathcs.backport.java.util.Collections;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum ClothingTypes {

    Koszula,
    Koszulka,
    Chinosy,
    Spodenki,
    Bluza,
    Kurtka,
    Marynarka,
    Tshirt,
    Czapka,
    Jeans;


    private static final List<ClothingTypes> values = Collections.unmodifiableList(Arrays.asList(values()));
    private static final Random random = new Random();
    public static ClothingTypes getRandom(){
        return values.get(random.nextInt(values.size()));
    }
}
