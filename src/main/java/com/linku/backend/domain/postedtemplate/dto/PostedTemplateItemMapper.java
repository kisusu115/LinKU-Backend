package com.linku.backend.domain.postedtemplate.dto;

import com.linku.backend.domain.postedtemplate.PostedTemplateItem;
import com.linku.backend.domain.postedtemplate.dto.response.PostedTemplateItemResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostedTemplateItemMapper {

    PostedTemplateItemResponse toResponse(PostedTemplateItem item);
    
    List<PostedTemplateItemResponse> toResponseList(List<PostedTemplateItem> items);
} 