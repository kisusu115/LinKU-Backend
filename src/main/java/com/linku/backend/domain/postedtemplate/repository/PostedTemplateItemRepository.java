package com.linku.backend.domain.postedtemplate.repository;

import com.linku.backend.domain.postedtemplate.PostedTemplateItem;
import com.linku.backend.domain.common.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostedTemplateItemRepository extends JpaRepository<PostedTemplateItem, Long> {

}
