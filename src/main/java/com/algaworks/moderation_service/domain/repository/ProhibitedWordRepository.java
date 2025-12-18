package com.algaworks.moderation_service.domain.repository;

import com.algaworks.moderation_service.domain.model.ProhibitedWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProhibitedWordRepository extends JpaRepository<ProhibitedWord, Long> {
}
