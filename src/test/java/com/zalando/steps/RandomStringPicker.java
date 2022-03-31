package com.zalando.steps;

import java.util.List;
import java.util.Random;

public class RandomStringPicker{

    public static String getRandomString(List<String> list) {
        Random rand = new Random();
        int index = rand.nextInt(list.size());
        String selected = list.get(index);
        if(selected.contains("(")){
            return selected.substring(0, selected.indexOf("("));
        }
        return selected;
    }
}
