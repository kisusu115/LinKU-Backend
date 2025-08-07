package com.linku.backend.domain.postedtemplate.repository;

import com.linku.backend.domain.postedtemplate.PostedTemplate;
import com.linku.backend.domain.common.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostedTemplateRepository extends JpaRepository<PostedTemplate, Long> {

}
