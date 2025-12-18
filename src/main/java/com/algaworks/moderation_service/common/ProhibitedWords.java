package com.algaworks.moderation_service.common;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ProhibitedWords {

    @Getter
    private static final List<String> words = new ArrayList<>();

    public static void addWord(String word) {
        if (word != null && !words.contains(word)) {
            words.add(word);
        }
    }
}
