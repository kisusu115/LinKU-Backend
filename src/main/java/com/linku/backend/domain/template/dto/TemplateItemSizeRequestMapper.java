package com.linku.backend.domain.template.dto;

import com.linku.backend.domain.template.dto.request.TemplateItemSizeRequest;
import com.linku.backend.domain.common.template.TemplateItemSize;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TemplateItemSizeRequestMapper {
    TemplateItemSize toEntity(TemplateItemSizeRequest request);
}