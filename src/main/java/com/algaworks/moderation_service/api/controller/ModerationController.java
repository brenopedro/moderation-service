package com.algaworks.moderation_service.api.controller;

import com.algaworks.moderation_service.api.model.ModerationInput;
import com.algaworks.moderation_service.api.model.ModerationOutput;
import com.algaworks.moderation_service.common.ProhibitedWords;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/moderate")
public class ModerationController {

    @PostMapping
    public ModerationOutput moderate(@RequestBody ModerationInput input) {

        Optional<String> foundWord = ProhibitedWords.getWords().stream()
                .filter(word -> input.getText().toLowerCase().contains(word.toLowerCase()))
                .findFirst();

        return ModerationOutput.builder()
                .approved(foundWord.isEmpty())
                .reason(foundWord.orElse(null))
                .build();
    }
}
