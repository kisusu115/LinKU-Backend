package com.linku.backend.domain.template.dto;

import com.linku.backend.domain.template.dto.request.TemplateItemPositionRequest;
import com.linku.backend.domain.common.template.TemplateItemPosition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TemplateItemPositionRequestMapper {
    TemplateItemPosition toEntity(TemplateItemPositionRequest request);
}