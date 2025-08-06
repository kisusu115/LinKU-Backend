package com.linku.backend.domain.template.repository;

import com.linku.backend.domain.template.Template;
import com.linku.backend.domain.common.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {

    List<Template> findAllByOwner_UserIdAndClonedFalseAndStatus(Long userId, Status status);

    List<Template> findAllByOwner_UserIdAndClonedTrueAndStatus(Long userId, Status status);

    Optional<Template> findByTemplateIdAndOwner_UserIdAndStatus(Long templateId, Long userId, Status status);
}
