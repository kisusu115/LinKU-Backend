package com.linku.backend.domain.template.dto;

import com.linku.backend.domain.template.dto.request.TemplateItemIconRequest;
import com.linku.backend.domain.common.template.IconSnapshot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TemplateItemIconRequestMapper {
    IconSnapshot toEntity(TemplateItemIconRequest request);
}