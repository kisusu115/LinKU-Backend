package com.linku.backend.domain.template.dto;

import com.linku.backend.domain.template.TemplateItem;
import com.linku.backend.domain.template.dto.response.TemplateItemResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TemplateItemMapper {

    TemplateItemResponse toResponse(TemplateItem item);
}