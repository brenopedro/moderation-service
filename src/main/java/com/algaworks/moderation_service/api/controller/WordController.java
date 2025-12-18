package com.algaworks.moderation_service.api.controller;

import com.algaworks.moderation_service.api.model.WordInput;
import com.algaworks.moderation_service.common.ProhibitedWords;
import com.algaworks.moderation_service.domain.model.ProhibitedWord;
import com.algaworks.moderation_service.domain.repository.ProhibitedWordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moderate/words")
public class WordController {

    private final ProhibitedWordRepository prohibitedWordRepository;

    public WordController(ProhibitedWordRepository prohibitedWordRepository) {
        this.prohibitedWordRepository = prohibitedWordRepository;

        prohibitedWordRepository.findAll()
                .forEach(prohibitedWord -> ProhibitedWords.addWord(prohibitedWord.getWord()));
    }

    @GetMapping
    public List<String> getProhibitedWords() {
        List<ProhibitedWord> words = prohibitedWordRepository.findAll();
        return words.stream()
                .map(ProhibitedWord::getWord)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProhibitedWord(@RequestBody WordInput input) {
        ProhibitedWord prohibitedWord = ProhibitedWord.builder()
                .word(input.getWord())
                .build();

        prohibitedWordRepository.save(prohibitedWord);
        ProhibitedWords.addWord(input.getWord());
    }
}
