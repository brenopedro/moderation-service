package com.algaworks.moderation_service.api.model;

import io.hypersistence.tsid.TSID;
import lombok.Data;

@Data
public class ModerationInput {

    private String text;
    private String commentId;
}
