package com.linku.backend.domain.icon.dto;

import com.linku.backend.domain.icon.Icon;
import com.linku.backend.domain.icon.dto.response.IconInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IconDtoMapper {

    @Mapping(source = "iconId", target = "id")
    IconInfoResponse toIconInfoResponse(Icon icon);
}
