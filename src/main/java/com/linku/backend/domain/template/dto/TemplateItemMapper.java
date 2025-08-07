package com.linku.backend.domain.template.dto;

import com.linku.backend.domain.template.TemplateItem;
import com.linku.backend.domain.template.dto.response.TemplateItemResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TemplateItemMapper {

    TemplateItemResponse toResponse(TemplateItem item);

    List<TemplateItemResponse> toResponseList(List<TemplateItem> items);
}