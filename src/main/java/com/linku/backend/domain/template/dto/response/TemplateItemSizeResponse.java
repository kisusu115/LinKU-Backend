package com.linku.backend.domain.template.dto.response;

import lombok.Getter;
import lombok.Builder;

@Getter
@Builder
public class TemplateItemSizeResponse {

    private Integer width;
    private Integer height;
}
