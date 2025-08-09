package com.linku.backend.domain.postedtemplate.repository;

import com.linku.backend.domain.common.enums.Status;
import com.linku.backend.domain.postedtemplate.PostedTemplateItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostedTemplateItemRepository extends JpaRepository<PostedTemplateItem, Long> {

    List<PostedTemplateItem> findAllByPostedTemplate_PostedTemplateIdAndStatus(Long postedTemplateId, Status status);
}
