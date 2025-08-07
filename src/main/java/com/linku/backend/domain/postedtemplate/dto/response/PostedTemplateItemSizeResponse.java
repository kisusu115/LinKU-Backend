package com.linku.backend.domain.postedtemplate.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostedTemplateItemSizeResponse {

    private Integer width;
    private Integer height;
} 